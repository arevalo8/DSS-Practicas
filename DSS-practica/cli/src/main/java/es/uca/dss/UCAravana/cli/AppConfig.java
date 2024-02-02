package es.uca.dss.UCAravana.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import mailSender.MailNotifier;
import trabajo.BookingDao;
import trabajo.BookingManager;
import trabajo.CaravanDao;
import trabajo.CaravanManager;
import trabajo.CustomerDao;
import trabajo.CustomerManager;

@Configuration
public class AppConfig {

    @Autowired
    JavaMailSender javaMailSender;

    @Bean
    public MailNotifier mailNotifier() {
        return new MailNotifier(javaMailSender);
    }

    @Bean
    public BookingManager bookingManager() {
        return new BookingManager(new BookingDao(), mailNotifier());
    }

    @Bean
    public CaravanManager caravanManager() {
        return new CaravanManager(new CaravanDao());
    }

    @Bean
    public CustomerManager customerManager() {
        return new CustomerManager(new CustomerDao());
    }
}
