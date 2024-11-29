
package com.nt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS only for this controller
public class UserController {


    // rbac/user/creatUser
   @GetMapping("/")
   @PreAuthorize("hasRole('ROLE_STUDENT')")
   public String any() {
	   return "anyPage";
   }
   	@PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_MANAGER')")
    @GetMapping("/home")
    public String home() {
    	System.out.println("/home");
    	return "home page is running";
    	
    	}
   	
    @GetMapping("/getUser")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getUsers() {
    	//System.out.println("getuser");
        return "here all the user list";
    }

    @PostMapping("/creatUser")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String createUser(@RequestBody String name) {
    	//System.out.println(name);
    	return name +" deatils succesfully register";
        }
    
    

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String deleteUser(@PathVariable Long id) {
        return id +" id details deleted succesfully";
    }
   	
   /*
     @GetMapping("/getUser")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public List<User> getUsers() {
    	System.out.println("getuser");
        return userService.getAllUsers();
    }

    @PostMapping("/creatUser")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public User createUser(@RequestBody User user) {
    	//System.out.println(name);
    	System.out.println(user);
        return userService.saveUser(user);
        }
    
    

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    } 
    */
}
