/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql;

/**
 *
 * @author scott
 */
public class TestQuoteDataResponse {
    
    private int number;
    private boolean usable;
    private String text;
    private TestQuoteSourceCode sourceCode;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TestQuoteSourceCode getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(TestQuoteSourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }
    
    
}
