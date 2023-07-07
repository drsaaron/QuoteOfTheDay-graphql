/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql.data;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author scott
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteOfTheDayHistoryForYear {
    
    private int year;
    private Collection<QuoteOfTheDay> qotdHistory;
}
