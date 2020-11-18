package com.isunican.proyectobase.model;

import java.util.Objects;

public class PuntoConocido {

    private String etiquetaCoordenada="";
    private double latitud=0.0;
    private double longitud=0.0;

    public PuntoConocido(String etiquetaCoordenada, double latitud, double longitud) {
        this.etiquetaCoordenada = etiquetaCoordenada;
        this.latitud = latitud;
        this.longitud = longitud;
    }

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
