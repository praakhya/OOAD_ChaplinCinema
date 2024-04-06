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
    @RequestMapping("/customer")
    public String signupCustomer(Model model, Principal principal) {
        return "signupCustomer";
    }
    @RequestMapping("/admin")
    public String signupAdmin(Model model, Principal principal) {
        return "signupAdmin";
    }
    @RequestMapping(value="/customer", method = RequestMethod.POST)
    public String signupCustomerPost(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("firstname") String firstName,
        @RequestParam("lastname") String lastName,
                            Model model,
                            Principal principal) {
        Customer storedCustomer = customerService.findByUsername(username).get();
        if (storedCustomer==null) {
            throw new EntityAlreadyExistsException(ExceptionMessage.USER_ALREADY_EXISTS.getError(), ExceptionMessage.USER_ALREADY_EXISTS.getReason());
        }
        else {
            System.out.println("Called post on signup");
            Customer customer = new Customer();
            customer.setUsername(username);
            customer.setPassword(passwordEncoder.encode(password));
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customerService.save(customer);
            System.out.println("CUSTOMER:"+customer);
        }

        return "login";
    }
    @RequestMapping(value="/admin", method = RequestMethod.POST)
    public String signupAdminPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            Model model,
            Principal principal) {

        System.out.println("Called post on signup");
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        adminService.save(admin);
        return "login";
    }
}
