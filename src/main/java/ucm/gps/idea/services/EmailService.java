package ucm.gps.idea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public  class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String token) {

        // Create the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("Complete Password Reset!");
        mailMessage.setFrom("daniatm1903@gmail.com");

        String text = "";
        text = "¿Ha olvidado su contraseña? \n" +
                "Para recuperar la contraseña haga click en el siguente enlace: \n\n" +
                 //"http://localhost:8080/changePassword?token=" + token + "<br/>" +
                "http://localhost:8080?t=" + token  +
                "\n\nSi no desea cambiar su contraseña o no ha solicitado este cambio," +
                "obvie y elimine este mensaje. \n" +
                "Gracias, \n" +
                "El equipo de Ideas";

        mailMessage.setText(text);
        javaMailSender.send(mailMessage);

    }

}