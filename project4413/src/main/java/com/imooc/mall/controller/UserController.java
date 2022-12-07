package com.imooc.mall.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.filter.UserFilter;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.EmailService;
import com.imooc.mall.service.UserService;
import com.imooc.mall.util.EmailUtil;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User Controller
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    ExecutorService executorService;

    @GetMapping("/test")
    @ResponseBody
    public User personalPage() {
        return userService.getUser();
    }

    /**
     * register
     */
    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("userName") String userName,
                                    @RequestParam("password") String password, @RequestParam("emailAddress") String emailAddress, @RequestParam("verificationCode") String verificationCode) throws ImoocMallException {
        //        if username is empty, then throw need username.
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        //        if password is empty, then throw need password.
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        //the length of password cannot less than 8 digits
        if (password.length() < 8) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        //        if email is empty, then throw need email.
        if (StringUtils.isEmpty(emailAddress)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_EMAIL_ADDRESS);
        }
        //        if email verification code is empty, then throw need code.
        if (StringUtils.isEmpty(verificationCode)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_VERIFICATION_CODE);
        }
        //If the mailbox is already registered, it is not allowed to register again
        boolean emailPassed = userService.checkEmailRegistered(emailAddress);
        if (!emailPassed) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.EMAIL_ALREADY_BEEN_REGISTERED);
        }
        //Check if the mailbox and verification code matches
        Boolean passEmailAndCode = emailService.checkEmailAndCode(emailAddress, verificationCode);
        if (!passEmailAndCode) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.WRONG_VERIFICATION_CODE);
        }
//        process to register
        userService.register(userName, password, emailAddress);
        return ApiRestResponse.success();
    }

    /**
     * login
     */
    @GetMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password, HttpSession session)
            throws ImoocMallException {
        //        if username is empty, then throw need username.
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        //        if password is empty, then throw need password.
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //Save user information without saving the password
        user.setPassword(null);
        session.setAttribute(Constant.IMOOC_MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    /**
     * Update personality signature
     */
    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature)
            throws ImoocMallException {
        User currentUser = UserFilter.userThreadLocal.get();
        if (currentUser == null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInformation(user);
        return ApiRestResponse.success();
    }

    /**
     * Log out and clear session
     */
    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.IMOOC_MALL_USER);
        return ApiRestResponse.success();
    }

    /**
     * Administrator login interface
     */
    @GetMapping("/adminLogin")
    @ResponseBody
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName,
                                      @RequestParam("password") String password, HttpSession session)
            throws ImoocMallException {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //Verify if you are an administrator
        if (userService.checkAdminRole(user)) {
            //If you are an administrator, perform the operation
            //Save user information without saving the password
            user.setPassword(null);
            session.setAttribute(Constant.IMOOC_MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            //throw error msg, alert user that do not log in this page with non-admin account
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }
    }

    /**
     * Send Email
     */
    @PostMapping("/user/sendEmail")
    @ResponseBody
    public ApiRestResponse sendEmail(@RequestParam("emailAddress") String emailAddress)
            throws ImoocMallException {
        //Check if the email address is valid
        boolean validEmailAddress = EmailUtil.isValidEmailAddress(emailAddress);
        if (validEmailAddress) {
            //check if it is registered
            boolean emailPassed = userService.checkEmailRegistered(emailAddress);
            if (!emailPassed) {
                //if it is registered， alert user with msg
                return ApiRestResponse.error(ImoocMallExceptionEnum.EMAIL_ALREADY_BEEN_REGISTERED);
            } else {
//                call Util class to generate a random verification code
                String verificationCode = EmailUtil.genVerificationCode();

//                call redis to prepare for sending email
                Boolean saveEmailToRedis = emailService.saveEmailToRedis(emailAddress, verificationCode);
                if (saveEmailToRedis) {
                    executorService.submit(new Runnable() {
                        @Override
//                        make a new thread, achieving high concurrency
                        public void run() {
                            emailService.sendSimpleMessage(emailAddress, Constant.EMAIL_SUBJECT, "欢迎注册，您的验证码是" + verificationCode);
                        }
                    });
                    return ApiRestResponse.success();
                } else {
                    //email with codes already sent to user email
                    return ApiRestResponse.error(ImoocMallExceptionEnum.EMAIL_ALREADY_BEEN_SEND);
                }
            }
        } else {
//            did not pass email check
            return ApiRestResponse.error(ImoocMallExceptionEnum.WRONG_EMAIL);
        }
    }


    @GetMapping("/loginWithJwt")
    @ResponseBody
    public ApiRestResponse loginWithJwt(@RequestParam String userName, @RequestParam String password) {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //Save user information without saving the password
        user.setPassword(null);
        Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY);
        String token = JWT.create()
                .withClaim(Constant.USER_NAME, user.getUsername())
                .withClaim(Constant.USER_ID, user.getId())
                .withClaim(Constant.USER_ROLE, user.getRole())
                //Expiration time
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.EXPIRE_TIME))
                .sign(algorithm);
        return ApiRestResponse.success(token);
    }


    /**
     * Administrator login interface
     */
    @GetMapping("/adminLoginWithJwt")
    @ResponseBody
    public ApiRestResponse adminLoginWithJwt(@RequestParam("userName") String userName,
                                             @RequestParam("password") String password)
            throws ImoocMallException {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //Verify if you are an administrator
        if (userService.checkAdminRole(user)) {
            //If you are an administrator, perform the operation
            //Save user information without saving the password
            user.setPassword(null);
            Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY);
            String token = JWT.create()
                    .withClaim(Constant.USER_NAME, user.getUsername())
                    .withClaim(Constant.USER_ID, user.getId())
                    .withClaim(Constant.USER_ROLE, user.getRole())
                    //Expiration time
                    .withExpiresAt(new Date(System.currentTimeMillis() + Constant.EXPIRE_TIME))
                    .sign(algorithm);
            return ApiRestResponse.success(token);
        } else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }
    }
}
