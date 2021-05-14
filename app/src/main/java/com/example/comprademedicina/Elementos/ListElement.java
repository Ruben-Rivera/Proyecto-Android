package com.example.comprademedicina.Elementos;

import java.io.Serializable;

public class ListElement implements Serializable {
    public int imgMedicina;
    public String nombre;
    public String descripcion;
    public String precio;
    public String tipo;

    public ListElement(int imgMedicina, String nombre, String descripcion, String precio, String tipo) {
        this.imgMedicina = imgMedicina;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo=tipo;
    }

    public int getImgMedicina() {
        return imgMedicina;
    }
    public void setImgMedicina(int imgMedicina) {
        this.imgMedicina = imgMedicina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
