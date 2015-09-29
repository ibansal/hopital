package com.companyname.hopitalize.annotation;

import com.companyname.hopitalize.common.RoleType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RolesAllowed {
    RoleType[] values();
}