package com.pes.chaplincinemabackend.endpoints;

import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Seat;
import com.pes.chaplincinemabackend.entities.Ticket;
import com.pes.chaplincinemabackend.services.AdminService;
import com.pes.chaplincinemabackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.V1.Admins.fullPath)
public class AdminEndpoint {
    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Admin> postAdmin(@RequestBody Admin userEntity) {
        return adminService.save(userEntity);
    }
    @RequestMapping(value = Paths.V1.Customers.GetOne, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Optional<Admin> findAdminByUsername(@PathVariable(Paths.V1.Admins.GetOnePathVariable) String username) {
        return adminService.findOne(username);
    }
    @RequestMapping(value = Paths.V1.Admins.GetOne, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Admin> deleteCustomer(@PathVariable(Paths.V1.Admins.GetOnePathVariable) String username) {
        return adminService.deleteAdmin(username);
    }
}
