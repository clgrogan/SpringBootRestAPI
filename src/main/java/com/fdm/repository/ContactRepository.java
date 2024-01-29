package com.fdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
