package muebles.constructores;

import java.util.ArrayList;

public class Venta {
  
  private int id;
  private ArrayList<Muebles> muebles;
  private String fecha_venta;
  private String usario;
  private double costo;

  public Venta() {
  }

  public Venta(int id, ArrayList<Muebles> muebles, String fecha_venta, String usario, double costo) {
    this.id = id;
    this.muebles = muebles;
    this.fecha_venta = fecha_venta;
    this.usario = usario;
    this.costo = costo;
  }

  public ArrayList<Muebles> getMuebles() {
    return muebles;
  }

  public void setMuebles(ArrayList<Muebles> mueble) {
    this.muebles = mueble;
  }
  
  public String getFecha_venta() {
    return fecha_venta;
  }

  public void setFecha_venta(String fecha_venta) {
    this.fecha_venta = fecha_venta;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsario() {
    return usario;
  }

  public void setUsario(String usario) {
    this.usario = usario;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(double costo) {
    this.costo = costo;
  }  
  
}
