import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class sendMail {

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

        Session session = Session.getInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setText(text);
            message.setSubject(subject);
            message.addRecipients(Message.RecipientType.TO, destinataire);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transport = null;
        try {
            transport = session.getTransport("smtp");
            transport.connect("daa7e5c9983ec2", "94013ef61aa8d0");
            transport.sendMessage(message, new Address[]{new InternetAddress(destinataire)});
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try{
                if(transport != null) {
                    transport.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        sendMessage("test", "test", "test@test.com");
    }
    }


