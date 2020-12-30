/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.logging.Level;/
//import java.util.logging.Logger;

/**
 *
 * @author Alexis
 */
public class Transacciones_cab {
    private int id_transaccion;
    private String fecha;
    private int id_cliente;
    private int total;
    private String direccion_envio;
    private int id_medio_pago;
    private int nro_tarjeta;
    private String estado;
    
    
    /**
     * Default Constructor
     */
    
    public Transacciones_cab() {
       
    }

    public Transacciones_cab(int id_transaccion, java.util.Date fecha, int id_cliente, int total, String direccion_envio, int id_medio_pago, int nro_tarjeta, String estado) throws ParseException {
      
       
        /*Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        this.fecha=(Date) date1;*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        this.fecha = formatter.format(fecha);
        
        this.id_transaccion = id_transaccion;
       
        this.id_cliente = id_cliente;
        this.total = total;
        this.direccion_envio = direccion_envio;
        this.id_medio_pago = id_medio_pago;
        this.nro_tarjeta = nro_tarjeta;
        this.estado = estado;
    }

   

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        this.fecha = formatter.format(fecha);
       
    }

   
    

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDireccion_envio() {
        return direccion_envio;
    }

    public void setDireccion_envio(String direccion_envio) {
        this.direccion_envio = direccion_envio;
    }

    public int getId_medio_pago() {
        return id_medio_pago;
    }

    public void setId_medio_pago(int id_medio_pago) {
        this.id_medio_pago = id_medio_pago;
    }

    public int getNro_tarjeta() {
        return nro_tarjeta;
    }

    public void setNro_tarjeta(int nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   

   

  

    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id_transaccion).append(", fecha: ")
                .append(fecha).append(", id_cliente=: ").append(id_cliente)
                .append(", total: ").append(total)
                .append(", direccion_envio: ").append(direccion_envio)
                .append(", id_medio_pago: ").append(id_medio_pago)
                .append(", nro_tarjeta: ").append(nro_tarjeta)
                .append(", estado: ").append(estado)
                .append("}").toString();
    }
    
    
    
}
