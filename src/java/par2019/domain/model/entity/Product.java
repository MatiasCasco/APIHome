/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.domain.model.entity;

/**
 *
 * @author Alexis
 */
public class Product extends BaseEntity<Integer> {
    private int id_categoria;
    private int precio_unit;
    private int cantidad;
    
    
    /**
     * Default Constructor
     */
    
    public Product() {
        super(0, "");
    }

    public Product(Integer id, String descripcion,int id_categoria,int precio_unit,int cantidad) {
        super(id, descripcion);
        this.id_categoria=id_categoria;
        this.precio_unit=precio_unit;
        this.cantidad=cantidad;
    }

    

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(int precio_unit) {
        this.precio_unit = precio_unit;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(id).append(", descripcion: ")
                .append(nombre).append(", id_categoria: ").append(id_categoria)
                .append(", precio_unit: ").append(precio_unit)
                .append(", cantidad: ").append(cantidad)
                .append("}").toString();
    }
    
    
}
