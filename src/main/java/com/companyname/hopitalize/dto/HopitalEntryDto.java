package com.companyname.hopitalize.dto;


import com.companyname.hopitalize.common.JsonAdaptable;
import org.json.simple.JSONObject;

import javax.validation.constraints.NotNull;

public class HopitalEntryDto implements JsonAdaptable{

    @NotNull(message = "User id cannot be empty")
    private String userId;

    @NotNull(message = "Advertiser id cannot be empty")
    private String hopitalId;

    @NotNull(message = "Name cannot be empty")
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHopitalId() {
        return hopitalId;
    }

    public void setHopitalId(String hopitalId) {
        this.hopitalId = hopitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("hopitalId",hopitalId);
        jsonObject.put("userId",userId);
        return jsonObject;
    }

    @Override
    public void fromJsonObject(JSONObject jsonObject) {

    }

    @Override
    public String toString() {
        return "HopitalEntryDto{" +
                "userId='" + userId + '\'' +
                ", hopitalId='" + hopitalId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
