package com.pes.chaplincinemabackend.endpoints;

import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(Paths.V1.Admins.fullPath)
public class AdminEndpoint {
    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = Paths.V1.Admins.GetAdminByUsernamePath, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Optional<Admin> getAdminByUsername(@PathVariable(Paths.V1.Admins.GetAdminByUsernameVariable) String username) {
        return adminService.findByUsername(username);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = Paths.V1.Admins.GetOneByID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Optional<Admin> getAdminByID(@PathVariable(Paths.V1.Admins.GetOneByIDPathVariable) UUID id) {
        return adminService.findByID(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Admin> postAdmin(@RequestBody Admin userEntity) {
        return adminService.create(userEntity);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Optional<Admin> putAdmin(@RequestBody Admin admin) {return adminService.update(admin);}

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = Paths.V1.Admins.GetAdminByUsernamePath, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Admin> deleteAdmin(@PathVariable(Paths.V1.Admins.GetAdminByUsernamePath) String username) {
        return adminService.delete(username);
    }
}
