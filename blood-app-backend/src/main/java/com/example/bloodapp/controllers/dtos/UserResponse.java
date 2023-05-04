package com.example.bloodapp.controllers.dtos;

import com.example.bloodapp.domain.entity.Role;
import com.example.bloodapp.domain.entity.User;

public class UserResponse extends BaseResponse{
    private Long id;
    private String email;
    private String password;
    private Role role;

    public UserResponse() {

    }

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
