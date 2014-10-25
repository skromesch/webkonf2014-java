package org.webkonf.mobilecloud.contact.repository;

import java.util.Collection;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * An interface for a repository that can store Contact
 * objects and allow them to be searched by title.
 * 
 */
// This @RepositoryRestResource annotation tells Spring Data Rest to
// expose the ContactRepository through a controller and map it to the 
// "/contact" path. This automatically enables you to do the following:
//
// 1. List all contacts by sending a GET request to /contact 
// 2. Add a contact by sending a POST request to /contact with the JSON for a contact
// 3. Get a specific contact by sending a GET request to /contact/{contactId}
//    (e.g., /contact/1 would return the JSON for the contact with id=1)
// 4. Send search requests to our findByXYZ methods to /contact/search/findByXYZ
//    (e.g., /contact/search/findByName?name=Foo)
//
@EnableScan
@RepositoryRestResource(path = ContactRepository.CONTACT_SVC_PATH)
public interface ContactRepository extends CrudRepository<Contact, Long>{
	
	public static final String NAME_PARAMETER = "name";
	public static final String EMAIL_PARAMETER = "email";
	public static final String AGE_PARAMETER = "age";
	
	public static final String CONTACT_SVC_PATH = "/contact";

	public static final String CONTACT_NAME_SEARCH_PATH = CONTACT_SVC_PATH + "/search/findByName";

	public Collection<Contact> findByName(
			@Param(ContactRepository.NAME_PARAMETER) String name);
	
	public Collection<Contact> findByEmail(
			@Param(ContactRepository.EMAIL_PARAMETER) String email);

	public Collection<Contact> findByAgeLessThan(
			@Param(ContactRepository.AGE_PARAMETER) int maxage);
	
	/*
	 * See: http://docs.spring.io/spring-data/jpa/docs/1.3.0.RELEASE/reference/html/jpa.repositories.html 
	 * for more examples of writing query methods
	 */
	
}
