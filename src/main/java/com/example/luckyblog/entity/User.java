package com.example.luckyblog.entity;

import java.time.Instant;

/**
 * @author liuyidiao
 */
public class User {
    Integer id;
    String username;

    String avatar;
    Instant createdAt;
    Instant updatedAt;
    public User(Integer id, String name) {
        this.id = id;
        this.username = name;
        this.avatar ="";
        this.createdAt= Instant.now();
        this.updatedAt= Instant.now();
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }
}
