package org.smalaca.taskmanager.systemtests.rest;

import org.smalaca.taskmanager.systemtests.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestClient {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String hostName() {
        return "http://localhost:8080";
    }

    public static List<UserDto> getAllUsers() {
        return restTemplate.getForObject(hostName() + "/user", List.class);
    }
}