package com.companyname.hopitalize.controller;

import com.companyname.hopitalize.annotation.ReadOnly;
import com.companyname.hopitalize.annotation.Update;
import com.companyname.hopitalize.dto.HopitalEntryDto;
import com.companyname.hopitalize.dto.RestResponse;
import com.companyname.hopitalize.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ishan.bansal on 6/24/15.
 */

@RestController
@RequestMapping("/v1/hopitalize")
public class AdminController {
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @ReadOnly
    @RequestMapping("status")
    @ResponseBody
    public ResponseEntity<RestResponse<String>> testApi() {
        RestResponse<String> restResponse = getRestResponse("Testing api.. It works!!!", true, null);
        ResponseEntity<RestResponse<String>> response = getOkRestResponseResponseEntity(restResponse);
        return response;
    }

    @Update
    @RequestMapping(value = "create/entry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
//    @RolesAllowed(values = {RoleType.ADMIN, RoleType.FINANCE, RoleType.SELLER})
    public ResponseEntity<RestResponse<String>> createAccount(@Valid @RequestBody HopitalEntryDto hopitalEntryDto,BindingResult bindingResult) throws Exception {
        System.out.println("DTO received ");
        System.out.println(hopitalEntryDto.toString());
        if (bindingResult.hasErrors()) {
            RestResponse<String> restResponse = getRestResponse(null, false, getErrors(bindingResult.getFieldErrors()));
            return new ResponseEntity<>(restResponse, HttpStatus.NOT_ACCEPTABLE);
        }
        adminService.saveHopitalData(hopitalEntryDto);
        RestResponse<String> restResponse = getRestResponse("Data saved successfully", true, null);
        ResponseEntity<RestResponse<String>> response = getOkRestResponseResponseEntity(restResponse);
        return response;
    }
    private <T> ResponseEntity<RestResponse<T>> getOkRestResponseResponseEntity(RestResponse<T> restResponse) {
        return new ResponseEntity<>(restResponse, HttpStatus.OK);
    }

    private <T> RestResponse<T> getRestResponse(T data, boolean status, String errorMessage) {
        return new RestResponse<>(data, status, errorMessage);
    }

    private <T> RestResponse<T> getRestResponse(T data, boolean status, String errorMessage, int total) {
        return new RestResponse<>(data, status, errorMessage, total);
    }

    private String getErrors(List<FieldError> fieldErrors) {
        StringBuffer buffer = new StringBuffer();
        String prefix = "";
        for (FieldError error : fieldErrors) {
            buffer.append(prefix);
            prefix = " , ";
            buffer.append(error.getDefaultMessage());
        }
        return buffer.toString();
    }
}
