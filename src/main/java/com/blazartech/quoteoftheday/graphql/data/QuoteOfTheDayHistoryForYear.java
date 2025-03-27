/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql.data;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import java.util.Collection;

/**
 *
 * @author scott
 */
public class QuoteOfTheDayHistoryForYear {

    public QuoteOfTheDayHistoryForYear() {
    }

    public QuoteOfTheDayHistoryForYear(int year, Collection<QuoteOfTheDay> qotdHistory) {
        this.year = year;
        this.qotdHistory = qotdHistory;
    }
    
    private int year;
    private Collection<QuoteOfTheDay> qotdHistory;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Collection<QuoteOfTheDay> getQotdHistory() {
        return qotdHistory;
    }

    public void setQotdHistory(Collection<QuoteOfTheDay> qotdHistory) {
        this.qotdHistory = qotdHistory;
    }
    
    
}
