/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import User.User;

/**
 *
 * @author alift
 */
public interface UserDaoInterface {
    public void createUser(User user);
    public User getUserByUsername(String username);
    public void updateUser(String username, String name, String email, String gender, int age, int height, int weight);
    public void deleteUser(User user);
    public boolean isMatch(String username, String password);
}
