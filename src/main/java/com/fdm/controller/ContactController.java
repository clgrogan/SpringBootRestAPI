package com.fdm.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdm.model.Contact;
import com.fdm.service.ContactService;

@RestController // Combines @Controller and @RequestBody (for methods)
@RequestMapping("api/v1/contacts")
public class ContactController {
	private ContactService contactService;

//	@Autowired // Redundant
	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	@GetMapping
	public ResponseEntity<List<Contact>> getContacts(){
		List<Contact> retrievedContacts = contactService.retrieveContacts();
		return ResponseEntity.ok(retrievedContacts);
	}
	
	@GetMapping("/{id}") ResponseEntity<Contact> getContact(@PathVariable("id") long contactId){
		Contact contact = contactService.retrieveContact(contactId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(location).body(contact);
	}
	@PostMapping
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
		Contact createdContact = contactService.createContact(contact);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id")
				.buildAndExpand(createdContact.getContactId()).toUri();
		return ResponseEntity.created(location).body(createdContact);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") long contactId, @RequestBody Contact contact){
		Contact updatedContact = contactService.updateContact(contact);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable("id") long contactId) {
		contactService.deleteContact(contactId);
	}
}
