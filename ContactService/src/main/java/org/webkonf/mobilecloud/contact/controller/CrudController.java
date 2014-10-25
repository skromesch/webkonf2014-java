package org.webkonf.mobilecloud.contact.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webkonf.mobilecloud.contact.repository.Contact;

/**
 * An interface for a repository that can store Contact
 * objects and allow them to be searched by title.
 * 
 * @author skromesch
 *
 */
// 1. List all contacts by sending a GET request to /contact 
// 2. Add a contact by sending a POST request to /contact with the JSON for a contact
// 3. Get a specific contact by sending a GET request to /contact/{contactId}
//    (e.g., /contact/1 would return the JSON for the contact with id=1)
// 4. Send search requests to our findByXYZ methods to /contact/search/findByXYZ
//    (e.g., /contact/search/findByName?name=Foo)
//
@Controller
public class CrudController {
	@RequestMapping(value="/crud/contact",method=RequestMethod.GET)
	public @ResponseBody Collection<Contact> getContactList() {
		return contacts.values();
	}

	@RequestMapping(value="/crud/contact",method=RequestMethod.POST)
	public @ResponseBody Contact 
		addContact(
				@RequestBody Contact v) {
		save(v);
		return v;
	}

	@RequestMapping(value="/crud/contact/{id}",method=RequestMethod.GET)	
	public @ResponseBody Contact getData(@PathVariable("id") String id,HttpServletResponse response) {
		Contact v = contacts.get(id);
		if(v == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return v;
	}

	@RequestMapping(value="/crud/contact/{id}",method=RequestMethod.DELETE)	
	public void deleteData(@PathVariable("id") String id,HttpServletResponse response) {
		Contact v = contacts.get(id);
		if(v == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		contacts.remove(v.getId());
		return;
	}
	
	private static final AtomicLong currentId = new AtomicLong(0L);

	private Map<String,Contact> contacts = new HashMap<String, Contact>();

	public Contact save(Contact entity) {
		checkAndSetId(entity);
		contacts.put(entity.getId(), entity);
		return entity;
	}

	private void checkAndSetId(Contact entity) {
		if(entity.getId() == null){
			entity.setId(String.valueOf(currentId.incrementAndGet()));
		}
	}
	
	
}
