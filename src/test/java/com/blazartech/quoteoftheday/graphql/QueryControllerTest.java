/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql;

import com.blazartech.products.crypto.BlazarCryptoFile;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author scott
 */
@GraphQlTest(QueryController.class)
@Slf4j
//@Transactional(transactionManager = "txManager")
public class QueryControllerTest {
    
    @MockBean
    private BlazarCryptoFile cryptoFile;
    
    @Autowired 
    private GraphQlTester graphQlTester;
    
    public QueryControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    private static final String GET_QUOTE_QUERY = """
                                                  query($quoteNumber: Int!) {
                                                    getQuote(number: $quoteNumber) {
                                                      number
                                                      usable
                                                      text
                                                      sourceCode {
                                                        number
                                                        text
                                                      }
                                                    }
                                                  }""";
    
    /**
     * Test of getQuote method, of class QueryController.
     */
    @Test
    @Sql("/getQuote.sql")
    public void testGetQuote() {
        log.info("getQuote");
        
        int number = 25;
        
        TestQuoteDataResponse result = graphQlTester
                .document(GET_QUOTE_QUERY)
                .variable("quoteNumber", number)
                .execute()
                .path("getQuote")
                .entity(TestQuoteDataResponse.class)
                .get();
        
        assertEquals(number, result.getNumber());
        assertTrue(result.isUsable());
    }

    
}
