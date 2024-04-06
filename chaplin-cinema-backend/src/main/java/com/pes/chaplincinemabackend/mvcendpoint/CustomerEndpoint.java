package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.services.CustomerService;
import com.pes.chaplincinemabackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(Paths.Customers.Base)
public class CustomerEndpoint {
    @Autowired
    private CustomerService customerService;
    @RequestMapping()
    public String getAllCustomers(Model model) {
        model.addAttribute("customers",customerService.findAll());
        return "customers";
    }
    @RequestMapping(value="/{username}", method = RequestMethod.DELETE)
    public String deleteCustomer(Model model, @PathVariable("username") String username) {
        customerService.deleteCustomer(username);
        return "customers";
    }
    @RequestMapping("/{id}")
    public String getCustomerByID(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("customer",customerService.findByID(id));
        return "customer";
    }
    @RequestMapping("/search")
    public String getCustomerBySubstr(Model model, @RequestParam("substr") String substr) {
        model.addAttribute("customers",customerService.searchCustomerBySubstring(substr));
        return "customers";
    }

}
