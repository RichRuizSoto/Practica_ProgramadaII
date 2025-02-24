package com.mycompany.cspractiicaprogramadaii;

public class ProductoDescuento extends ProductoBase {

    public ProductoDescuento(String categoria) {
        super(categoria);
    }
    
    @Override
    public double precioProducto(Producto producto) {
        
        if (producto.getCantidadDisponible() >= 78) {
            double desc = (producto.getPrecio() / 100) * 10;
            return producto.getPrecio() - desc;
        }
        return producto.getPrecio();
    }

}
