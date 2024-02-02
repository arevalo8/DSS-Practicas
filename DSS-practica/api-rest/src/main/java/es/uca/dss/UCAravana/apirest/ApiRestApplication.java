package es.uca.dss.UCAravana.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;

import mailSender.MailNotifier;
import trabajo.Booking;
import trabajo.BookingManager;
import trabajo.Caravan;
import trabajo.CaravanManager;
import trabajo.Customer;
import trabajo.CustomerManager;
import trabajo.Dao;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "es.uca.dss", "trabajo" })
@ComponentScan(basePackages = { "es.uca.dss.UCAravana.apirest"})
@EntityScan("trabajo")
public class ApiRestApplication {

    @Autowired 
    BookingRepository bRepository;
    
    @Autowired
    CaravanRepository caRepository;
    
    @Autowired
    CustomerRepository cuRepository;
	
    @Autowired
    JavaMailSender javaMailSender;

    
    @Bean
    public MailNotifier mailNotifier() {
        return new MailNotifier(javaMailSender);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ApiRestApplication.class, args);
    }

    @Bean 
    public Dao<Caravan> caravanDao() {
    	return new CaravanJPADao(caRepository);
    }
    
    @Bean 
    public CaravanManager caravanManager() {
    	return new CaravanManager(caravanDao());
    }
    
    
    @Bean 
    public Dao<Customer> customerDao() {
    	return new CustomerJPADao(cuRepository);
    }
    
    @Bean 
    public CustomerManager customerManager() {
    	return new CustomerManager(customerDao());
    }
    
    @Bean 
    public Dao<Booking> bookingDao() {
    	return new BookingJPADao(bRepository);
    }
    
    @Bean 
    public BookingManager bookingManager() {
    	return new BookingManager(bookingDao(), mailNotifier());
    }
    
    
    
   /*@Bean
    public BookingManager bookingManager() {
        BookingJPADao bd = new BookingJPADao(bRepository);
        return new BookingManager(bd, mailNotifier());
    }*/

    
}
