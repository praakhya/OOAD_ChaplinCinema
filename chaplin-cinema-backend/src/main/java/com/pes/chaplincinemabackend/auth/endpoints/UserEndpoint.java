package com.pes.chaplincinemabackend.auth.endpoints;

import com.pes.chaplincinemabackend.auth.entities.UserEntity;
import com.pes.chaplincinemabackend.auth.services.UserService;
import com.pes.chaplincinemabackend.auth.utils.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Paths.V1.Users.fullPath)
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> findAllUsers() {
        return userService.findAll();
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<UserEntity> postUser(@RequestBody UserEntity userEntity) {
        return userService.save(userEntity);
    }
    @RequestMapping(value = Paths.V1.Users.GetOne, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Optional<UserEntity> findUserByUsername(@PathVariable(Paths.V1.Users.GetOnePathVariable) String username) {
        return userService.findOne(username);
    }
}