package com.imooc.mall.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Email Utility
 */
public class EmailUtil {

    public static String genVerificationCode() {
        //That's right... I was using the old fashion way of generating random verification code
        //I believe regular expressions can do the job for us, but I haven't found the right one.
        List<String> verificationChars = Arrays.asList(
                new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
                        "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"});
        Collections.shuffle(verificationChars);
        String result = "";
        //6 digit verification code with chars.
        for (int i = 0; i < 6; i++) {
            result += verificationChars.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        //What is the random verification code this time?
        System.out.println(EmailUtil.genVerificationCode());
    }

    /**
     * check if the email is valid.
     * @param email
     * @return true if it's a valid email, false otherwise.
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
//            this is a build-in class, we use it for validation
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
//            doesn't like other methods return boolean value, if email validation failed, method will throw exception!
        } catch (AddressException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


}
