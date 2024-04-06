package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.auth.services.UserService;
import com.pes.chaplincinemabackend.common.exceptions.EntityDoesNotExistException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.services.CustomerService;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping()
    public String getUser(Model model, Principal principal) {
        Optional<User> user = userService.findOne(principal.getName());
        user.ifPresent(value -> model.addAttribute("user", value));
        return "profile";
    }
    @RequestMapping("/edit")
    public String editUser(Model model, Principal principal) {
        Optional<User> user = userService.findOne(principal.getName());
        user.ifPresent(value -> model.addAttribute("user", value));
        return "profileEdit";
    }
    @RequestMapping("/submitcustomer")
    public String submitEdit(@ModelAttribute Customer customer,
                             Model model,
                             Principal principal) {
        Customer storedCustomer = customerService.findByID(customer.getId()).orElseThrow(() -> new EntityDoesNotExistException(String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getReason(), customer.getId()),
                String.format(ExceptionMessage.ENTITY_DOES_NOT_EXIST.getError(), customer.getId())));
        storedCustomer.setFirstName(customer.getFirstName());
        storedCustomer.setLastName(customer.getLastName());
        if (customer.getPassword() != null && !customer.getPassword().isBlank()) {
            storedCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }
        storedCustomer = customerService.update(storedCustomer).get();
        model.addAttribute("user", customerService.update(storedCustomer).get());
        return "profile";
    }
}
