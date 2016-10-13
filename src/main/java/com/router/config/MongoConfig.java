package com.router.config;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus-pc on 29.09.2016.
 */

@Configuration
@EnableMongoRepositories("com.router.data")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.mongodb.host}")
    private String mongoHost;

    @Value("${spring.mongodb.port}")
    private String mongoPort;

    @Value("${spring.mongodb.database}")
    private String mongoDB;

    @Value("${spring.mongodb.login}")
    private String mongoDBLogin;

    @Value("${spring.mongodb.password}")
    private String mongoDBPassword;

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    public Mongo mongo() throws Exception {
        //ServerAddress serverAddress = new ServerAddress(mongoHost, Integer.valueOf(mongoPort));
        //MongoCredential auth = MongoCredential.createPlainCredential(mongoDBLogin, mongoDB, mongoDBPassword.toCharArray());
        //List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
        //credentialList.add(auth);
        //return new MongoClient(serverAddress, credentialList, new MongoClientOptions.Builder().connectTimeout(300000).socketTimeout(300000).build());
        return new MongoClient(getServiceURI());
    }

    public String getServiceURI() throws Exception {
        CloudEnvironment environment = new CloudEnvironment();
        Map credential = (Map)((Map)environment.getServiceDataByName("compose-for-mongodb")).get("credentials");
        return (String)credential.get("uri");
    }

}
