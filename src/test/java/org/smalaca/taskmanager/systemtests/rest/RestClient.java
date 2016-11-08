package org.smalaca.taskmanager.systemtests.rest;

import org.smalaca.taskmanager.systemtests.dto.UserDto;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class RestClient {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String hostName() {
        return "http://localhost:8080";
    }

    public static List<UserDto> getAllUsers() {
        return restTemplate.getForObject(hostName() + "/user", List.class);
    }

    public static URI createAUser(UserDto user) {
        return restTemplate.postForLocation(hostName() + "/user/", user, UserDto.class);
    }
}