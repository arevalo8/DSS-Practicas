package mailSender;

public interface EmailSender {
    void sendMail(String toEmail,String subject, String text);
}