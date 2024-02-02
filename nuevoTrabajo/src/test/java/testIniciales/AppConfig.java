package testIniciales;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.mail.Session; // Cambio en la importación
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;

import mailSender.MailNotifier;
import trabajo.BookingDao;
import trabajo.BookingManager;

public class AppConfig {
	
    @Bean
    @Primary
    public JavaMailSender javaMailSender() {
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        return javaMailSender;
    }
    
   MailNotifier mn = new MailNotifier(javaMailSender());
    
    BookingDao bd = new BookingDao();
    BookingManager bm = new BookingManager(bd, mn);
}



	/* @Autowired
	    JavaMailSender javaMailSender;

	    @Bean
	    public MailNotifier mailNotifier() {
	        return new MailNotifier(javaMailSender);
	    }
	    
	    @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("ucaravanas@gmail.com");
	        mailSender.setPassword("zqthuarxwbuxkhfw");
	        
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.connectiontimeout", "2000");
	        properties.put("mail.smtp.timeout", "2000");
	        properties.put("mail.smtp.writetimeout", "2000");
	        
	        mailSender.setJavaMailProperties(properties);
	        
	        return mailSender;
	    }*/

