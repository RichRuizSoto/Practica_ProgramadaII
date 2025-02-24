package com.mycompany.cspractiicaprogramadaii;

public abstract class ProductoBase {
    
    String categoria;

    public ProductoBase(String categoria) {
        this.categoria = categoria;
    }

    public double precioProducto(Producto producto) {
        return producto.getPrecio();
    }

}
