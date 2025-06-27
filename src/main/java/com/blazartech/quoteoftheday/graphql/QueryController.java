/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql;

import com.blazartech.products.qotdp.data.Quote;
import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.data.QuoteOfTheDayHistory;
import com.blazartech.products.qotdp.data.QuoteSourceCode;
import com.blazartech.products.qotdp.data.access.QuoteOfTheDayDAL;
import com.blazartech.products.qotdp.process.GetQuoteOfTheDayPAB;
import com.blazartech.quoteoftheday.graphql.data.QuoteOfTheDayHistoryForYear;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

/**
 *
 * @author scott
 */
@Controller
public class QueryController {
    
    private static final Logger log = LoggerFactory.getLogger(QueryController.class);
    
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
    public QuoteOfTheDay getQuoteOfTheDay(@Argument LocalDate runDate) {
        if (runDate == null) {
            log.info("getting quote of the day for today");
            return qotdPAB.getQuoteOfTheDay();
        } else {
            log.info("getting quote of the day for {}", runDate);
            return qotdPAB.getQuoteOfTheDay(runDate);
        }
    }
    
    @SchemaMapping
    @Async
    public CompletableFuture<QuoteSourceCode> sourceCode(Quote q) {
        log.info("getting source code for quote {}", q.getNumber());
        int sc = q.getSourceCode();
        return CompletableFuture.completedFuture(dal.getQuoteSourceCode(sc));
    }
    
    @SchemaMapping
    @Async
    public CompletableFuture<Quote> quote(QuoteOfTheDay qotd) {
        log.info("getting quote for QOTD {}", qotd.getNumber());
        return CompletableFuture.completedFuture(dal.getQuote(qotd.getQuoteNumber()));
    }
    
    @SchemaMapping
    @Async
    @Transactional
    public CompletableFuture<List<QuoteOfTheDayHistoryForYear>> quoteOfTheDayHistory(Quote q) {
        log.info("getting QOTD history for quote {}", q.getNumber());
        QuoteOfTheDayHistory history = dal.getQuoteOfTheDayHistoryForQuote(q.getNumber());

        List<QuoteOfTheDayHistoryForYear> historyForYear = history.getHistoryByYear().keySet().stream()
                .sorted((y1, y2) -> Integer.compare(y1, y2))
                .map(y -> new QuoteOfTheDayHistoryForYear(y, history.getHistoryByYear().get(y)))
                .collect(Collectors.toList());
        
        return CompletableFuture.completedFuture(historyForYear);
    }
    
    @SchemaMapping 
    @Async
    public CompletableFuture<Collection<QuoteOfTheDay>> quotesOfTheDay(QuoteOfTheDayHistoryForYear hry) {
        return CompletableFuture.completedFuture(hry.getQotdHistory());
    }
}
