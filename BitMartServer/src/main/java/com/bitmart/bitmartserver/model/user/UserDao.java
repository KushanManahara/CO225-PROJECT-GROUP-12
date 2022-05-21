package com.bitmart.bitmartserver.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {

    @Autowired
    private UserRepository repository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(User user){
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(users::add);
        return users;
    }

    public void delete(User user){
        repository.delete(user);
    }
}
