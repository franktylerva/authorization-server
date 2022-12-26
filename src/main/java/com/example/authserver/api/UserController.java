package com.example.authserver.api;

import com.example.authserver.jpa.User;
import com.example.authserver.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserResponse> users() {
        return userRepository.findAll().stream().map(user -> {
            return new UserResponse(user.getUsername(), user.isEnabled());
        }).toList();
    }

    @PostMapping("/{username}")
    public ResponseEntity<Object> createUser(@PathVariable String username, @RequestBody UserRequest userRequest) {
        userRepository.save(userRequest.user());
        var location = UriComponentsBuilder.fromPath("/api/users/{username}")
                .buildAndExpand(username).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{username}")
    public UserResponse findUser(@PathVariable String username) {

        var found = userRepository.findById(username);

        if(found.isPresent()) {
            var u = found.get();
            return new UserResponse(u.getUsername(), u.isEnabled());
        }
        throw new UserNotFoundException(username);
    }

    record UserResponse(String username, boolean enabled){};

    record UserRequest(User user){};

}
