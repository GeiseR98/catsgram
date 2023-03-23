package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }
    public User create(User user) {
        users.put(user.getEmail(), user);
        return user;
    }
    public boolean isThereASpecifiedUser(User user) {
        if (users.containsKey(user.getEmail())) {
            return true;
        } else {
            return false;
        }
    }
    public User findUserByEmail(String email) {
        return users.getOrDefault(email, null);
    }
}
