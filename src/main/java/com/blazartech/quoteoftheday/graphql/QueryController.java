/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql;

import com.blazartech.products.qotdp.data.Quote;
import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.data.QuoteSourceCode;
import com.blazartech.products.qotdp.data.access.QuoteOfTheDayDAL;
import com.blazartech.products.qotdp.process.GetQuoteOfTheDayPAB;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author scott
 */
@Controller
@Slf4j
public class QueryController {
    
    @Autowired
    private QuoteOfTheDayDAL dal;
    
    @Autowired
    private GetQuoteOfTheDayPAB qotdPAB;
    
    @QueryMapping
    public Quote getQuote(@Argument int number) {
        log.info("getting quote {}", number);
        
        Quote q = dal.getQuote(number);
        return q;
    }
    
    @QueryMapping
    public QuoteOfTheDay getQuoteOfTheDay(@Argument Date runDate) {
        if (runDate == null) {
            log.info("getting quote of the day for today");
            return qotdPAB.getQuoteOfTheDay();
        } else {
            log.info("getting quote of the day for {}", runDate);
            return qotdPAB.getQuoteOfTheDay(runDate);
        }
    }
    
    @SchemaMapping
    public QuoteSourceCode sourceCode(Quote q) {
        log.info("getting source code for quote {}", q.getNumber());
        int sc = q.getSourceCode();
        return dal.getQuoteSourceCode(sc);
    }
    
    @SchemaMapping
    public Quote quote(QuoteOfTheDay qotd) {
        log.info("getting quote for QOTD {}", qotd.getNumber());
        return dal.getQuote(qotd.getQuoteNumber());
    }
}
