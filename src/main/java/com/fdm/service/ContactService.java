package com.fdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdm.model.Contact;
import com.fdm.repository.ContactRepository;

@Service
public class ContactService {
	
	private ContactRepository contactRepo;

//	@Autowired // redundant for all component types
	public ContactService(ContactRepository contactRepo) {
		super();
		this.contactRepo = contactRepo;
	}

	public List<Contact> retrieveContacts(){
		return contactRepo.findAll();
	}
	
	public Contact retrieveContact(Long id) {
		return contactRepo.findById(id).get(); //findById returns optional. use get method on optional
	}
	
	public Contact createContact(Contact contact) {
		return contactRepo.save(contact);
	}
	
	public Contact updateContact(Contact contact) {
		return contactRepo.save(contact);
	}
	
	public void deleteContact(long contactId) {
		contactRepo.deleteById(contactId);
	}
}
