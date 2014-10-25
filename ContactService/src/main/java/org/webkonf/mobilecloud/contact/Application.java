package org.webkonf.mobilecloud.contact;

import org.webkonf.mobilecloud.contact.json.ResourcesMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration
@EnableDynamoDBRepositories
@EnableWebMvc
@ComponentScan
@Configuration
public class Application extends RepositoryRestMvcConfiguration {


	// Tell Spring to launch our app!
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public ObjectMapper halObjectMapper(){
		return new ResourcesMapper();
	}
	
    @Value("${AWS_ACCESS_KEY_ID}")
    private String amazonAWSAccessKey;

    @Value("${AWS_SECRET_KEY}")
    private String amazonAWSSecretKey;

  
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(
                amazonAWSCredentials());
        
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

}
