package com.pes.chaplincinemabackend;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.pes.chaplincinemabackend.auth.factories.IdentityPasswordEncoderFactories;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@SpringBootApplication(scanBasePackages = {"com.pes.chaplincinemabackend"})
@EnableMongoRepositories
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class ChaplinCinemaBackendApplication {

	@Autowired
	private MongoTemplate mongoTemplate;
	public static void main(String[] args) {
		SpringApplication.run(ChaplinCinemaBackendApplication.class, args);
	}
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = IdentityPasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}
/*	@PostConstruct
	public void postConstruct() throws ParseException {

		MongoCollection<Document> collection = mongoTemplate.getCollection("movie");
		MongoCursor<Document> cursor = collection.find().cursor();
		while(cursor.hasNext()) {
			Document d = cursor.next();
			Document key = new Document("_id", d.get("_id"));
			Object dateStr = d.get("release_date");
			if (dateStr instanceof String) {
				if (dateStr != null && !((String) dateStr).isEmpty()) {

					String format = "YYYY-MM-dd";
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					d.put("release_date", sdf.parse(dateStr.toString()));
				}
				else {
					d.remove("release_date");
				}
				collection.replaceOne(key, d);
			}
		}
	}*/
}
