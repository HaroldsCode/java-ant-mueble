package muebles.logica;

import muebles.constructores.Usuario;
import muebles.constructores.Usuario;

public class Login {
  
  private Usuario uLogin = new Usuario( );
  private boolean sesion;
  private String usuarioSesion;
  
  public Login() {
    this.sesion = false;
    this.uLogin.setCedula("123456");
    this.uLogin.setNombre("Santiago");
    this.uLogin.setUsuarioNombre("t");
    this.uLogin.setUsuarioContrasena("t");
    this.usuarioSesion = this.uLogin.getNombre();
  }

  public boolean isSesion() {
    return sesion;
  }
  
  public String usuarioSesion(){
    return this.usuarioSesion;
  }
  
  public String getUsuario(){
    return this.uLogin.getUsuarioNombre();
  }
  
  public boolean IniciarSesion ( Usuario usuario ){
    this.sesion = (uLogin.getUsuarioNombre().equals(usuario.getUsuarioNombre()) && uLogin.getUsuarioContrasena().equals(usuario.getUsuarioContrasena()) );
    return this.sesion;
  }
  
  public void cerrarSesion ( ){
    this.sesion = false;
  }
}
