package com.example.authserver.api;

import com.example.authserver.jpa.User;
import com.example.authserver.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserResponse> users() {
        return userRepository.findAll().stream().map(user -> {
            return new UserResponse(user.getId(), user.getUsername(), user.isEnabled(), user.getPassword());
        }).toList();
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUser(@PathVariable String username, @RequestBody UserRequest userRequest) {
        var newUser = userRepository.save(userRequest.user());
        var location = UriComponentsBuilder.fromPath("/api/users/{username}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public UserResponse findUser(@PathVariable UUID id) {

        var found = userRepository.findById(id);

        if(found.isPresent()) {
            var u = found.get();
            return new UserResponse(u.getId(), u.getUsername(), u.isEnabled(), u.getPassword());
        }
        throw new UserNotFoundException(id.toString());
    }

    record UserResponse(UUID id, String username, boolean enabled, String password){};

    record UserRequest(User user){};

}
