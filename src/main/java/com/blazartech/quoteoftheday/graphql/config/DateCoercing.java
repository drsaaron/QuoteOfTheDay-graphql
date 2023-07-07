/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql.config;

import com.blazartech.products.services.date.DateServices;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
public class DateCoercing implements Coercing<Date, String> {

    @Autowired
    private DateServices dateServices;

    @Override
    public String serialize(Object o) {
        Date d = (Date) o;
        return dateServices.formatDate(d);
    }

    @Override
    public Date parseValue(Object o) {
        if (o != null) {
            String d = (String) o;
            return dateServices.parseDate(d);
        } else {
            return null;
        }
    }
    
    @Override
    public Date parseLiteral(Object o) {
        if (o != null) {
            StringValue sv = (StringValue) o;
            return parseValue(sv.getValue());
        } else {
            return null;
        }
    }
}
