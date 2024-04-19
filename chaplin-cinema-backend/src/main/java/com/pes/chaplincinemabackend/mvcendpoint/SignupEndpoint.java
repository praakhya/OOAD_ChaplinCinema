package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionMessage;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.repositories.AdminRepository;
import com.pes.chaplincinemabackend.repositories.CustomerRepository;
import com.pes.chaplincinemabackend.services.AdminService;
import com.pes.chaplincinemabackend.services.CustomerService;
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
@RequestMapping(Paths.Signup.Base)
public class SignupEndpoint {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping()
    public String signup(Model model, Principal principal) {
        return "signup";
    }

    @RequestMapping(value="/customer", method = RequestMethod.POST)
    public String signupCustomerPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            Model model,
            Principal principal) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPassword(password);
        customer.setEnabled(true);
        Customer storedCustomer = customerService.create(customer).orElseThrow(() -> new UsernameNotFoundException(username));
        return "login";
    }
}
