package com.riwi.eventsvenues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EventsVenuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsVenuesApplication.class, args);
    }

}
