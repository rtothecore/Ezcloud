package kr.co.ezinfotech.ezcloud.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{

	@Value("${spring.data.mongodb.username}")
	private String userName;
	
	@Value("${spring.data.mongodb.password}")
	private String password;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public Mongo mongo() throws Exception {
		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		MongoClientOptions builder = MongoClientOptions.builder().writeConcern(WriteConcern.UNACKNOWLEDGED).build();
		return new MongoClient(new ServerAddress(host, 27017), Arrays.asList(credential), builder);
		// return new MongoClient(new ServerAddress(host, 27017), Arrays.asList(credential));
	}
	
	public @Bean MongoTemplate mongoTemplate() throws Exception{ 
		return new MongoTemplate(mongo(), database);
	}
	
}
