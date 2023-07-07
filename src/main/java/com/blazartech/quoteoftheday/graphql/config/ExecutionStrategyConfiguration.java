/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.quoteoftheday.graphql.config;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
@Configuration
public class ExecutionStrategyConfiguration {
    
    @Bean
    public ExecutionStrategy queryExecutionStrategy() {
        AsyncExecutionStrategy s = new AsyncExecutionStrategy();
        return s;
    }
}
