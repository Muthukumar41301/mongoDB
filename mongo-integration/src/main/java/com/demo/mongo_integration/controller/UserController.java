package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.entity.*;
import com.demo.mongo_integration.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }

    @PostMapping("/saveAll")
    public List<Users> createAllUsers(@RequestBody List<Users> users) {
        return userRepository.saveAll(users);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/names")
    public List<Users> getUsers(@RequestParam("userName") String userName){
        return userRepository.finByUserNames(userName);
    }

    @PostMapping("/roles")
    public Roles createRole(@RequestBody Roles roles) {
        return roleRepository.save(roles);
    }

    @PutMapping("/roles/{id}")
    public Roles updateRole(@PathVariable String id, @RequestBody Roles updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(updatedRole.getName());
                    role.setDescription(updatedRole.getDescription());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @DeleteMapping("/roles/{id}")
    public String deleteRole(@PathVariable String id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return "Role deleted successfully";
        } else {
            return "Role not found with id: " + id;
        }
    }
}
