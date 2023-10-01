    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

/**
 *
 * @author alift
 */
public interface Loginable {
    public void register();
    //public void register(String name, String username, String password, String email, String gender, int age, int height, int weight);
    public boolean login();
    public boolean login(String username, String password);
}
