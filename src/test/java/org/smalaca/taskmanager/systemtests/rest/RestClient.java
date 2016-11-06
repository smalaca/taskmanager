package org.smalaca.taskmanager.systemtests.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestClient {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String hostName() {
        return "http://localhost:8080";
    }

    public static ResponseEntity<List> getAllUsers() {
        return restTemplate.getForEntity(hostName() + "/user", List.class);
    }
}