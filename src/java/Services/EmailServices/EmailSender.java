package Services.EmailServices;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EmailSpecifier(String reciever, String messageSubject, String message ) {
//        final String fromEmail = "subinsedhai002@gmail.com"; //requires valid gmail id
//        final String password = "ecgsagldchlcfqjc"; // correct password for gmail id
        final String fromEmail = "virinchicollegeproject@gmail.com"; //requires valid gmail id
        final String password = "cyolblhmbwywkopq"; // correct password for gmail id
        final String toEmail = reciever; // can be any email id 

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        sendEmail(session, toEmail, messageSubject, message);

    }
}
