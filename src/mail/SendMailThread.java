package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import Properties.PropertiesReader;

public class SendMailThread implements Runnable {

    private String destinataire;
    private String subject;
    private String text;

    private static Properties props = PropertiesReader.getInstance().getProperties();

    private static Properties properties;
    static {
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", props.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.host", props.getProperty("mail.smtp.host"));
        properties.setProperty("mail.smtp.auth", props.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.port", props.getProperty("mail.smtp.port"));
        properties.setProperty("mail.smtp.ssl", props.getProperty("mail.smtp.ssl"));
        properties.setProperty("mail.smtp.tls", props.getProperty("mail.smtp.tls"));
        properties.setProperty("mail.smtp.user", props.getProperty("mail.smtp.user"));
    }
    public SendMailThread(String subject, String text, String destinataire) {
        this.subject = subject;
        this.text = text;
        this.destinataire = destinataire;
    }

    public void run() {
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
            transport = session.getTransport(props.getProperty("mail.transport.protocol"));
            transport.connect(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
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
}
