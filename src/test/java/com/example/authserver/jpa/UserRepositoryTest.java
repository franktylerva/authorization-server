package com.example.authserver.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testPersistence() {

        var user = new User();
        user.setUsername("frank");
        user.setPassword("password");
        user.setEnabled(true);

        var role1 = new Role();
        role1.setName("role1");

        user.addRole( new Role("role1"));
        user.addGroup(new Group("group1"));

        userRepository.save(user);

        var found = userRepository.findById("frank").get();
        assertThat(found.getPassword()).isEqualTo("password");






    }


}