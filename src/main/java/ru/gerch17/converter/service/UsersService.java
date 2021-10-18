package ru.gerch17.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerch17.converter.entity.Users;
import ru.gerch17.converter.repo.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void createUser(String name, String password) {
        Users user = new Users();
        user.setUserName(name);
        user.setPassword(password);
        user.setUser_role("ROLE_USER");
        usersRepository.save(user);
    }

    public boolean findOneUserByLogin(String login){
        if(usersRepository.findByUserName(login)!=null)
            return true;
        else return false;
    }
}
