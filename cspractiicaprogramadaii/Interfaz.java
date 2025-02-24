package com.mycompany.cspractiicaprogramadaii;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Interfaz extends JFrame {

    private Producto[] productos;
    Carrito carrito = new Carrito();

    public Interfaz(Producto[] productos) {
        this.productos = productos;

        setTitle("Sistema de Cobro Minisúper");
        setSize(new Dimension(1100, 500));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);

        JPanel northPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel centralPanel = new JPanel(new BorderLayout());

        northPanel.setBackground(new Color(211, 211, 211));
        westPanel.setBackground(new Color(50, 50, 50));
        centralPanel.setBackground(Color.white);

        northPanel.setPreferredSize(new Dimension(0, 70));
        westPanel.setPreferredSize(new Dimension(160, 0));

        JPanel NCPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel CCPanel = new JPanel(new BorderLayout());

        NCPanel.setPreferredSize(new Dimension(0, 60));
        CCPanel.setPreferredSize(new Dimension(1000, 0));

        NCPanel.setBackground(new Color(127, 182, 211));

        JButton inventario, venta;

        inventario = new JButton("Admin");
        venta = new JButton("Venta");

        JButton[] northButtons = {inventario, venta};

        for (JButton boton : northButtons) {
            boton.setBackground(new Color(0, 0, 0, 0));
            boton.setPreferredSize(new Dimension(90, 50));
            boton.setFont(new Font("Arial", Font.BOLD, 13));
            boton.setForeground(new Color(50, 50, 50));
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            boton.setFocusPainted(false);
            NCPanel.add(boton);
        }

        JPanel WCCPanel = new JPanel();

        String[] nombreDeColumnas = {"NOMBRES", "ID", "PRECIO", "CANTIDAD"};

        Object[][] info = new Object[productos.length][4];

        for (int i = 0; i < productos.length; i++) {
            info[i][0] = productos[i].getNombre();
            info[i][1] = productos[i].getCodigo();
            info[i][2] = productos[i].getPrecio();
            info[i][3] = productos[i].getCantidadDisponible();
        }

        DefaultTableModel modelo = new DefaultTableModel(info, nombreDeColumnas);
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(500, 324));

        WCCPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel CCCPanel = new JPanel();

        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(865, 25));
        CCCPanel.add(space);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setPreferredSize(new Dimension(365, 50));
        CCCPanel.add(inputPanel);

        JComboBox comboBox = new JComboBox();
        comboBox.setBackground(Color.white);
        comboBox.setForeground(Color.black);
        comboBox.addItem("");
        for (Producto producto : productos) {
            comboBox.addItem(producto.getNombre());
        }

        inputPanel.add(comboBox);

        JButton agregar = new JButton("Agregar");
        inputPanel.add(agregar);
        agregar.setForeground(Color.darkGray);
        agregar.setContentAreaFilled(false);
        agregar.setFocusPainted(false);
        agregar.addActionListener((ActionEvent agregarProducto) -> {
            String productoSeleccionado = (String) comboBox.getSelectedItem();
            for (int i = 0; i < productos.length; i++) {
                if (productoSeleccionado.equals(productos[i].getNombre())) {
                    productos[i].setCantidadDisponible(productos[i].getCantidadDisponible() - 1);
                    modelo.setValueAt(productos[i].getCantidadDisponible(), i, 3);
                    modelo.fireTableCellUpdated(i, 3);

                    carrito.agregarProductos(productos[i]);
                }
            }
        });

        JPanel spaceInputPanel = new JPanel();
        inputPanel.add(spaceInputPanel);

        JButton remover = new JButton("Remover");
        inputPanel.add(remover);
        remover.setForeground(Color.red);
        remover.setContentAreaFilled(false);
        remover.setBorderPainted(false);
        remover.setFocusPainted(false);

        remover.addActionListener((ActionEvent removerProducto) -> {
            String productoSeleccionado = (String) comboBox.getSelectedItem();
            for (int i = 0; i < productos.length; i++) {
                if (productoSeleccionado.equals(productos[i].getNombre())) {
                    if (carrito.removerProductos(productos[i])) {
                        productos[i].setCantidadDisponible(productos[i].getCantidadDisponible() + 1);
                        modelo.setValueAt(productos[i].getCantidadDisponible(), i, 3);
                        modelo.fireTableCellUpdated(i, 3);
                    }
                }
            }
        });

        JButton confirmar = new JButton("Confirmar");
        confirmar.setPreferredSize(new Dimension(140, 35));
        confirmar.setBackground(new Color(50, 50, 50));
        confirmar.setForeground(Color.WHITE);
        confirmar.setBorderPainted(false);
        confirmar.setFocusPainted(false);
        CCCPanel.add(confirmar);

        JLabel recibo = new JLabel("<html>");
        JScrollPane scrollRecibo = new JScrollPane(recibo);
        scrollRecibo.setPreferredSize(new Dimension(400, 200));

        JPanel panelRecibo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRecibo.setPreferredSize(new Dimension(370, 200));
        panelRecibo.setBackground(Color.white);
        panelRecibo.add(recibo);

        confirmar.addActionListener((ActionEvent confirmarPedido) -> {
            ArrayList<Producto> cart = carrito.getCarrito();
            StringBuilder reciboText = new StringBuilder("<html>");
            double total = 0;
            for (Producto producto : cart) {
                reciboText.append("Producto: ").append(producto.getNombre())
                        .append(", Precio: ").append(producto.getPrecio())
                        .append("<br>");
                total += producto.getPrecio();
            }
            reciboText.append("<br><b>Total: </b>").append(total);
            reciboText.append("</html>");
            recibo.setText(reciboText.toString());
            if (panelRecibo.getParent() == null) {
                CCCPanel.add(panelRecibo, BorderLayout.EAST);
            }
            CCCPanel.revalidate();
            CCCPanel.repaint();
        });

        CCPanel.add(CCCPanel, BorderLayout.CENTER);
        CCPanel.add(WCCPanel, BorderLayout.WEST);

        centralPanel.add(NCPanel, BorderLayout.NORTH);
        centralPanel.add(CCPanel, BorderLayout.CENTER);

        add(northPanel, BorderLayout.NORTH);
        add(westPanel, BorderLayout.WEST);
        add(centralPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}
