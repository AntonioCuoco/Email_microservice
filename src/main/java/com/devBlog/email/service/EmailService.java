package com.devBlog.email.service;

//import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.devBlog.email.dto.EmailDto;
import com.devBlog.email.entity.User;
import com.devBlog.email.repository.UserRepository;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
	
	@Value("${username_gmail}")
    private String gmailUsername;
	
	@Value("${password_gmail}")
    private String gmailPassword; 
	
	public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) {
        String emailSender = emailDto.getEmail();
        String nameSender = emailDto.getName();
        String phoneNumber = emailDto.getPhone_number();
        String body = emailDto.getProject_details();

        // Configurazione delle proprietà per la connessione al server SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Sostituisci con il tuo host SMTP
        properties.put("mail.smtp.port", "587"); // Porta SMTP (potrebbe variare in base al provider)
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Credenziali per l'autenticazione al server SMTP
        String username = gmailUsername;
        String password = gmailPassword;

        // Creazione della sessione
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creazione del messaggio
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailSender));
            message.setSubject(nameSender);
            message.setText(body);

            // Invio del messaggio
            Transport.send(message);

            return new ResponseEntity<>("Email inviata con successo!", HttpStatus.OK);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Errore nell'invio dell'email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	public void saveEmail() {
		
	}
	
	
}
