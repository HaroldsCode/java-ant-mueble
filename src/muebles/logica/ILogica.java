/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package muebles.logica;

import java.util.ArrayList;
import muebles.constructores.Muebles;
import muebles.constructores.Venta;

/**
 *
 * @author Harold
 */
public interface ILogica {
  
  public void registrarProducto ( Muebles mueble );
  
  public void editarProducto ( Muebles mueble );
  
  public Muebles buscarProductoPorId ( int id );
  
  public Muebles buscarProductoPorPosicion ( int posicion, String parametro );
  
  public ArrayList<Muebles> listadoMuebles ( String parametro );
  
  public void EliminarProducto ( int id );
 
  public void registrarVenta ( Venta venta );
  
  public ArrayList<Venta> listadoVenta ( );
  
  public abstract String GenerarReporteVentas ( );
  
  public abstract String GenerarReporteInventario ( );
}
