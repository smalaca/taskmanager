package org.smalaca.taskmanager.systemtests.rest;

import org.smalaca.taskmanager.systemtests.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

public class RestClient {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static String hostName() {
        return "http://localhost:8080";
    }

    public static List<UserDto> getAllUsers() {
        return restTemplate.getForObject(hostName() + "/user", List.class);
    }

    public static URI createUser(UserDto user) {
        return restTemplate.postForLocation(hostName() + "/user/", user, UserDto.class);
    }

    public static void updateUser(String userId, UserDto user) {
        restTemplate.put(hostName() + "/user/" + userId, user);
    }

    public static UserDto getUser(String userId) {
        return restTemplate.getForObject(hostName() + "/user/" + userId, UserDto.class);
    }

    public static HttpStatus getNonExistingUser(String userId) {
        try {
            restTemplate.getForEntity(hostName() + "/user/" + userId, UserDto.class);
        } catch (HttpStatusCodeException exception) {
            return exception.getStatusCode();
        }

        return OK;
    }

    public static void deleteUser(String userId) {
        restTemplate.delete(hostName() + "/user/" + userId);
    }
}