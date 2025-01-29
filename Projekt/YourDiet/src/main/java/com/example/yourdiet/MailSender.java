package com.example.yourdiet;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailSender {
    String host = "smtp.gmail.com";
    final String user = ""; //adres email nadwacy
    final String password = ""; //kod wygenerowwany przez Google


    MailSender(){}

    void sendMail(String eMail, String subject, String body){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(eMail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
