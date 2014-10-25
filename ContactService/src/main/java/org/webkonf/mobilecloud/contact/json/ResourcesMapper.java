package org.webkonf.mobilecloud.contact.json;

import java.io.IOException;

import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Begin long explanation of why this class was created...
 * 
 * 
 * By default, Spring Data Rest uses a format called HATEOAS (http://en.wikipedia.org/wiki/HATEOAS)
 * to output the data returned from a Repository. The results from findAll(), findByName(), etc. are
 * wrapped in an Object called Resources. When this Resources object is converted to JSON, it adds
 * additional fields to the JSON so that we don't just get back a list of Contact objects.
 * 
 * For our ContactRepository, the default output would like something like this for the /contact :
 * 
 * {
    "_links": {
        "search": {
            "href": "http://localhost:8080/contact/search"
        }
    },
    "_embedded": {
        "contacts": [
            {
                "name": "John Doe",
                "email": "john.doe@email.com",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/contact/1"
                    }
                }
            }
        ]
    }
}
 */
@SuppressWarnings("serial")
public class ResourcesMapper extends ObjectMapper {

	@SuppressWarnings("rawtypes")
	private JsonSerializer<Resources> serializer = new JsonSerializer<Resources>() {

		// We are going to register this class to handle all instances of type
		// Resources
		@Override
		public Class<Resources> handledType() {
			return Resources.class;
		}

		@Override
		public void serialize(Resources value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			Object content = value.getContent();
			JsonSerializer<Object> s = provider.findValueSerializer(
					content.getClass(), null);
			s.serialize(content, jgen, provider);
		}
	};
	
	// Create an ObjectMapper and tell it to use our customer serializer
	// to convert Resources objects into JSON
	public ResourcesMapper() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(serializer);
		registerModule(module);
	}

}
