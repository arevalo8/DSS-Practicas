package es.uca.dss.UCAravana.apirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import trabajo.Customer;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CustomerRepository repository) {

    return args -> {
     // log.info("Preloading " + repository.save(new Customer("Javier", "Gonzales", "Perez", "78945478p", "685214555", "javi@gmail.com", "B")));
     // log.info("Preloading " + repository.save(new Customer("Paco", "Gonzal", "Pascal", "85214569m", "621111111", "pedro@gmail.com", "B96")));
    };
  }
  /*
  @Bean
  CommandLineRunner initDatabase(CustomerRepository repository) {

    return args -> {
      /*log.info("Preloading " + repository.save(new Caravan(0, "ABX-01", "Burro", 125, 2, 90)));
      log.info("Preloading " + repository.save(new Caravan(1, "ABX-02", "Formula1", 33, 33, 33)));*/
   /* };
  }*/
  
}
