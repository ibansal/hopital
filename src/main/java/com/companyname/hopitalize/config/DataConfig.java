package com.companyname.hopitalize.config;

import com.companyname.hopitalize.db.AeroSpikeDBManager;
import com.companyname.hopitalize.db.MongoDBManager;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@PropertySource(value = {"classpath:aerospike.properties","classpath:mongodb.properties"})
public class DataConfig {


    @Autowired
    Environment env;


    @Bean
    public AeroSpikeDBManager aeroSpikeDBManager() {
        String hostUrls = env.getProperty("aerospike.db.host", String.class);
        String[] split = hostUrls.split(",");
        List<String> list = Lists.newArrayList(split);
        AeroSpikeDBManager dbm = new AeroSpikeDBManager(list);
        return dbm;
    }

    @Bean
    public MongoDBManager mongoDBManager() {
        String hostUrl = env.getProperty("mongo.db.host", String.class);
        String port = env.getProperty("mongo.db.port", String.class);
        String dbName = env.getProperty("mongo.db.name", String.class);
        return (new MongoDBManager(hostUrl, dbName));
    }

}