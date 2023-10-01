/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

/**
 *
 * @author alift
 */
public interface ControlListener {
    public void register(String nama, String username, String email, String password, String gender, int age, int height, int weight);
    public void login(String username, String login);
}
