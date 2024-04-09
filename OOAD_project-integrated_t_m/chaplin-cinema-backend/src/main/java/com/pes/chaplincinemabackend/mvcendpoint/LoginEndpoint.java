package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping(Paths.Login.Base)
public class LoginEndpoint {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping()
    public String login(Model model, Principal principal) {
        return "login";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String loginPost(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model,
                            Principal principal) {

        System.out.println("Called post on login");
        model.addAttribute("principal", principal.getName());
        return "index";
    }
}
