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

    public User login(String userName, String password){
        User currentUser = null;
        List<User> usersAll = UserRepository.getAllRecords();
        for (User user: usersAll){
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                currentUser = user;
            }
        }
        return currentUser;
    }

    public User createAccount(String userName, String password, String fullName){
        User user = new User();
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPassword(password);
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
