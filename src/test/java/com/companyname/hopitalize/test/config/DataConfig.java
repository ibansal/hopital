package com.companyname.hopitalize.test.config;

import com.companyname.hopitalize.db.AeroSpikeDBManager;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@PropertySource(value = {"classpath:database.properties","classpath:aerospike.properties"})
public class DataConfig {

    private static final String PACKAGES_TO_SCAN = "com.companyname.hopitalize.domain";
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
}