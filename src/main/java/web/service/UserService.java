package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void addUser(User user);
    public User getUserById(Long id);
    public void editUser(User user);
    public void deleteUser(User user);
}
