package com.isunican.proyectobase.model;

import java.util.Objects;

public class DistanciaGasolineraYPuntoConocido {
   private double distancia=0.0;
   private PuntoConocido puntoConocido=null;


    public DistanciaGasolineraYPuntoConocido(double distancia) {
        this.distancia = distancia;
    }

    public PuntoConocido getPuntoConocido() {
        return puntoConocido;
    }
    public void setPuntoConocido(PuntoConocido puntoConocido) {
        this.puntoConocido = puntoConocido;
    }
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return "DistanciaGasolineraYPuntoConocido{" +
                "distancia=" + distancia +
                ", puntoConocido=" + puntoConocido +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DistanciaGasolineraYPuntoConocido)) return false;
        DistanciaGasolineraYPuntoConocido that = (DistanciaGasolineraYPuntoConocido) o;
        return Double.compare(that.distancia, distancia) == 0 &&
                puntoConocido.equals(that.puntoConocido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distancia, puntoConocido);
    }
}
