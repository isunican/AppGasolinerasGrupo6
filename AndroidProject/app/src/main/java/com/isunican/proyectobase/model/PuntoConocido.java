package com.isunican.proyectobase.model;

import java.util.Objects;


/**
 * Clase que simboliza un punto de referencia determinado por coordenadas (latitud y longitud).
 */
public class PuntoConocido {

    private String etiquetaCoordenada=""; //Incluye un valor identificativo para que el usuario reconozca las coordenadas, podria tener como valor Casa, Colegio...
    private double latitud=0.0;
    private double longitud=0.0;

    /*
     Se le pasan al contructor todos los valores para los atributos
     */
    public PuntoConocido(String etiquetaCoordenada, double latitud, double longitud) {
        this.etiquetaCoordenada = etiquetaCoordenada;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /*
     Metodos para modificar y obtener los valores almacenados en los atributos
     */
    public String getEtiquetaCoordenada() {
        return etiquetaCoordenada;
    }

    public void setEtiquetaCoordenada(String etiquetaCoordenada) {
        this.etiquetaCoordenada = etiquetaCoordenada;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }



    /*
     Siempre se tienen que redefinir los metodos toString() para obtener la información del objeto reunida, equal() para poder comparar objetos del mismo tipo, hashCode para poder generar una clave identificativa
     unica para el objeto
     */
    @Override
    public String toString() {
        return "PuntoConocido{" +
                "etiquetaCoordenada='" + etiquetaCoordenada + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuntoConocido)) return false;
        PuntoConocido that = (PuntoConocido) o;
        return Double.compare(that.latitud, latitud) == 0 &&
                Double.compare(that.longitud, longitud) == 0 &&
                etiquetaCoordenada.equals(that.etiquetaCoordenada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(etiquetaCoordenada, latitud, longitud);
    }
}
