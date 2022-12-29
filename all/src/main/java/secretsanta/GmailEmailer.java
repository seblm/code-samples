package secretsanta;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

import static java.util.Collections.singletonList;

class GmailEmailer implements Emailer {

    private final Session session;

    GmailEmailer(String userName, String password) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
    }

    @Override
    public void email(Person to, Person santa) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, singletonList(new InternetAddress(to.email)).toArray(new Address[1]));
            message.setSubject("Cadeau de Noël du 14 janvier 2023");
            message.setText("" +
                    "Bonjour " + to.firstName + "," +
                    "\n" +
                    "\n" +
                    "Voici le prénom de la personne à qui tu devras offrir un cadeau le 14 janvier 2023 à Vertou :\n" +
                    santa.firstName + "\n" +
                    "\n" +
                    "C'est un tirage au sort : personne d'autre que toi n'est au courant ! Garde le secret.");
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
