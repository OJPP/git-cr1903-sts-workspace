package com.apress.prospring3.ch16.restful;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.apress.prospring3.ch16.domain.Contact;
import com.apress.prospring3.ch16.domain.Contacts;

public class RestfulClientSample {

	private static final String URL_GET_ALL_CONTACTS = "http://localhost:8080/prospring3/restful/contact/listdata";
	private static final String URL_GET_CONTACT_BY_ID = "http://localhost:8080/prospring3/restful/contact/{id}";
	private static final String URL_CREATE_CONTACT = "http://localhost:8080/prospring3/restful/contact/";
	private static final String URL_UPDATE_CONTACT = "http://localhost:8080/prospring3/restful/contact/{id}";
	private static final String URL_DELETE_CONTACT = "http://localhost:8080/prospring3/restful/contact/{id}";

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:restful-client-app-context.xml");
		ctx.refresh();

		Contacts contacts;
		Contact contact;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
		System.out.println("");

		// Testing create contact
		System.out.println("Testing create contact :");
		Contact contactNew = new Contact();
		contactNew.setFirstName("JJJ");
		contactNew.setLastName("Gosling");
		contactNew.setBirthDate(new DateTime());
		contactNew = restTemplate.postForObject(URL_CREATE_CONTACT, contactNew, Contact.class);
		System.out.println("Contact created successfully: " + contactNew);

		long newContactId = contactNew.getId(); 

		// Test retrieve contact by id
		System.out.println("Testing retrieve a contact by id : " + newContactId);
 		contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, newContactId);
		System.out.println(contact);
		System.out.println("");

		// Test update contact
		contact = restTemplate.getForObject(URL_UPDATE_CONTACT, Contact.class, newContactId);
		contact.setFirstName("Jim Fung");
		System.out.println("Testing update contact by id :" + newContactId);
		restTemplate.put(URL_UPDATE_CONTACT, contact, 1);
		System.out.println("Contact update successfully: " + contact);
		System.out.println("");

		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
		System.out.println("");

		// Testing delete contact
		
		newContactId -= 1;
		
		restTemplate.delete(URL_DELETE_CONTACT, newContactId);
		System.out.println("Testing delete contact by id :" + (newContactId));
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);

		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
		System.out.println("");

		ctx.close();
	}

	private static void listContacts(Contacts contacts) {

		for (Contact contact : contacts.getContacts()) {
			System.out.println(contact);
		}

		System.out.println("");

	}

}
