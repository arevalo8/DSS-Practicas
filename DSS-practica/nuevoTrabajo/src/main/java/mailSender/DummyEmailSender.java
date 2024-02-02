package mailSender;

public class DummyEmailSender implements EmailSender {
    @Override
    public void sendMail(String to, String subject, String body) {
        // Unicamente para realizar pruebas
    }
}