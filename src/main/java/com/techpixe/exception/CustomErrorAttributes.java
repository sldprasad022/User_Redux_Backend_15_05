package com.techpixe.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

//    @Override
//    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) 
//    {
//        // Get the default error attributes
//        Map<String, Object> defaultAttributes = super.getErrorAttributes(webRequest, options);
//
//        // Remove unwanted fields
//        defaultAttributes.remove("timestamp");
//        defaultAttributes.remove("path");
//        defaultAttributes.remove("trace");
//
//        return defaultAttributes;
//    }
}
