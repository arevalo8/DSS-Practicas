package mailSender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailNotifier implements EmailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public MailNotifier(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	

    @Bean
    public JavaMailSender javaMailSender() {
        return mailSender;
    }

	@Override
	public void sendMail(String email, String subject, String body	) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setFrom("javi8arevalo@gmail.com");
		msg.setSubject(subject + " UCAravanas");
		msg.setTo(email);
		msg.setText(body);
		
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
