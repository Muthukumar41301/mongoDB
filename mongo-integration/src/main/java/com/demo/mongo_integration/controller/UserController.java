package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.entity.*;
import com.demo.mongo_integration.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FilesRepository filesRepository;

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

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<UploadFiles> fileOptional = filesRepository.findById(Long.valueOf(id));
        if (fileOptional.isPresent()) {
            UploadFiles file = fileOptional.get();
            System.out.println(Arrays.toString(file.getFile()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Testing.pdf");
            return new ResponseEntity<>(file.getFile(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
