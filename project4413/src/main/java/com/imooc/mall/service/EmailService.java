package com.imooc.mall.service;

import com.imooc.mall.model.vo.CartVO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Email Service
 */

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    Boolean saveEmailToRedis(String emailAddress, String verificationCode);

    Boolean checkEmailAndCode(String emailAddress, String verificationCode);
}
