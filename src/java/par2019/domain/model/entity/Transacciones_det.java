/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity; 

/**
 *
 * @author Matias
 */
public class Transacciones_det  extends BaseEntitytd<Integer> {

    
    //private int id_transaccion;
    //private int item;
    private int id_producto;
    private int cantidad;
    private int precio;
    private int subtotal;
    
    public Transacciones_det() {
        super(0,0);
        
    }

    public Transacciones_det(int id_transaccion,int item, int id_producto, int cantidad, int precio, int subtotal) {
        super(id_transaccion,item);
        //this.id_transaccion=id_transaccion;
        //this.item = item;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

 
   
    
    
    
    
    @Override
    public String toString() {
        return "transacciones_det{ id_transaccion" +this.id+ "item=" + this.item + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio=" + precio + ", subtotal=" + subtotal + '}';
    }

    
    
    
}
