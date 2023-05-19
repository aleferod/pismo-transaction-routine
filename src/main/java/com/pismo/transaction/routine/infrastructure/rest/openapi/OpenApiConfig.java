package com.pismo.transaction.routine.infrastructure.rest.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI transactionRoutineOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Transaction Routine")
                        .description("Pismo Transaction Routine API ")
                        .version("1.0"));
    }

}
