package com.eriel.amex.demo.configuration;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


import java.io.IOException;

@Configuration
public class MongoInmemoryConfig {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "eriel_amex_demo_database";

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(DB_HOST);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, DB_NAME);
        return mongoTemplate;
    }
}
