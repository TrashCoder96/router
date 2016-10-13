package com.router.config;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
        return new MongoClient(getServiceURI());//new MongoClientURI("mongodb://" + mongoDBLogin + ":" + mongoDBPassword + "@" + mongoHost + ":" + mongoPort + "/" + mongoDB));
    }

    public String getServiceURI() throws Exception {
        CloudEnvironment environment = new CloudEnvironment();
        if ( environment.getServiceDataByLabels("mongodb-2.4").size() == 0 ) {
            throw new Exception( "No MongoDB service is bound to this app!!" );
        }
        Map credential = (Map)((Map)environment.getServiceDataByLabels("mongodb-2.4").get(0)).get( "credentials" );
        return (String)credential.get( "url" );
    }

}
