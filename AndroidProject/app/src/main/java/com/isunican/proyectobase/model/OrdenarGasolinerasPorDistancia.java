package com.isunican.proyectobase.model;

import java.util.Comparator;

public class OrdenarGasolinerasPorDistancia implements Comparator<Gasolinera> {
    @Override
    public int compare(Gasolinera o1, Gasolinera o2) {
        int salida=1000;
        if(o1.getDistancia().getDistancia()<o2.getDistancia().getDistancia()){
            salida=-1;
        }else if(o1.getDistancia().getDistancia()==o2.getDistancia().getDistancia()){
            salida=0;
        }else{
            salida=1;
        }
        return salida;
    }
}
