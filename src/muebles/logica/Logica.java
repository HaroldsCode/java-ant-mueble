package muebles.logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import muebles.constructores.Muebles;
import muebles.constructores.Venta;
import muebles.precarga.PreMuebles;
import muebles.precarga.PreVentas;

public class Logica implements ILogica {
    
  private ArrayList<Muebles> Inventario = new ArrayList<>();
  private ArrayList<Venta> Ventas = new ArrayList<>();
  
  private static int contadorVenta = new PreVentas().getPrecarga().size()+1;
  private static int contadorInventario = new PreMuebles().getPrecarga().size()+1;
  
  public Logica (){
    this.Inventario.addAll(new PreMuebles().getPrecarga());
    this.Ventas.addAll(new PreVentas().getPrecarga());
  }
      
  /*************************************
   *              Muebles              *
   *************************************/

  @Override
  public void registrarProducto( Muebles mueble ) {
    mueble.setId(contadorInventario);
    Inventario.add(mueble);
    contadorInventario++;
  }

  @Override
  public void editarProducto( Muebles mueble ) {
    for ( int i = 0; i < Inventario.size(); i++ ) {
      if ( Inventario.get(i).getId() == mueble.getId() ) {
        Inventario.get(i).setNombre(mueble.getNombre());
        Inventario.get(i).setPrecio(mueble.getPrecio());
        Inventario.get(i).setCantidad(mueble.getCantidad());
      }
    }
  }

  @Override
  public Muebles buscarProductoPorId(int id) {
    Muebles resultado = new Muebles();
    for (Muebles mueble : Inventario) {
      if ( mueble.getId() == id ){
        resultado = mueble;
      }
    }
    return resultado;
  }
  
  @Override
  public Muebles buscarProductoPorPosicion(int posicion, String parametro) {
    return this.listadoMuebles(parametro).get(posicion);
  }

  @Override
  public ArrayList<Muebles> listadoMuebles(String parametro) {
    
    if ( "".equals(parametro) || parametro == null ) {
      return Inventario;
    }
    
    ArrayList<Muebles> nuevaData = new ArrayList<>();
    for (Muebles mueble : Inventario) {
      if ( mueble.getNombre().toLowerCase().contains(parametro.toLowerCase()) ){
        nuevaData.add( mueble );
      }
    }
    return nuevaData;
  }

  @Override
  public void EliminarProducto(int id) {
    ArrayList<Muebles> InventarioTemporal = new ArrayList<>();
    
    for (Muebles mueble : Inventario) {
      if ( mueble.getId() != id ){
        InventarioTemporal.add(mueble);
      }
    }
    
    this.Inventario.clear();
    this.Inventario.addAll(InventarioTemporal);
  }
  
  /*************************************
   *              Ventas               *
   *************************************/
  
  @Override
  public void registrarVenta( Venta venta ) {
    venta.setId(contadorVenta);
    venta.setFecha_venta(LocalDate.now().toString());
    Ventas.add(venta);
    contadorVenta++;
  }
  
  @Override
  public ArrayList<Venta> listadoVenta ( ){
    return this.Ventas;
  }

  /*************************************
   *             Reportes              *
   *************************************/
  
  @Override
  public String GenerarReporteVentas() {
    double contador = 0;
    String respuesta =  "**********************************************************\n" +
                        " Muebles Santiago\n" +
                        " Estas son las ventas del día.\n\n" +
                        "**********************************************************\n";
    
    for ( Venta venta : this.Ventas ) {
      if ( venta.getFecha_venta().equals( LocalDate.now().toString() ) ){
        respuesta +=    "\n[[[[[[[[[[[[[ Factura #"+ venta.getId() +" ]]]]]]]]]]]]]";
        for ( Muebles mueble : venta.getMuebles() ) {
          respuesta +=  "\n- ID: " + mueble.getId()+ "\n" +
                        "- NOMBRE: " + mueble.getNombre() + "\n" +
                        "- SUBTOTAL: $" + mueble.getPrecio() + "\n" +
                        "- CANTIDAD: " + mueble.getCantidad() + "\n";
          contador += mueble.getPrecio();
        }
      }
    }
    if ( !this.Ventas.isEmpty() ){
      respuesta +=  "---------------------------------------------\n" +
                    " TOTAL : $"+ contador +"\n" +
                    "---------------------------------------------\n\n";
      
    }
    return respuesta;
  }

  @Override
  public String GenerarReporteInventario() {
    String respuesta =  "**********************************************************\n" +
                        " Los siguientes productos se encuentran con existencias bajas.\n" +
                        " COMPRA MÁS PRODUCTOS!!\n\n" +
                        "**********************************************************\n";
    
    for ( Muebles mueble : this.Inventario ) {
      if ( mueble.getCantidad() <= (mueble.getCantidadMinima()) ){
        respuesta +=  "\n- ID: " + mueble.getId()+ "\n" +
                      "- NOMBRE: " + mueble.getNombre() + "\n" +
                      "- PRECIO DE VENTA: " + mueble.getPrecio() + "\n" +
                      "- CANTIDAD: " + mueble.getCantidad() + "\n\n" +
                      "=======================================\n";
      }
    }
    return respuesta;
  }
  
}
