/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;


public class granos {
    //variables o campos de mi base de datos
    int id;
    String nombreProducto;
    int cantidad;

    public granos() {
    }

    public granos(int id, String nombreProducto, int cantidad) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void insertarProductoGranos(JTextField parametroId, JTextField parametroNombre, JTextField parametroCantidad){
        setId(Integer.parseInt(parametroId.getText()));
        setNombreProducto(parametroNombre.getText());
        setCantidad(Integer.parseInt(parametroCantidad.getText()));
        
        Conexion c = new Conexion();
        String consulta = "INSERT INTO granos (id, nombreProducto, cantidad) VALUES (?,?,?);";
        
        try {
            CallableStatement cs = c.establecerConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el producto...");
            
            parametroId.setText("");
            parametroNombre.setText("");
            parametroCantidad.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo insertar el producto, error: "+e.getMessage());
        }
    }
    
    public void mostrarProductosGranos(JTable parametroMostrarTabla){
        Conexion c = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        parametroMostrarTabla.setRowSorter(ordenarTabla);
        
        String sql = "select * from granos";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombre producto");
        modelo.addColumn("Cantidad en KG");
        
        parametroMostrarTabla.setModel(modelo);
        
        String[] datos = new String[3];
        Statement st;
        
        try {
            st = c.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            parametroMostrarTabla.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros, error" +
                    e.getMessage());
        }
    }
    
    public void modificarProductos(JTextField parametroId, JTextField parametroNombre, JTextField parametroCantidad){
        setId(Integer.parseInt(parametroId.getText()));
        setNombreProducto(parametroNombre.getText());
        setCantidad(Integer.parseInt(parametroCantidad.getText()));
        
        Conexion c = new Conexion();
        String consulta = "update granos set nombreProducto = ?, cantidad = ? where id ="+getId();
        
        try {
            CallableStatement cs = c.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombreProducto());
            cs.setInt(2, getCantidad());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modifico el producto exitosamente...");
            parametroId.setText("");
            parametroNombre.setText("");
            parametroCantidad.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar el producto. Error: "+e.getMessage());
        }
    }
    
    public void eliminarProductos(JTextField parametroId, JTextField parametroNombre, JTextField parametroCantidad){
        setId(Integer.parseInt(parametroId.getText()));
        
        Conexion c = new Conexion();
        String cadenaEliminar = "delete from granos where id ="+getId();
        String cadenaBuscar = "select * from granos where id ="+getId();
        
        try {
            Statement st = c.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(cadenaBuscar);
            if(rs.next()){
                CallableStatement cs = c.establecerConexion().prepareCall(cadenaEliminar);
                cs.execute();
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente...");
                
                parametroId.setText("");
                parametroNombre.setText("");
                parametroCantidad.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "El producto con el ID "+getId()+" no existe en la base de datos");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto. Error: "+e.getMessage());
        }
    }
}
