package com.isunican.proyectobase.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;


/*
------------------------------------------------------------------
    Clase que almacena la informacion de una gasolinera
    Implementa la interfaz Parceable, que permite que luego podamos
    pasar objetos de este tipo entre activities a traves de una llamada intent
------------------------------------------------------------------
*/

public class Gasolinera implements Parcelable, Comparable<Gasolinera> {
    private double distanciaEntreGasolineraYPunto =0.0; //Se anhade este atributo que indica la distancia entre la presente gasolinera y un punto de referencia.
    private int ideess;
    private double latitud; //Se ha incluido el elemento latitud que no estaba previamente.
    private double longitud; //Se ha incluido el elemento longitud que no estaba previamente.
    private String localidad;
    private String provincia;
    private String direccion;
    private double gasoleoA;
    private double gasoleoB; //Elemento anhadido para poder listar y mostrar con su valor.
    private double gasolina95;
    private String rotulo;




    /**
     * Constructor, getters y setters
     */
    public Gasolinera (int ideess,double latitud,double longitud, String localidad, String provincia, String direccion, double gasoleoA,double gasoleoB, double gasolina95, String rotulo){
        this.ideess = ideess;
        this.latitud=latitud; //Se ha incluido el elemento latitud que no estaba previamente.
        this.longitud=longitud; //Se ha incluido el elemento longitud que no estaba previamente.
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleoA = gasoleoA;
        this.gasoleoB=gasoleoB; //Elemento anhadido para poder listar y mostrar con su valor.
        this.gasolina95 = gasolina95;
        this.rotulo = rotulo;
    }

    /*
    Se incluyen los metodos get y set para poder anhadir la distancia a un punto a traves de sus coordenadas y obtener la misma.
     */
    public double getDistanciaEntreGasolineraYPunto() {
        return distanciaEntreGasolineraYPunto;
    }

    public void setDistanciaEntreGasolineraYPunto(double distanciaEntreGasolineraYPunto) {
        this.distanciaEntreGasolineraYPunto = distanciaEntreGasolineraYPunto;
    }


    /*
    Se incluyen los metodos get y set para poder modificar y obtener la latitud y longitud de la gasolinera.
    */
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

    public int getIdeess() { return ideess; }
    public void setIdeess(int ideess) { this.ideess = ideess; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getGasoleoA() { return gasoleoA; }
    public void setGasoleoA(double gasoleoA) { this.gasoleoA = gasoleoA; }

    //He tenido que meter el gasoleo B ya que es el más básico y se pedía en la funcionalidad.
    public double getGasoleoB() {
        return gasoleoB;
    }

    public void setGasoleoB(double gasoleoB) {
        this.gasoleoB = gasoleoB;
    }
    public String getRotulo() { return rotulo; }
    public void setRotulo(String rotulo) { this.rotulo = rotulo; }

    public double getGasolina95() { return gasolina95; }
    public void setGasolina95(double gasolina95) { this.gasolina95 = gasolina95; }


    @Override
    public String toString() {
        return "Gasolinera{" +
                "distancia=" + distanciaEntreGasolineraYPunto +
                ", ideess=" + ideess +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", direccion='" + direccion + '\'' +
                ", gasoleoA=" + gasoleoA +
                ", gasoleoB=" + gasoleoB +
                ", gasolina95=" + gasolina95 +
                ", rotulo='" + rotulo + '\'' +
                '}';
    }

    /**
     * interfaz Parcelable
     *
     * Métodos necesarios para implementar la interfaz Parcelable
     * que nos permitirá pasar objetos del tipo Gasolinera
     * directamente entre actividades utilizando intents
     * Se enviarían utilizando putExtra
     * myIntent.putExtra("id", objeto gasolinera);
     * y recibiéndolos con
     * Gasolinera g = getIntent().getExtras().getParcelable("id")
     */
    protected Gasolinera(Parcel in) {
        distanciaEntreGasolineraYPunto=in.readDouble(); //Se incluye para pasar el valor junto con el objeto entre activities
        ideess = in.readInt();
        latitud=in.readDouble(); //Se incluye para pasar el valor junto con el objeto entre activities
        longitud=in.readDouble(); //Se incluye para pasar el valor junto con el objeto entre activities
        localidad = in.readString();
        provincia = in.readString();
        direccion = in.readString();
        gasoleoA = in.readDouble();
        gasoleoB=in.readDouble(); //Elemento anhadido para poder listar y mostrar con su valor.
        gasolina95 = in.readDouble();
        rotulo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(distanciaEntreGasolineraYPunto); //Se incluye para pasar el valor junto con el objeto entre activities
        dest.writeInt(ideess);
        dest.writeDouble(latitud); //Se incluye para pasar el valor junto con el objeto entre activities
        dest.writeDouble(longitud); //Se incluye para pasar el valor junto con el objeto entre activities
        dest.writeString(localidad);
        dest.writeString(provincia);
        dest.writeString(direccion);
        dest.writeDouble(gasoleoA);
        dest.writeDouble(gasoleoB); //Elemento anhadido para poder listar y mostrar con su valor.
        dest.writeDouble(gasolina95);
        dest.writeString(rotulo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Gasolinera> CREATOR = new Parcelable.Creator<Gasolinera>() {
        @Override
        public Gasolinera createFromParcel(Parcel in) {
            return new Gasolinera(in);
        }

        @Override
        public Gasolinera[] newArray(int size) {
            return new Gasolinera[size];
        }
    };

    /**
     * Metodo que compara un atributo del objeto pasado como parametro con otro de la misma clase
     * para especificar un orden entre ambos, siendo la salida -1 this es < que o; siendo 0 ambos son
     * iguales y siendo 1 this es > que o. Sirve para ordenar TDAs. Puede lanzar NullPointerException
     * y ClassCastException.
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Gasolinera o) {

        if(gasoleoB==o.gasoleoB){
            return 0;
        }else if(gasoleoB<o.gasoleoB){
            return -1;
        }else{
            return 1;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasolinera)) return false;
        Gasolinera that = (Gasolinera) o;
        return Double.compare(that.distanciaEntreGasolineraYPunto, distanciaEntreGasolineraYPunto) == 0 &&
                ideess == that.ideess &&
                Double.compare(that.latitud, latitud) == 0 &&
                Double.compare(that.longitud, longitud) == 0 &&
                Double.compare(that.gasoleoA, gasoleoA) == 0 &&
                Double.compare(that.gasoleoB, gasoleoB) == 0 &&
                Double.compare(that.gasolina95, gasolina95) == 0 &&
                localidad.equals(that.localidad) &&
                provincia.equals(that.provincia) &&
                direccion.equals(that.direccion) &&
                rotulo.equals(that.rotulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distanciaEntreGasolineraYPunto, ideess, latitud, longitud, localidad, provincia, direccion, gasoleoA, gasoleoB, gasolina95, rotulo);
    }
}