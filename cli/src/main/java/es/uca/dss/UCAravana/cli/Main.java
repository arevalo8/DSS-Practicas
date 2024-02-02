package es.uca.dss.UCAravana.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {


	public static void main(String[] args) { 
		/*ApplicationContext context = */
		SpringApplication.run(Main.class, args); 
		//MailNotifier mailNotifier = context.getBean(MailNotifier.class);
		//BookingDao bd = new BookingDao();
		//BookingManager bm = new BookingManager(bd/*, mailNotifier*/);
		/*CustomerDao customerDao = new CustomerDao();
		CustomerManager cm = new CustomerManager(customerDao);
		CaravanDao caravanDao = new CaravanDao();
		CaravanManager caravanManager = new CaravanManager(caravanDao);*/
		
		//ManagingCommands mg = new ManagingCommands(caravanManager, cm, bm);
		
	}
	

}
    // Obtiene una instancia de MailNotifier a través del contexto de Spring
    //MailNotifier mailNotifier = context.getBean(MailNotifier.class);

    //BookingDao bd = new BookingDao();
	 

	    	
	    	/*List<String> pho = new ArrayList<String>();
	    	pho.add("685214741");
	    	pho.add("856214523");
	    	Customer c1 = new Customer(1, "Juan", "Álvarez", "Bermejo", "74562587t", pho, "juan.alvarez@gmail.com", "B96");
	    	List<String> pho2= new ArrayList<String>();
	    	pho2.add("685962457");
	    	pho2.add("639741852");
	    	Customer c2 = new Customer(1, "Pedro", "Mata", "García", "54789621p", pho2, "pedro.mata@gmail.com@gmail.com", "B96");
	    	
	    	
	    	File file = new File("./pruebaCustomers2.json");
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	objectMapper.writeValue(file, c2);*/
	    	//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	    	
	    	/*
	    	List<Customer> customers = new ArrayList<Customer>();
	    	customers.add(c1);
	    	customers.add(c2);
	    	
	    	File file = new File("./mycustomer.json");
	    	ObjectMapper mapper = new ObjectMapper();
	    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
	    	
	    	try {
	    		if(file.exists()) {
	    			rootNode = mapper.readTree(file);
	    		} else {
	    			rootNode = mapper.createObjectNode();
	    		}
	    		
	    		for(Customer customer : customers) {
	    			JsonNode CustomerNode = mapper.valueToTree(Customer);
	    			
	    			((ObjectNode)) rootNode.set(customer.getIdCustomer().toString(), CustomerNode);
	    			
	    			mapper.writeValue(file, rootNode);
	    		}
	    		
	    	} catch (IOException e) {
	    		throw RuntimeException("Error al guarfar objetos", e);
	    	}*/
	    	
	    	
	    
	 

