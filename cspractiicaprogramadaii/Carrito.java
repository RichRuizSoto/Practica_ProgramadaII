package com.mycompany.cspractiicaprogramadaii;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Carrito {

    ArrayList<Producto> carrito = new ArrayList();
    Producto productoSeleccionado;

    public void agregarProductos(Producto producto) {
        productoSeleccionado = new Producto(producto.getNombre(), producto.getCodigo(), producto.getPrecio(), producto.getCantidadDisponible(), "Simple");
        productoSeleccionado.setPrecio(productoSeleccionado.precioProducto(productoSeleccionado));
        carrito.add(productoSeleccionado);
        System.out.println(productoSeleccionado.getNombre());
    }

public boolean removerProductos(Producto producto) {
    try {
        for (int i = 0; i < carrito.size(); i++) {
            if (carrito.get(i).getCodigo().equals(producto.getCodigo())) {
                carrito.remove(carrito.get(i));
                return true;
            }
        }

        throw new NoSuchElementException("Producto no encontrado en el carrito.");

    } catch (NoSuchElementException e) {
        System.out.println("Error al remover producto: " + e.getMessage());
        return false; 
    } finally {
        System.out.println("Proceso completo");
    }
}


    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

}
