package com.example.comprademedicina.Elementos;

import java.io.Serializable;

public class ListElement implements Serializable {
    public int imgMedicina;
    public String nombre;
    public String descripcion;
    public String precio;

    public ListElement(int imgMedicina, String nombre, String descripcion, String precio) {
        this.imgMedicina = imgMedicina;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
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
}
