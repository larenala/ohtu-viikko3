package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        if(username.length()<3 || password.length()<8) {
            return true;
        }
        for(int i=0; i<username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isAlphabetic(c)) {
                return true;
            }
        }
        for(int i=0; i<password.length(); i++) {

            char c = password.charAt(i);
            if (!Character.isAlphabetic(c)) {
                return false;
            } else if (i==password.length()-1) {
                return true;
            }
        }

        return false;
    }
}
