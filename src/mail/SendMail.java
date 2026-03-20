package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    private static Properties properties;

    static {
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "2525");
        properties.setProperty("mail.smtp.ssl", "false");
        properties.setProperty("mail.smtp.tls", "true");
        properties.setProperty("mail.smtp.user", "daa7e5c9983ec2");
    }

    public static void sendMessage(String subject, String text, String destinataire) {
        new Thread(new SendMailThread(subject, text, destinataire)).start();
    }

    public static void main(String[] args) {
        sendMessage("test", "test", "test@test.com");
    }
    }


