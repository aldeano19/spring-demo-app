package com.eriel.amex.demo.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/**
 * Configure the connection to a remote MongoDB instance.
 *
 * Remove comments to set up remote database connection.
 */
//@Configuration
//@EnableMongoRepositories(basePackages = "com.eriel.amex.demo.repository")
//public class MongoRemoteConfig extends AbstractMongoConfiguration {
//
//    private final String DATABASE_NAME = "eriel_amex_demo_database";
//
//    @Override
//    public MongoClient mongoClient() {
////        String dbHost = System.getenv("DB_HOST");
////        int dbPort = Integer.parseInt(System.getenv("DB_PORT"));
//
//        String dbHost = "35.**.**.153";
//        int dbPort = 27017;
//        return new MongoClient(dbHost, dbPort);
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return DATABASE_NAME;
//    }
//}
