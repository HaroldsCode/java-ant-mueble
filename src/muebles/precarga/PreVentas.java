package muebles.precarga;

import java.util.ArrayList;
import muebles.constructores.Muebles;
import muebles.constructores.Venta;

public class PreVentas {
 
  private ArrayList<Muebles> muebles;
  private ArrayList<Venta> listado;

  public PreVentas() {
    this.listado = new ArrayList<>();
    
    this.muebles = new ArrayList<Muebles>();
    this.muebles.add( new Muebles( 1, "Silla Rimax Azúl", 1, 35000 ) );
    this.listado.add( new Venta( 1, this.muebles, "2022-03-13", "t", 35000.0 ) );
    
    this.muebles = new ArrayList<Muebles>();
    this.muebles.add( new Muebles( 3, "Silla Rimax Verde", 1, 35000 ) );
    this.muebles.add( new Muebles( 6, "Organizador Vanyplaz", 3, 75000 ) );
    this.listado.add( new Venta( 2, this.muebles, "2022-03-13", "t", 110000.0 ) );
    
    this.muebles = new ArrayList<Muebles>();
    this.muebles.add( new Muebles( 6, "Organizador Vanyplaz", 3, 75000 ) );
    this.listado.add( new Venta( 3, this.muebles, "2022-03-14", "t", 75000.0 ) );
  }

  public ArrayList<Venta> getPrecarga() {
    return listado;
  }
  
}
