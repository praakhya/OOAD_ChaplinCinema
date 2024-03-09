package com.pes.chaplincinemabackend.endpoints;

import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Seat;
import com.pes.chaplincinemabackend.entities.Ticket;
import com.pes.chaplincinemabackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.V1.Customers.fullPath)
public class CustomerEndpoint {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> postCustomer(@RequestBody Customer userEntity) {
        return customerService.save(userEntity);
    }
    @RequestMapping(value = Paths.V1.Customers.GetOne, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Optional<Customer> findCustomerByUsername(@PathVariable(Paths.V1.Customers.GetOnePathVariable) String username) {
        return customerService.findOne(username);
    }
    @RequestMapping(value = Paths.V1.Customers.GetOne, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Customer> deleteCustomer(@PathVariable(Paths.V1.Customers.GetOnePathVariable) String username) {
        return customerService.deleteCustomer(username);
    }
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }
    @RequestMapping(value=Paths.V1.Customers.GetCustomersByFirstName, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findCustomersByFirstName(@PathVariable(Paths.V1.Customers.GetCustomersByFirstNameVariable) String firstName) {
        return customerService.findCustomersByFirstName(firstName);
    }
    @RequestMapping(value=Paths.V1.Customers.GetCustomersByLastName, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findCustomersByLastName(@PathVariable(Paths.V1.Customers.GetCustomersByLastNameVariable) String lastName) {
        return customerService.findCustomersByLastName(lastName);
    }
    void makeBooking (UUID movieId, UUID theaterId, Seat seat) {

    }
    void cancelBooking (UUID bookingId) {

    }
    Ticket getTicket() {
        return new Ticket();
    }
}
