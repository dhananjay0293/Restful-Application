package com.user;

import com.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    public static User getMockUser(){
        User user = new User();
        user.setId("10");
        user.setName("abc");
        user.setAge("20");
        user.setAddress("Bangalore");
        user.setGender("F");
        user.setRole("admin");
        return user;
    }

    public static User getMockUserWithoutId(){
        User user = new User();
        user.setName("abc");
        user.setAge("20");
        user.setAddress("Bangalore");
        user.setGender("F");
        user.setRole("admin");
        return user;
    }

    public static List<User> getMockUserList(){
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId("10");
        user.setName("abc");
        user.setAge("20");
        user.setAddress("Bangalore");
        user.setGender("F");
        user.setRole("admin");
        userList.add(user);
        return userList;
    }
}
