package com.example.SpringBoot.Service;

import com.example.SpringBoot.model.User;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private static int PEOPLE_COUNT;
    private List<User> users = new ArrayList<>();


    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++PEOPLE_COUNT);
        users.add(user);
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void update(int id, User updatedUser) {
        User userToBoUpdated = show(id);
        userToBoUpdated.setId(updatedUser.getId());
        userToBoUpdated.setUserName(updatedUser.getUserName());
        userToBoUpdated.setAge(updatedUser.getAge());

    }
}