package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.auth.services.UserService;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(Paths.Profile.Base)
public class ProfileEndpoint {

    @Autowired
    private UserService userService;
    @RequestMapping()
    public String getUser(Model model, Principal principal) {
        Optional<User> user = userService.findOne(principal.getName());
        user.ifPresent(value -> model.addAttribute("user", value));
        return "profile";
    }

}
