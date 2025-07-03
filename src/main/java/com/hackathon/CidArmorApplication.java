package com.hackathon;

//import com.hackathon.dto.xml.CustomersXml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;

@SpringBootApplication
public class CidArmorApplication {

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(CidArmorApplication.class, args);

		/*JAXBContext context = JAXBContext.newInstance(CustomersXml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		File xmlFile = new File("customers.xml"); // your XML file path
		CustomersXml customers = (CustomersXml) unmarshaller.unmarshal(xmlFile);

		customers.getCustomer().forEach(c -> {
			System.out.println("Name: " + c.getName());
			System.out.println("Account: " + c.getAccountNumber());
			System.out.println("Amount: " + c.getTransactionAmount());
			System.out.println("Date: " + c.getTransactionDate());
			System.out.println("-----");
		});*/
	}

}
