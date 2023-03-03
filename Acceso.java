/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Acceso {
    private String usuario;
    private int password;

    public Acceso() {
    }

    public Acceso(String usuario, int password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    
    public void loginExitoso(){
        interfazGrafica ig = new interfazGrafica();
        ig.setVisible(true);
    }
    
    public void entrar(JTextField parametroUser, JPasswordField parametroPassword){
        setUsuario(parametroUser.getText());
        setPassword(Integer.parseInt(parametroPassword.getText()));
        
        if(getUsuario().equals("miguel")){
            if(getPassword()==1234){
                loginExitoso();
            }else{
                JOptionPane.showMessageDialog(null, "Verifique los datos de acceso");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Verifique los datos de acceso");
        }
    }
}
