package com.fdm.service;

import java.util.List;
import java.util.Optional;

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
	
	public Contact retrieveContact(Long contactId) throws ContactNotFoundException {
		Optional<Contact> contactOptional = contactRepo.findById(contactId);
		if(!contactOptional.isPresent()) {
			throw new ContactNotFoundException("Contact with id "+ contactId+"");
		}
		return contactOptional.get();
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
