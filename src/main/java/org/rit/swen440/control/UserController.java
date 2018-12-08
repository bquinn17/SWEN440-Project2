package org.rit.swen440.control;

import org.rit.swen440.dataLayer.User;
import org.rit.swen440.repository.UserRepository;
import java.util.List;

public class UserController {

    public User getUser(String userName) {
        List<User> usersAll = UserRepository.getAllRecords();
        for (User user : usersAll){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public void createUser(User user){
        UserRepository.createRecord(user);
    }

    public void removeUser(User user) {
        UserRepository.deleteRecord(user);
    }

    public User login(String UserName, String Password){
        User currentUser = null;
        List<User> usersAll = UserRepository.getAllRecords();
        for (User user: usersAll){
            if (user.getUserName().equals(UserName) && user.getPassword().equals(Password)){
                currentUser = user;
            }
        }
        return currentUser;
    }

    public User createAccount(String UserName, String Password, String FullName){
        User user = new User();
        user.setFullName(FullName);
        user.setUserName(UserName);
        user.setPassword(Password);
        UserRepository.createRecord(user);
        return user;
    }

    public void writeUsers(List<User> users) {
        for (User user : users) {
            updateUser(user);
        }
    }

    public void updateUser(User user) {
        UserRepository.updateRecord(user);
    }
}
