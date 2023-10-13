/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql.config;

import com.blazartech.products.services.date.DateServices;
import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import java.util.Date;
import java.util.Locale;
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
    public String serialize(Object o, GraphQLContext graphQLContext, Locale locale) {
        Date d = (Date) o;
        return dateServices.formatDate(d);
    }

    @Override
    public Date parseValue(Object o, GraphQLContext graphQLContext, Locale locale) {
        if (o != null) {
            String d = (String) o;
            return dateServices.parseDate(d);
        } else {
            return null;
        }
    }

    @Override
    public Date parseLiteral(Value<?> o, CoercedVariables variables, GraphQLContext graphQLContext, Locale locale) throws CoercingParseLiteralException {
         if (o != null) {
            StringValue sv = (StringValue) o;
            return parseValue(sv.getValue(), graphQLContext, locale);
        } else {
            return null;
        }
    }
}
