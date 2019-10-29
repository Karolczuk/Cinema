//package com.app.cinema.service.security.listeners;
//
//
//import com.app.cinema.exceptions.AppException;
//import com.app.cinema.service.UserService;
//import lombok.RequiredArgsConstructor;
////import org.springframework.context.ApplicationListener;
////import org.springframework.mail.javamail.MimeMessageHelper;
////
////import org.springframework.stereotype.Component;
////import org.springframework.mail.javamail.JavaMailSender;
////
////import javax.mail.internet.MimeMessage;
////import java.util.UUID;
//
//
//import j2html.TagCreator;
//import org.springframework.context.ApplicationListener;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.internet.MimeMessage;
//import java.util.UUID;
//
//// klasa przechwyci wygenerowane podczas rejestracji zdarzenie i pozwoli
//// wykonac dla niego pewne akcje
//@Component
//@RequiredArgsConstructor
//public class EventListener implements ApplicationListener<OnEventData> {
//
//    private final JavaMailSender javaMailSender;
//    private final UserService userService;
//
//    // ta metoda wykona sie kiedy wygenerujesz zdarzenie ktore
//    // przesyla typ OnEventData
//    @Override
//    public void onApplicationEvent(OnEventData onEventData) {
//
//        if ( onEventData == null ) {
//            throw new AppException("event data object is  null");
//        }
//
//        try {
//
//            // generowanie tokena
//            String token = UUID.randomUUID().toString();
//
//            // dodajemy usera z tokenem
//            userService.saveUserWithToken(onEventData.getUser(), token);
//
//            // wysylamy email z tokenem
//            String emailAddress = onEventData.getUser().getEmail();
//            String subject = "web-app registration link";
//            String url = onEventData.getUrl() + "security/registrationConfirmation?token=" + token;
//            String message = "Click to activate your account: " + url;
//
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//            String message2 = TagCreator.html("Click").renderFormatted();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//            messageHelper.setTo(emailAddress);
//            messageHelper.setSubject(subject);
//            // messageHelper.setText("<h1>Hello world</h1>");
//            mimeMessage.setContent("<h1>Hello world</h1>", "text/html");
//            javaMailSender.send(mimeMessage);
//
//            /*String message2 = TagCreator.html("Click").renderFormatted();
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setTo(emailAddress);
//            simpleMailMessage.setSubject(subject);
//            simpleMailMessage.setText(message2);
//            javaMailSender.send(simpleMailMessage);*/
//
//        } catch ( Exception e ) {
//            e.printStackTrace();
//        }
//
//    }
//}
