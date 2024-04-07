package com.pes.chaplincinemabackend.mvcendpoint;

import com.pes.chaplincinemabackend.auth.services.UserService;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(Paths.Users.Base)
public class UsersEndpoint {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users",userService.findAll());
        return "users";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value="/makeadmin/{id}")
    public String makeAdmin(@PathVariable("id") UUID id, Model model) {
        userService.makeAdmin(id);
        model.addAttribute("users",userService.findAll());
        return "users";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value="/removeadmin/{id}")
    public String removeAdmin(@PathVariable("id") UUID id, Model model) {
        userService.removeAdmin(id);
        model.addAttribute("users",userService.findAll());
        return "users";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value="/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") UUID id, Model model) {
        userService.delete(id);
        model.addAttribute("users",userService.findAll());
        return "users";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value="/{username}", method = RequestMethod.DELETE)
    public String deleteCustomer(Model model, @PathVariable("username") String username) {
        userService.delete(username);
        return "users";
    }
    @RequestMapping("/search")
    public String getUsersBySubstr(Model model, @RequestParam("substr") String substr) {
        model.addAttribute("users",userService.searchUserBySubstr(substr));
        return "users";
    }
}
