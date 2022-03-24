package muebles.precarga;

import java.util.ArrayList;
import muebles.constructores.Muebles;

public class PreMuebles {
  
  private ArrayList<Muebles> Listado;
  
  public PreMuebles (){
    Listado = new ArrayList<>();
    Listado.add(new Muebles(1, "Silla Rimax Azúl", 25, 35000));
    Listado.add(new Muebles(2, "Silla Rimax Blanca", 25, 35000));
    Listado.add(new Muebles(3, "Silla Rimax Verde", 4, 35000));
    Listado.add(new Muebles(4, "Mesa Rimax 4 puestos", 25, 55000));
    Listado.add(new Muebles(5, "Juego comedor Rimax 4 puestos ( Sillas x Mesa ) Color Café", 6, 200000));
    Listado.add(new Muebles(6, "Organizador Vanyplaz", 10, 25000));
  }
  
  public ArrayList<Muebles> getPrecarga(){
    return this.Listado;
  }
  
}
