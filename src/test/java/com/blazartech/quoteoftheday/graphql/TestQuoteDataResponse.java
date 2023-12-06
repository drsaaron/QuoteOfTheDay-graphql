/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql;

import lombok.Data;

/**
 *
 * @author scott
 */
@Data
public class TestQuoteDataResponse {
    
    private int number;
    private boolean usable;
    private String text;
    private TestQuoteSourceCode sourceCode;
}
