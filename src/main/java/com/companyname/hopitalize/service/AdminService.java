package com.companyname.hopitalize.service;

import com.companyname.hopitalize.common.CommonConstants;
import com.companyname.hopitalize.db.MongoDBManager;
import com.companyname.hopitalize.dto.HopitalEntryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ishan.bansal on 9/29/15.
 */
@Service
public class AdminService {

    @Autowired
    MongoDBManager mongoDBManager;

    public void saveHopitalData(HopitalEntryDto hopitalEntryDto) {
        if (hopitalEntryDto == null) {
            return;
        }
        mongoDBManager.addObject(CommonConstants.MONGO_COLLECTION_NAME, hopitalEntryDto.toJsonObject().toJSONString());
    }
}
