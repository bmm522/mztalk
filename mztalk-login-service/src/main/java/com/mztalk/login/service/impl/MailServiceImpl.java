package com.mztalk.login.service.impl;

import com.mztalk.login.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ConcurrentHashMap;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Override
    public ConcurrentHashMap<String, Object> getAuthCodeOfEmail(String email) {
        ConcurrentHashMap<String, Object> map =new ConcurrentHashMap<String, Object>();

        String authCode = makeRandomNumber();

        String contents = getContents(authCode);

        if(isValidEmail(email)) {
            sendMail("Your EmailAuthCode.",contents, email);
            map.put("authCode", authCode);
            return map;
        }
        map.put("authCode",  "It`s not an appropriate email format");
        return map;
    }

    public void sendMail(String subejct, String body, Object obj) {
        try {
            InternetAddress[] receiverList = new InternetAddress[1];
            receiverList[0] = new InternetAddress((String)obj);
            // SMTP 발송 Properties 설정
            Properties props = getProperties();

            // SMTP Session 생성
            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator(){

                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(MailProperties.MAIL_ID, MailProperties.MAIL_PW);
                }

            });
            // Mail 조립
            Message mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress(MailProperties.MAIL_ID));
            mimeMessage.setRecipients(Message.RecipientType.TO, receiverList);
            // 메일 제목
            mimeMessage.setSubject(subejct);
            // 메일 본문 (.setText를 사용하면 단순 텍스트 전달 가능)
            mimeMessage.setContent(body, "text/html; charset=UTF-8");
            // Mail 발송
            System.out.println(mimeMessage);
            Transport.send(mimeMessage);

        } catch(Exception e) {
            System.out.println(e.getMessage());
            log.error("Error SendMail");
        }
    }


    private Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", MailProperties.HOST);
        props.put("mail.smtp.port", MailProperties.PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.ssl.trust", MailProperties.HOST);
        props.put("mail.smtp.auth", "true");
        return props;
    }

    public String makeRandomNumber() {
        String randomNumber = "";
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        for(int i = 0 ; i < 6 ; i ++) {
            randomNumber += random.nextInt(10);
        }
        return randomNumber;
    }

    public static boolean isValidEmail(String email) {
        String format = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return true;
        }
        log.error("It's not an appropriate email format");
        return false;
    }

    public String getContents(String authCode) {
        StringBuffer contents = new StringBuffer();
        contents.append("<h1>Email AuthCode</h1><br><br>");
        contents.append("<p> Your EmailAuthCode "+authCode+" <br>Please input authcode.</p><br>");
        return contents.toString();
    }


}
