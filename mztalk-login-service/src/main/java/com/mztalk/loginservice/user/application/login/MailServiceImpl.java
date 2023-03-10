package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceEmailAuthResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${mail.mailId}")
    private String mailId;
    @Value("${mail.mailPw}")
    private String mailPw;
    @Override
    public ServiceEmailAuthResponseDto getAuthCodeOfEmail(String email) {

        String authCode = makeRandomNumber();

        String contents = getContents(authCode);

        if(isValidEmail(email)) {
            sendMail("Your EmailAuthCode.",contents, email);
            return new ServiceEmailAuthResponseDto(authCode);
        }

        return new ServiceEmailAuthResponseDto("It`s not an appropriate email format");
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
                    return new javax.mail.PasswordAuthentication(mailId, mailPw);
                }

            });
            // Mail 조립
            Message mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress(mailId));
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
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.ssl.trust", host);
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
