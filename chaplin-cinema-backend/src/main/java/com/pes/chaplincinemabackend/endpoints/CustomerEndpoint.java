package com.pes.chaplincinemabackend.endpoints;

import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Seat;
import com.pes.chaplincinemabackend.entities.Ticket;
import com.pes.chaplincinemabackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.V1.Customers.fullPath)
public class CustomerEndpoint {
    @Autowired
    private CustomerService customerService;
    @PreAuthorize("@userPermissions.authorizedForAdminAndCurrentUsername(principal, #username)")
    @RequestMapping(value=Paths.V1.Customers.GetCustomersByUsername, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> getCustomerByUsername(@PathVariable(Paths.V1.Customers.GetCustomersByUsernameVariable) @P(Paths.V1.Customers.GetCustomersByUsernameVariable) String username) {
        return customerService.findByUsername(username);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value=Paths.V1.Customers.GetCustomersByFirstName, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCustomersByFirstName(@PathVariable(Paths.V1.Customers.GetCustomersByFirstNameVariable) String firstname) {
        return customerService.findByFirstname(firstname);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value=Paths.V1.Customers.GetCustomersByLastName, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCustomersByLastname(@PathVariable(Paths.V1.Customers.GetCustomersByLastNameVariable) String lastname) {
        return customerService.findByLastname(lastname);
    }
    @PreAuthorize("@userPermissions.authorizedForAdminAndCurrentId(principal, #id)")
    @RequestMapping(value=Paths.V1.Customers.GetOne, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> getCustomerByID(@PathVariable(Paths.V1.Customers.GetOnePathVariable) @P(Paths.V1.Customers.GetOnePathVariable) UUID id) {
        return customerService.findByID(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> postCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PreAuthorize("@userPermissions.authorizedForAdminAndCurrentCustomerBody(principal, #customer)")
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> putCustomer(@RequestBody @P("customer") Customer customer) {
        return customerService.update(customer);
    }

    @PreAuthorize("@userPermissions.authorizedForAdminAndCurrentUsername(principal, #username)")
    @RequestMapping(value = Paths.V1.Customers.GetCustomersByUsername, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> deleteCustomer(@PathVariable(Paths.V1.Customers.GetCustomersByUsernameVariable) @P(Paths.V1.Customers.GetCustomersByUsernameVariable) String username) {
        return customerService.deleteCustomer(username);
    }
}
