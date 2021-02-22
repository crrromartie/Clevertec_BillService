package ru.clevertec.bill.model.service.impl;

import ru.clevertec.bill.exception.ServiceException;
import ru.clevertec.bill.model.service.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

public class MailServiceImpl implements MailService {

    @Override
    public void sendEmail(String filepath) throws ServiceException {
        Session session = Session.getDefaultInstance(MailPropertiesManager.getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailPropertiesManager.getUserName(),
                        MailPropertiesManager.getUserPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailPropertiesManager.getUserName()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailPropertiesManager.getMailTo()));
            message.setSubject(MailPropertiesManager.getMailSubject());
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart text = new MimeBodyPart();
            text.setText(MailPropertiesManager.getMailText());
            MimeBodyPart pdf = new MimeBodyPart();
            pdf.attachFile(filepath);
            emailContent.addBodyPart(text);
            emailContent.addBodyPart(pdf);
            message.setContent(emailContent);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new ServiceException(e);
        }
    }
}
