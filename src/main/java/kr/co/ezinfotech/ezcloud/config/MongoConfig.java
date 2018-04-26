package kr.co.ezinfotech.ezcloud.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{

	@Value("${spring.data.mongodb.username}")
	private String userName;
	
	@Value("${spring.data.mongodb.password}")
	private String password;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public Mongo mongo() throws Exception {
		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		return new MongoClient(new ServerAddress("192.168.66.30", 27017), Arrays.asList(credential));
	}
	
	public @Bean MongoTemplate mongoTemplate() throws Exception{ 
		return new MongoTemplate(mongo(), database);
	}
	
}
