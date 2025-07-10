package com.demo.mongo_integration.controller;

import com.demo.mongo_integration.entity.*;
import com.demo.mongo_integration.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Create a user", description = "Add a single user to the database")
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }

    @Operation(summary = "Create all users", description = "Add multiple users to the database")
    @PostMapping("/saveAll")
    public List<Users> createAllUsers(@RequestBody List<Users> users) {
        return userRepository.saveAll(users);
    }

    @Operation(summary = "Get all users", description = "Retrieve all users from the database")
    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Operation(summary = "Get users by name", description = "Retrieve users by their user name")
    @GetMapping("/names")
    public List<Users> getUsers(@Parameter(description = "User name to filter by") @RequestParam("userName") String userName){
        return userRepository.findByUserNames(userName);
    }

    @Operation(summary = "Create a role", description = "Add a single role to the database")
    @PostMapping("/roles")
    public Roles createRole(@RequestBody Roles roles) {
        return roleRepository.save(roles);
    }

    @Operation(summary = "Update a role", description = "Update a role by its ID")
    @PutMapping("/roles/{id}")
    public Roles updateRole(@Parameter(description = "ID of the role to update") @PathVariable String id, @RequestBody Roles updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(updatedRole.getName());
                    role.setDescription(updatedRole.getDescription());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Operation(summary = "Delete a role", description = "Delete a role by its ID")
    @DeleteMapping("/roles/{id}")
    public String deleteRole(@Parameter(description = "ID of the role to delete") @PathVariable String id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return "Role deleted successfully";
        } else {
            return "Role not found with id: " + id;
        }
    }

    @Operation(summary = "Get a file by ID", description = "Retrieve a file by its ID")
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@Parameter(description = "ID of the file to retrieve") @PathVariable String id) {
        Optional<UploadFiles> fileOptional = filesRepository.findById(Long.valueOf(id));
        if (fileOptional.isPresent()) {
            UploadFiles file = fileOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", file.getFileName());
            // This part is problematic as we are not storing the file content in the DB anymore
            // We would need to fetch the file from S3 using the s3Key
            // Returning an empty byte array for now
            return new ResponseEntity<>(new byte[0], headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
