package com.mycompany.cspractiicaprogramadaii;

public class Producto extends ProductoBase {

    private String nombre;
    final private String codigo;
    private double precio;
    private int cantidadDisponible;

    public Producto(String nombre, String codigo, double precio, int cantidadDisponible, String categoria) {
        super(categoria);
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }





}
