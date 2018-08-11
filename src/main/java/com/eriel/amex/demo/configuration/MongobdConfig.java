package com.eriel.amex.demo.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.eriel.amex.demo.repository")
public class MongobdConfig extends AbstractMongoConfiguration {

    private final String DATABASE_NAME = "eriel_amex_demo_database";

    @Override
    public MongoClient mongoClient() {
//        String dbHost = System.getenv("DB_HOST");
//        int dbPort = Integer.parseInt(System.getenv("DB_PORT"));

        String dbHost = "35.231.186.153";
        int dbPort = 27017;
        return new MongoClient(dbHost, dbPort);
    }

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }
}
