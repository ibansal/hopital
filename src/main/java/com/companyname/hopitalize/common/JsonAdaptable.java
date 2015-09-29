package com.companyname.hopitalize.common;

import java.io.Serializable;

import org.json.simple.JSONObject;

public interface JsonAdaptable extends Serializable {

    public JSONObject toJsonObject();

    public void fromJsonObject(JSONObject jsonObject);

}
