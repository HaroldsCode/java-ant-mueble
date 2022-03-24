package muebles.presentacion;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import muebles.constructores.Usuario;
import muebles.logica.Login;
import muebles.constructores.Muebles;
import muebles.constructores.Venta;
import muebles.logica.Logica;

public class Main extends javax.swing.JFrame {

  private DefaultTableModel modeloInventario;
  private DefaultTableModel modeloVenta;
  private DefaultTableModel modeloBusqueda;
  private DefaultTableModel modeloListadoVentas;
  
  private ArrayList<Muebles> ListadoTemporal;
  private Muebles muebleTemporal;
  private Muebles muebleEliminar;
  
  private Login login = new Login();
  private Logica logica = new Logica();
  
  private int id_seleccionado;
  private double contadorTotal;
  
  public Main() {
    initComponents();
    this.id_seleccionado = 0;
    this.contadorTotal = 0;
    this.ListadoTemporal = new ArrayList<>();
    this.muebleTemporal = new Muebles();
    this.muebleEliminar = new Muebles();
    
    this.botonesInventario();
    this.limpiarCampos();
        
    this.pantallaLogin();
    this.TablaInventario();
    this.TablaVenta();
    this.TablaBusqueda();
    this.TablaListadoVentas();
    
    this.cargarReportes();
    this.cargarTabla(jtInventario, modeloInventario, "Inventario", "");
    this.cargarTabla(jtBusqueda, modeloBusqueda, "", "");
  }
  
  private void TablaListadoVentas () {
    modeloListadoVentas  = new DefaultTableModel(){
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    
    modeloListadoVentas.addColumn("ID");
    modeloListadoVentas.addColumn("VENDEDOR");
    modeloListadoVentas.addColumn("MONTO VENDIDO");
    modeloListadoVentas.addColumn("FECHA DE VENTA");
    
    
    this.jtListadoVentas.setModel(this.modeloListadoVentas);
    this.jtListadoVentas.getTableHeader().setReorderingAllowed(false);
    
    jspListadoVentas.setViewportView(jtListadoVentas);
    if (jtListadoVentas.getColumnModel().getColumnCount() > 0) {
      jtListadoVentas.getColumnModel().getColumn(0).setResizable(false);
      jtListadoVentas.getColumnModel().getColumn(0).setPreferredWidth(10);
      jtListadoVentas.getColumnModel().getColumn(1).setResizable(false);
      jtListadoVentas.getColumnModel().getColumn(1).setPreferredWidth(100);
      jtListadoVentas.getColumnModel().getColumn(2).setResizable(false);
      jtListadoVentas.getColumnModel().getColumn(2).setPreferredWidth(100);
      jtListadoVentas.getColumnModel().getColumn(3).setResizable(false);
      jtListadoVentas.getColumnModel().getColumn(3).setPreferredWidth(25);
    }
  }
  
  private void TablaBusqueda () {
    modeloBusqueda  = new DefaultTableModel(){
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    
    modeloBusqueda.addColumn("ID");
    modeloBusqueda.addColumn("NOMBRE");
    modeloBusqueda.addColumn("PRECIO");
    
    
    this.jtBusqueda.setModel(this.modeloBusqueda);
    this.jtBusqueda.getTableHeader().setReorderingAllowed(false);
    
    jspBusqueda.setViewportView(jtBusqueda);
    if (jtBusqueda.getColumnModel().getColumnCount() > 0) {
      jtBusqueda.getColumnModel().getColumn(0).setResizable(false);
      jtBusqueda.getColumnModel().getColumn(0).setPreferredWidth(1);
      jtBusqueda.getColumnModel().getColumn(1).setResizable(false);
      jtBusqueda.getColumnModel().getColumn(1).setPreferredWidth(200);
      jtBusqueda.getColumnModel().getColumn(2).setResizable(false);
      jtBusqueda.getColumnModel().getColumn(2).setPreferredWidth(10);
    }
  }
  
  private void TablaVenta () {
    modeloVenta  = new DefaultTableModel(){
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    
    modeloVenta.addColumn("NOMBRE");
    modeloVenta.addColumn("CANTIDAD");
    modeloVenta.addColumn("SUBTOTAL");
    
    this.jtVenta.setModel(this.modeloVenta);
    this.jtVenta.getTableHeader().setReorderingAllowed(false);
    
    jspVenta.setViewportView(jtVenta);
    if (jtVenta.getColumnModel().getColumnCount() > 0) {
      jtVenta.getColumnModel().getColumn(0).setResizable(false);
      jtVenta.getColumnModel().getColumn(0).setPreferredWidth(200);
      jtVenta.getColumnModel().getColumn(1).setResizable(false);
      jtVenta.getColumnModel().getColumn(1).setPreferredWidth(1);
      jtVenta.getColumnModel().getColumn(2).setResizable(false);
      jtVenta.getColumnModel().getColumn(2).setPreferredWidth(1);
    }
  }
  
  private void TablaInventario () {
    modeloInventario  = new DefaultTableModel(){
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
        
    modeloInventario.addColumn("ID");
    modeloInventario.addColumn("NOMBRE");
    modeloInventario.addColumn("CANTIDAD");
    modeloInventario.addColumn("PRECIO");
    
    this.jtInventario.setModel(this.modeloInventario);
    this.jtInventario.getTableHeader().setReorderingAllowed(false);
    
    jScrollPane1.setViewportView(jtInventario);
    if (jtInventario.getColumnModel().getColumnCount() > 0) {
      jtInventario.getColumnModel().getColumn(0).setResizable(false);
      jtInventario.getColumnModel().getColumn(0).setPreferredWidth(1);
      jtInventario.getColumnModel().getColumn(1).setResizable(false);
      jtInventario.getColumnModel().getColumn(1).setPreferredWidth(300);
      jtInventario.getColumnModel().getColumn(2).setResizable(false);
      jtInventario.getColumnModel().getColumn(2).setPreferredWidth(1);
      jtInventario.getColumnModel().getColumn(3).setResizable(false);
      jtInventario.getColumnModel().getColumn(3).setPreferredWidth(1);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jpMenu = new javax.swing.JPanel();
    jpOpciones = new javax.swing.JPanel();
    btnInicio = new javax.swing.JButton();
    btnInventario = new javax.swing.JButton();
    btnReportes = new javax.swing.JButton();
    btnSalir = new javax.swing.JButton();
    jlSesion = new javax.swing.JLabel();
    jpContent = new javax.swing.JPanel();
    jTabbedContent = new javax.swing.JTabbedPane();
    jpLogin = new javax.swing.JPanel();
    btnLogin = new javax.swing.JButton();
    jlUsuario = new javax.swing.JLabel();
    txtUsuario = new javax.swing.JTextField();
    jlContrasena = new javax.swing.JLabel();
    txtContrasena = new javax.swing.JTextField();
    jpInicio = new javax.swing.JPanel();
    jlTitleInicio = new javax.swing.JLabel();
    jpReporteInventario = new javax.swing.JPanel();
    jspReporteInventario = new javax.swing.JScrollPane();
    jtpReporteInventario = new javax.swing.JTextPane();
    jpReporteVenta = new javax.swing.JPanel();
    jspReporteVenta = new javax.swing.JScrollPane();
    jtpReporteVenta = new javax.swing.JTextPane();
    jpInventario = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jtInventario = new javax.swing.JTable();
    txtBusquedaInventario = new javax.swing.JTextField();
    jlTitleInventario = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jpAccionesInvantario = new javax.swing.JPanel();
    btnEliminarInventario = new javax.swing.JButton();
    btnCancelarInventario = new javax.swing.JButton();
    btnEditarInventario = new javax.swing.JButton();
    btnGuardarInventario = new javax.swing.JButton();
    txtPrecioInventario = new javax.swing.JTextField();
    jlPrecioInventario = new javax.swing.JLabel();
    txtCantidadInventario = new javax.swing.JTextField();
    jlCantidadInventario = new javax.swing.JLabel();
    txtNombreInventario = new javax.swing.JTextField();
    jlNombreInventatio = new javax.swing.JLabel();
    jlBusquedaInventario = new javax.swing.JLabel();
    jpVenta = new javax.swing.JPanel();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jpRegistrarVenta = new javax.swing.JPanel();
    jspBusqueda = new javax.swing.JScrollPane();
    jtBusqueda = new javax.swing.JTable();
    jspVenta = new javax.swing.JScrollPane();
    jtVenta = new javax.swing.JTable();
    txtBusquedaVenta = new javax.swing.JTextField();
    btnCancelarVenta = new javax.swing.JButton();
    btnRelizarVenta = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    txtCantidadVenta = new javax.swing.JTextField();
    btnAgregarMueble = new javax.swing.JButton();
    jlTotalTitle = new javax.swing.JLabel();
    jlTotalSum = new javax.swing.JLabel();
    jlBusquedaVenta = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jSeparator2 = new javax.swing.JSeparator();
    btnEliminarMuebleVenta = new javax.swing.JButton();
    jpListadoVentas = new javax.swing.JPanel();
    jspListadoVentas = new javax.swing.JScrollPane();
    jtListadoVentas = new javax.swing.JTable();
    btnOrdenarFecha = new javax.swing.JButton();
    btnOrdenarId = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Muebles Santigo");
    setIconImages(null);
    setName("jfMuebles"); // NOI18N
    setResizable(false);
    setType(java.awt.Window.Type.POPUP);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jpMenu.setBackground(new java.awt.Color(250, 250, 250));

    jpOpciones.setBackground(new java.awt.Color(250, 250, 250));
    jpOpciones.setLayout(new java.awt.GridLayout(3, 1, 0, 15));

    btnInicio.setText("Inicio");
    btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnInicioMouseClicked(evt);
      }
    });
    jpOpciones.add(btnInicio);

    btnInventario.setText("Inventario");
    btnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnInventarioMouseClicked(evt);
      }
    });
    jpOpciones.add(btnInventario);

    btnReportes.setText("Ventas");
    btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnReportesMouseClicked(evt);
      }
    });
    jpOpciones.add(btnReportes);

    btnSalir.setText("Salir");
    btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnSalirMouseClicked(evt);
      }
    });

    javax.swing.GroupLayout jpMenuLayout = new javax.swing.GroupLayout(jpMenu);
    jpMenu.setLayout(jpMenuLayout);
    jpMenuLayout.setHorizontalGroup(
      jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpMenuLayout.createSequentialGroup()
        .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jpMenuLayout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
              .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          .addGroup(jpMenuLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jlSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jpMenuLayout.setVerticalGroup(
      jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuLayout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 346, Short.MAX_VALUE)
        .addComponent(jlSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnSalir)
        .addContainerGap())
    );

    getContentPane().add(jpMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 530));

    jpContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jTabbedContent.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

    jpLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jpLogin.setMaximumSize(new java.awt.Dimension(850, 530));
    jpLogin.setMinimumSize(new java.awt.Dimension(850, 530));

    btnLogin.setText("Entrar");
    btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnLoginMouseClicked(evt);
      }
    });

    jlUsuario.setText("USUARIO");

    txtUsuario.setText("t");

    jlContrasena.setText("CONTRASEÑA");

    txtContrasena.setText("t");

    javax.swing.GroupLayout jpLoginLayout = new javax.swing.GroupLayout(jpLogin);
    jpLogin.setLayout(jpLoginLayout);
    jpLoginLayout.setHorizontalGroup(
      jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpLoginLayout.createSequentialGroup()
        .addGap(238, 238, 238)
        .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(txtUsuario)
          .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jlContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(331, Short.MAX_VALUE))
    );
    jpLoginLayout.setVerticalGroup(
      jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpLoginLayout.createSequentialGroup()
        .addGap(150, 150, 150)
        .addComponent(jlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jlContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(205, Short.MAX_VALUE))
    );

    jTabbedContent.addTab("tab1", jpLogin);

    jpInicio.setMaximumSize(new java.awt.Dimension(850, 530));
    jpInicio.setMinimumSize(new java.awt.Dimension(850, 530));
    jpInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jlTitleInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jlTitleInicio.setText("Muebles Santiago - Inicio");
    jpInicio.add(jlTitleInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 31));

    jpReporteInventario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reporte de inventario ( cantidad escasa )", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

    jtpReporteInventario.setEditable(false);
    jspReporteInventario.setViewportView(jtpReporteInventario);

    javax.swing.GroupLayout jpReporteInventarioLayout = new javax.swing.GroupLayout(jpReporteInventario);
    jpReporteInventario.setLayout(jpReporteInventarioLayout);
    jpReporteInventarioLayout.setHorizontalGroup(
      jpReporteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpReporteInventarioLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jspReporteInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(12, Short.MAX_VALUE))
    );
    jpReporteInventarioLayout.setVerticalGroup(
      jpReporteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpReporteInventarioLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jspReporteInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        .addContainerGap())
    );

    jpInicio.add(jpReporteInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 410, 470));

    jpReporteVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reporte de ventas diarias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

    jtpReporteVenta.setEditable(false);
    jspReporteVenta.setViewportView(jtpReporteVenta);

    javax.swing.GroupLayout jpReporteVentaLayout = new javax.swing.GroupLayout(jpReporteVenta);
    jpReporteVenta.setLayout(jpReporteVentaLayout);
    jpReporteVentaLayout.setHorizontalGroup(
      jpReporteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpReporteVentaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jspReporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jpReporteVentaLayout.setVerticalGroup(
      jpReporteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpReporteVentaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jspReporteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        .addContainerGap())
    );

    jpInicio.add(jpReporteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 390, 470));

    jTabbedContent.addTab("tab2", jpInicio);

    jpInventario.setMaximumSize(new java.awt.Dimension(850, 530));
    jpInventario.setMinimumSize(new java.awt.Dimension(850, 530));
    jpInventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jtInventario.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jtInventario.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jtInventarioMouseClicked(evt);
      }
    });
    jScrollPane1.setViewportView(jtInventario);

    jpInventario.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 829, 300));

    txtBusquedaInventario.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        txtBusquedaInventarioKeyReleased(evt);
      }
    });
    jpInventario.add(txtBusquedaInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 490, 320, 30));

    jlTitleInventario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jlTitleInventario.setText("Muebles Santiago - Inventario");
    jpInventario.add(jlTitleInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

    java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 4);
    flowLayout1.setAlignOnBaseline(true);
    jpAccionesInvantario.setLayout(flowLayout1);

    btnEliminarInventario.setText("Eliminar");
    btnEliminarInventario.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnEliminarInventarioMouseClicked(evt);
      }
    });
    jpAccionesInvantario.add(btnEliminarInventario);

    btnCancelarInventario.setText("Cancelar");
    btnCancelarInventario.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnCancelarInventarioMouseClicked(evt);
      }
    });
    jpAccionesInvantario.add(btnCancelarInventario);

    btnEditarInventario.setText("Actualizar");
    btnEditarInventario.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnEditarInventarioActionPerformed(evt);
      }
    });
    jpAccionesInvantario.add(btnEditarInventario);

    btnGuardarInventario.setText("Guardar");
    btnGuardarInventario.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        btnGuardarInventarioMouseClicked(evt);
      }
    });
    jpAccionesInvantario.add(btnGuardarInventario);

    jlPrecioInventario.setText("Precio");

    jlCantidadInventario.setText("Cantidad");

    jlNombreInventatio.setText("Nombre");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(19, 19, 19)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jlNombreInventatio)
          .addComponent(txtNombreInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jpAccionesInvantario, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(txtCantidadInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jlCantidadInventario))
            .addGap(39, 39, 39)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(txtPrecioInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jlPrecioInventario))))
        .addContainerGap(26, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
              .addComponent(jlCantidadInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(txtCantidadInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
              .addComponent(jlNombreInventatio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(6, 6, 6)
              .addComponent(txtNombreInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jlPrecioInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtPrecioInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jpAccionesInvantario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jpInventario.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 830, 130));

    jlBusquedaInventario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jlBusquedaInventario.setText("Busqueda específica");
    jpInventario.add(jlBusquedaInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, -1, 30));

    jTabbedContent.addTab("tab3", jpInventario);

    jpRegistrarVenta.setMaximumSize(new java.awt.Dimension(850, 530));
    jpRegistrarVenta.setMinimumSize(new java.awt.Dimension(850, 530));
    jpRegistrarVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jtBusqueda.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jtBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jtBusquedaMouseClicked(evt);
      }
    });
    jspBusqueda.setViewportView(jtBusqueda);

    jpRegistrarVenta.add(jspBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 400, 270));

    jtVenta.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jtVenta.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jtVentaMouseClicked(evt);
      }
    });
    jspVenta.setViewportView(jtVenta);

    jpRegistrarVenta.add(jspVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 410, 370));

    txtBusquedaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        txtBusquedaVentaKeyReleased(evt);
      }
    });
    jpRegistrarVenta.add(txtBusquedaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 400, 29));

    btnCancelarVenta.setText("Cancelar Venta");
    btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnCancelarVentaActionPerformed(evt);
      }
    });
    jpRegistrarVenta.add(btnCancelarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, 130, 30));

    btnRelizarVenta.setText("Realizar Venta");
    btnRelizarVenta.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnRelizarVentaActionPerformed(evt);
      }
    });
    jpRegistrarVenta.add(btnRelizarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, 120, 30));

    jLabel4.setText("Cantidad");
    jpRegistrarVenta.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, -1));

    txtCantidadVenta.setText("1");
    jpRegistrarVenta.add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 310, 30));

    btnAgregarMueble.setText("Agregar");
    btnAgregarMueble.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAgregarMuebleActionPerformed(evt);
      }
    });
    jpRegistrarVenta.add(btnAgregarMueble, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 390, -1, 30));

    jlTotalTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
    jlTotalTitle.setText("Total: $");
    jpRegistrarVenta.add(jlTotalTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

    jlTotalSum.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
    jlTotalSum.setText("0");
    jpRegistrarVenta.add(jlTotalSum, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, -1, -1));

    jlBusquedaVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jlBusquedaVenta.setText("Busqueda específica");
    jpRegistrarVenta.add(jlBusquedaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, 20));
    jpRegistrarVenta.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 820, 10));
    jpRegistrarVenta.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 400, 10));

    btnEliminarMuebleVenta.setText("Eliminar");
    btnEliminarMuebleVenta.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnEliminarMuebleVentaActionPerformed(evt);
      }
    });
    jpRegistrarVenta.add(btnEliminarMuebleVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 410, 30));

    jTabbedPane1.addTab("Registrar Venta", jpRegistrarVenta);

    jtListadoVentas.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jtListadoVentas.getTableHeader().setReorderingAllowed(false);
    jtListadoVentas.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jtListadoVentasMouseClicked(evt);
      }
    });
    jspListadoVentas.setViewportView(jtListadoVentas);

    btnOrdenarFecha.setText("Fecha");
    btnOrdenarFecha.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOrdenarFechaActionPerformed(evt);
      }
    });

    btnOrdenarId.setText("Id");
    btnOrdenarId.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOrdenarIdActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
    jLabel1.setText("Ordenar por: ");

    javax.swing.GroupLayout jpListadoVentasLayout = new javax.swing.GroupLayout(jpListadoVentas);
    jpListadoVentas.setLayout(jpListadoVentasLayout);
    jpListadoVentasLayout.setHorizontalGroup(
      jpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpListadoVentasLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(jpListadoVentasLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnOrdenarId)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnOrdenarFecha))
          .addComponent(jspListadoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(18, Short.MAX_VALUE))
    );
    jpListadoVentasLayout.setVerticalGroup(
      jpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jpListadoVentasLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jspListadoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnOrdenarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnOrdenarId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1))
        .addContainerGap(40, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Ventas Relizadas", jpListadoVentas);

    javax.swing.GroupLayout jpVentaLayout = new javax.swing.GroupLayout(jpVenta);
    jpVenta.setLayout(jpVentaLayout);
    jpVentaLayout.setHorizontalGroup(
      jpVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTabbedPane1)
    );
    jpVentaLayout.setVerticalGroup(
      jpVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTabbedPane1)
    );

    jTabbedContent.addTab("tab5", jpVenta);

    jpContent.add(jTabbedContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 570));

    getContentPane().add(jpContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 850, 530));

    pack();
  }// </editor-fold>//GEN-END:initComponents
  
  private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
    Usuario u = new Usuario();
    u.setUsuarioNombre(this.txtUsuario.getText().trim());
    u.setUsuarioContrasena(this.txtContrasena.getText().trim());
    
    if ( u.getUsuarioNombre().equals("") || u.getUsuarioContrasena().equals("") ) {
      JOptionPane.showMessageDialog(rootPane, "Los campos deben ser llenados", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    if ( !login.IniciarSesion( u ) ) {
      JOptionPane.showMessageDialog(rootPane, "Revisa las credenciales", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    this.pantallaLogin();
    this.txtUsuario.setText("");
    this.txtContrasena.setText("");
  }//GEN-LAST:event_btnLoginMouseClicked

  private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
    int op = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro/a de querer cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if ( op == 0 ) {
      login.cerrarSesion();
      this.pantallaLogin();
    }
  }//GEN-LAST:event_btnSalirMouseClicked

  private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
    this.jTabbedContent.setSelectedIndex(1);
    this.cargarReportes();
  }//GEN-LAST:event_btnInicioMouseClicked

  private void btnInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventarioMouseClicked
    this.jTabbedContent.setSelectedIndex(2);
    this.cargarTabla(jtInventario, modeloInventario, "Inventario", "");
  }//GEN-LAST:event_btnInventarioMouseClicked

  private void btnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseClicked
    this.jTabbedContent.setSelectedIndex(3);
    this.cargarTabla(jtBusqueda, modeloBusqueda, "", "");
    this.cargarTablaListadoVentas("FECHA");
  }//GEN-LAST:event_btnReportesMouseClicked

  private void btnGuardarInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarInventarioMouseClicked
    if ( !this.soloEntero(this.txtCantidadInventario.getText().trim() ) &&  !this.soloNumeros( this.txtPrecioInventario.getText().trim() ) ){
      JOptionPane.showMessageDialog(rootPane, "Revisa la información que intentas registrar", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if ( this.txtNombreInventario.getText().trim().isEmpty() ){
      JOptionPane.showMessageDialog(rootPane, "Revisa la información que intentas registrar", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }

    String nombre = this.txtNombreInventario.getText().trim();
    int cantidad = Integer.parseInt( this.txtCantidadInventario.getText().trim() );
    double precio = Double.parseDouble( this.txtPrecioInventario.getText().trim() );
    
    if ( cantidad == 0 || precio == 0.0 ){
      JOptionPane.showMessageDialog(rootPane, "Revisa la información que intentas registrar", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    Muebles mueble = new Muebles();
    mueble.setNombre(nombre);
    mueble.setCantidad(cantidad);
    mueble.setPrecio(precio);
    
    logica.registrarProducto(mueble);
    this.registrarInventario(mueble);
  }//GEN-LAST:event_btnGuardarInventarioMouseClicked

  private void btnCancelarInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarInventarioMouseClicked
    int op = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro/a de querer canelar la acción?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if ( op == 0 ) {
      this.limpiarCampos();
      this.botonesInventario();
    }
  }//GEN-LAST:event_btnCancelarInventarioMouseClicked

  private void btnEliminarInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarInventarioMouseClicked
    int op = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro/a de querer eliminar este artículo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if ( op == 0 ) {
      logica.EliminarProducto(id_seleccionado);
      this.limpiarCampos();
      this.botonesInventario();
      this.cargarTabla(jtInventario, modeloInventario, "Inventario", "");
    }
  }//GEN-LAST:event_btnEliminarInventarioMouseClicked

  private void jtInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtInventarioMouseClicked
    int index = this.jtInventario.getSelectedRow();
    String parametro = this.txtBusquedaInventario.getText().trim();
    
    this.cargarFormulario(index, parametro);
    this.id_seleccionado = logica.buscarProductoPorPosicion(index, parametro).getId();
    this.botonesInventario();
  }//GEN-LAST:event_jtInventarioMouseClicked

  private void txtBusquedaInventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaInventarioKeyReleased
    this.limpiarCampos();
    this.botonesInventario();
    String parametro = this.txtBusquedaInventario.getText().trim();
    this.cargarTabla(jtInventario, modeloInventario, "Inventario", parametro);
  }//GEN-LAST:event_txtBusquedaInventarioKeyReleased

  private void txtBusquedaVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaVentaKeyReleased
    String parametro = this.txtBusquedaVenta.getText().trim();
    this.cargarTabla(jtBusqueda, modeloBusqueda, "", parametro);
  }//GEN-LAST:event_txtBusquedaVentaKeyReleased

  private void btnEditarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarInventarioActionPerformed
    String nombre = this.txtNombreInventario.getText().trim();
    String cantidad = this.txtCantidadInventario.getText().trim();
    String precio = this.txtPrecioInventario.getText().trim();
    
    if ( nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty() ) {
      JOptionPane.showMessageDialog(rootPane, "Revisa la información que intentas registrar", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if ( !this.soloEntero(cantidad) || !this.soloNumeros(precio) ) {
      JOptionPane.showMessageDialog(rootPane, "Revisa la información que intentas registrar", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if ( Integer.parseInt(cantidad) == 0 || Double.valueOf(precio) == 0.0 ){
      JOptionPane.showMessageDialog(rootPane, "Revisa la información que intentas registrar", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    Muebles muebleEditado = new Muebles(
      id_seleccionado,
      nombre,
      Integer.parseInt(cantidad),
      Double.valueOf(precio)
    );
    
    logica.editarProducto(muebleEditado);
    this.cargarTabla(jtInventario, modeloInventario, "Inventario", "");
    
    this.id_seleccionado = 0;
    this.botonesInventario();
    this.limpiarCampos();
    
  }//GEN-LAST:event_btnEditarInventarioActionPerformed

  private void jtBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtBusquedaMouseClicked
    int index = this.jtBusqueda.getSelectedRow();
    String parametro = this.txtBusquedaVenta.getText().trim();
    
    Muebles mueble = logica.buscarProductoPorPosicion(index, parametro);
    this.muebleTemporal = new Muebles( mueble.getId(), mueble.getNombre(), mueble.getCantidad(), mueble.getPrecio() );
  }//GEN-LAST:event_jtBusquedaMouseClicked

  private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
    if ( ListadoTemporal.isEmpty() ){
      JOptionPane.showMessageDialog(rootPane, "No se ha realizado ninguna acción");
      return;
    }
    
    int op = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro/a de cancelar la venta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if ( op != 0 ) return;
    
    for ( Muebles restaurar : ListadoTemporal ) {
      Muebles muebleEditable = logica.buscarProductoPorId(restaurar.getId());
      int cantidadTemporal = muebleEditable.getCantidad();
      logica.editarProducto(new Muebles(
        muebleEditable.getId(),
        muebleEditable.getNombre(),
        cantidadTemporal + restaurar.getCantidad(),
        muebleEditable.getPrecio()
      ));
    }
    this.limpiarModeloVenta();
  }//GEN-LAST:event_btnCancelarVentaActionPerformed

  private void btnAgregarMuebleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMuebleActionPerformed
    if ( muebleTemporal.getId() == 0 ){
      JOptionPane.showMessageDialog(rootPane, "No se ha realizado ninguna acción");
      return;
    }
    
    String cantidad = this.txtCantidadVenta.getText().trim();
    
    // verificaciónes
    if ( this.muebleTemporal.getCantidad() == 0 ) return;
    if ( cantidad == null || cantidad.isEmpty() || !soloEntero(cantidad) ){
      JOptionPane.showMessageDialog(rootPane, "La Cantidad debe estár llena y es de tipo entero.");
      return;
    }
    if ( this.muebleTemporal.getCantidad() < Integer.parseInt(cantidad) ) {
      JOptionPane.showMessageDialog(rootPane, "La cantidad supera las existencias en el inventario ( "+this.muebleTemporal.getCantidad()+" )\n"
                                               + "asegurate de compara más.");
      return;
    }
    
    // Calculos
    this.muebleTemporal.setCantidad( Integer.parseInt(cantidad) );
    this.muebleTemporal.setPrecio( Integer.parseInt(cantidad) * this.muebleTemporal.getPrecio() );
        
    // Total
    this.contadorTotal += this.muebleTemporal.getPrecio();
    this.jlTotalSum.setText( ""+this.contadorTotal );
    
    // manejo de lista
    if ( !this.sumarRepetidos() ) {
      this.ListadoTemporal.add(muebleTemporal);
    }
    
    // Restando las cantidades vendidas del inventario
    Muebles muebleEditable = logica.buscarProductoPorId(muebleTemporal.getId());
    int cantidadTemporal = muebleEditable.getCantidad();
    logica.editarProducto(new Muebles(
      muebleEditable.getId(),
      muebleEditable.getNombre(),
      cantidadTemporal - muebleTemporal.getCantidad(),
      muebleTemporal.getPrecio()
    ));
    
    // Procesos de restauración
    this.cargarTabla(jtBusqueda, modeloBusqueda, "", "");
    this.cargarTablaVenta();    
    this.jtBusqueda.clearSelection();
    this.muebleTemporal = new Muebles();
    this.txtCantidadVenta.setText("1");
  }//GEN-LAST:event_btnAgregarMuebleActionPerformed

  private void btnRelizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelizarVentaActionPerformed
    if ( ListadoTemporal.isEmpty() ){
      JOptionPane.showMessageDialog(rootPane, "No se ha realizado ninguna acción");
      return;
    }
    
    int op = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro/a de realizar la venta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if ( op != 0 ) return;

    // Ralizar Venta
    Venta registrar = new Venta();
    registrar.setMuebles((ArrayList<Muebles>) ListadoTemporal.clone());
    registrar.setUsario(login.getUsuario());
    registrar.setCosto(contadorTotal);
    logica.registrarVenta( registrar );
    
    // Limpiar datos
    this.limpiarModeloVenta();
    
    this.cargarTablaListadoVentas("FECHA");
  }//GEN-LAST:event_btnRelizarVentaActionPerformed

  private void jtVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVentaMouseClicked
    Muebles m = ListadoTemporal.get( this.jtVenta.getSelectedRow() );
    muebleEliminar = new Muebles( m.getId(), m.getNombre(), m.getCantidad(), m.getPrecio() );
  }//GEN-LAST:event_jtVentaMouseClicked

  private void btnEliminarMuebleVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMuebleVentaActionPerformed
    // validación
    if ( muebleEliminar.getId() == 0 ) {
      JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún artículo", "Alerta", JOptionPane.WARNING_MESSAGE);
      return;
    }
    
    int op = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro/a de querer eliminar este artículo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if ( op != 0 ) return;
    
    ArrayList<Muebles> temporal = new ArrayList<>();
    temporal.addAll(ListadoTemporal);
        
    // Aumenta el stock del mueble en inventario
    Muebles muebleEditable = logica.buscarProductoPorId(muebleEliminar.getId());
    int cantidadTemporal = muebleEditable.getCantidad();
    logica.editarProducto(new Muebles(
      muebleEditable.getId(),
      muebleEditable.getNombre(),
      cantidadTemporal + muebleEliminar.getCantidad(),
      muebleEditable.getPrecio()
    ));
        
    // Elimina articulo del recibo
    ListadoTemporal.clear();
    for ( Muebles m : temporal ) {
      if ( m.getId() != muebleEditable.getId() )
        ListadoTemporal.add(m);
    }
    
    // Total
    this.contadorTotal -= muebleEliminar.getPrecio();
    this.jlTotalSum.setText( ""+this.contadorTotal );
    
    // Restauración
    this.jtVenta.clearSelection();
    muebleEliminar = new Muebles();
    this.cargarTablaVenta();
  }//GEN-LAST:event_btnEliminarMuebleVentaActionPerformed

  private void btnOrdenarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarFechaActionPerformed
    this.cargarTablaListadoVentas("FECHA");
  }//GEN-LAST:event_btnOrdenarFechaActionPerformed

  private void btnOrdenarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarIdActionPerformed
    this.cargarTablaListadoVentas("ID");
  }//GEN-LAST:event_btnOrdenarIdActionPerformed

  private void jtListadoVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtListadoVentasMouseClicked
    
    int index = Integer.parseInt( this.jtListadoVentas.getValueAt(jtListadoVentas.getSelectedRow(), 0).toString() );
    Venta recibo = new Venta();
    
    for ( Venta ven : logica.listadoVenta() ) {
      if ( ven.getId() == index ){
        recibo.setId(ven.getId());
        recibo.setFecha_venta(ven.getFecha_venta());
        recibo.setUsario(ven.getUsario());
        recibo.setMuebles(ven.getMuebles());
        recibo.setCosto(ven.getCosto());
      }
    }
    
    String imprimir = "#"+recibo.getId()+"\n"
                    + "$"+recibo.getCosto()+"\n";
    
    for ( Muebles mueble : recibo.getMuebles() ) {
      imprimir += "\n Nombre: "+mueble.getNombre()+""
                + "\n Cantidad: "+mueble.getCantidad()+""
                + "\n Monto: "+mueble.getPrecio()+"\n";
    }
    JOptionPane.showMessageDialog(rootPane, imprimir.toString());
  }//GEN-LAST:event_jtListadoVentasMouseClicked

  /**************************************************
   *                    Código                      *
   * ************************************************/
    
  private void pantallaLogin () {
    if ( login.isSesion() ){
      this.jlSesion.setText(login.usuarioSesion());
      this.jTabbedContent.setSelectedIndex(1);
    } else {
      this.jlSesion.setText("");
      this.jTabbedContent.setSelectedIndex(0);
    }
    this.jpMenu.setVisible( login.isSesion() );
  }
  
  private boolean soloNumeros ( String number ) {
    Pattern pat = Pattern.compile("[0-9]{1,999999999}.{0,1}[0-9]{0,999999999}");
    Matcher mat = pat.matcher(number);
    return mat.matches();
  }
  
  private boolean soloEntero ( String number ) {
    Pattern pat = Pattern.compile("[0-9]{1,999999999}");
    Matcher mat = pat.matcher(number);
    return mat.matches();
  }
  
  private boolean sumarRepetidos () {
    for ( int  i = 0; i < ListadoTemporal.size(); i++ ) {
      if ( ListadoTemporal.get(i).getId() == muebleTemporal.getId() ) {
        double pre = logica.buscarProductoPorId( ListadoTemporal.get(i).getId() ).getPrecio();
        ListadoTemporal.get(i).setCantidad(ListadoTemporal.get(i).getCantidad() + 1);
        ListadoTemporal.get(i).setPrecio(ListadoTemporal.get(i).getPrecio() + pre);
        return true;
      }
    }
    return false;
  }
  
  private void botonesInventario (){
    this.btnCancelarInventario.setVisible( this.id_seleccionado > 0 );
    this.btnEditarInventario.setVisible( this.id_seleccionado > 0 );
    this.btnEliminarInventario.setVisible( this.id_seleccionado > 0 );
    this.btnGuardarInventario.setVisible( this.id_seleccionado == 0 );
  }
  
  private void limpiarCampos (){
    this.id_seleccionado = 0;
    this.jtInventario.clearSelection();
    this.txtNombreInventario.setText("");
    this.txtCantidadInventario.setText("0");
    this.txtPrecioInventario.setText("0");
  }
  
  private void limpiarTabla( JTable jtable, DefaultTableModel modelo ){
    for (int i = 0; i < jtable.getRowCount(); i++) {
      modelo.removeRow(i);
      i-=1;
    }
  }
  
  private void limpiarModeloVenta (){
    this.contadorTotal = 0;
    this.ListadoTemporal.clear();
    this.muebleTemporal = new Muebles();
    this.muebleEliminar = new Muebles();
    
    this.limpiarTabla(jtVenta, modeloVenta);
    this.limpiarTabla(jtBusqueda, modeloBusqueda);
    this.cargarTabla(jtBusqueda, modeloBusqueda, "", "");
    
    this.jtBusqueda.clearSelection();
    this.jtVenta.clearSelection();
    
    this.txtBusquedaVenta.setText("");
    this.jlTotalSum.setText( ""+this.contadorTotal );
    this.txtCantidadVenta.setText("1");
  }
  
  private void cargarTablaVenta () {
    this.limpiarTabla(jtVenta, modeloVenta);
    for ( Muebles mueble : ListadoTemporal ) {
      modeloVenta.addRow( new String[] { 
        mueble.getNombre(),
        String.valueOf(mueble.getCantidad()),
        String.valueOf(mueble.getPrecio())
      });
    }
    jtVenta.setModel(modeloVenta);
  }
  
  private void cargarTablaListadoVentas ( String parametro ) {
    this.limpiarTabla(jtListadoVentas, modeloListadoVentas);
    
    if ( parametro.equals("FECHA") ) {
      for ( int i = logica.listadoVenta().size()-1; i >= 0; i-- ) {
        modeloListadoVentas.addRow( new String[] { 
          String.valueOf(logica.listadoVenta().get(i).getId()),
          logica.listadoVenta().get(i).getUsario(),
          String.valueOf(logica.listadoVenta().get(i).getCosto()),
          logica.listadoVenta().get(i).getFecha_venta()
        });
      }
    }
    if ( parametro.equals("ID") ) {
      for ( Venta venta : logica.listadoVenta() ) {
        modeloListadoVentas.addRow( new String[] { 
          String.valueOf(venta.getId()),
          venta.getUsario(),
          String.valueOf(venta.getCosto()),
          venta.getFecha_venta()
        });
      }
    }
    
    jtListadoVentas.setModel(modeloListadoVentas);
  }
  
  private void cargarTabla ( JTable jtable, DefaultTableModel modelo, String pantalla,String parametro) {
    this.limpiarTabla(jtable, modelo);
    for ( Muebles mueble : logica.listadoMuebles( parametro ) ) {
      if ( mueble.getCantidad() > 0 ) {
        if ( pantalla.equals("Inventario") ) {
          modelo.addRow( new String[] { 
            String.valueOf(mueble.getId()),
            mueble.getNombre(),
            String.valueOf(mueble.getCantidad()),
            String.valueOf(mueble.getPrecio())
          });
        } else {
          modelo.addRow( new String[] { 
            String.valueOf(mueble.getId()),
            mueble.getNombre(),
            String.valueOf(mueble.getPrecio())
          });
        }
      } else {
        if ( pantalla.equals("Inventario") ) {
          modelo.addRow( new String[] { 
            String.valueOf(mueble.getId()),
            mueble.getNombre(),
            "0",
            String.valueOf(mueble.getPrecio())
          });
        }
      }
    }
    jtable.setModel(modelo);
  }
  
  private void cargarReportes () {
    
    this.jtpReporteVenta.setText(logica.GenerarReporteVentas());
    this.jtpReporteInventario.setText(logica.GenerarReporteInventario());
    
  }
  
  private void cargarFormulario ( int dato, String parametro) {
    Muebles mueble = logica.buscarProductoPorPosicion(dato, parametro);
    this.txtNombreInventario.setText( mueble.getNombre() );
    this.txtCantidadInventario.setText( String.valueOf(mueble.getCantidad()) );
    this.txtPrecioInventario.setText( String.valueOf(mueble.getPrecio()) );
  }
    
  private void registrarInventario ( Muebles mueble ) {
    
    modeloInventario.addRow( new String[] { 
      String.valueOf(mueble.getId()),
      mueble.getNombre(),
      String.valueOf(mueble.getCantidad()),
      String.valueOf(mueble.getPrecio())
    });
    
    this.jtInventario.setModel(modeloInventario);
    this.limpiarCampos();
  }
    
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Windows".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        Main main = new Main();
        main.setLocation(250, 150);
        main.setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnAgregarMueble;
  private javax.swing.JButton btnCancelarInventario;
  private javax.swing.JButton btnCancelarVenta;
  private javax.swing.JButton btnEditarInventario;
  private javax.swing.JButton btnEliminarInventario;
  private javax.swing.JButton btnEliminarMuebleVenta;
  private javax.swing.JButton btnGuardarInventario;
  private javax.swing.JButton btnInicio;
  private javax.swing.JButton btnInventario;
  private javax.swing.JButton btnLogin;
  private javax.swing.JButton btnOrdenarFecha;
  private javax.swing.JButton btnOrdenarId;
  private javax.swing.JButton btnRelizarVenta;
  private javax.swing.JButton btnReportes;
  private javax.swing.JButton btnSalir;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JTabbedPane jTabbedContent;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JLabel jlBusquedaInventario;
  private javax.swing.JLabel jlBusquedaVenta;
  private javax.swing.JLabel jlCantidadInventario;
  private javax.swing.JLabel jlContrasena;
  private javax.swing.JLabel jlNombreInventatio;
  private javax.swing.JLabel jlPrecioInventario;
  private javax.swing.JLabel jlSesion;
  private javax.swing.JLabel jlTitleInicio;
  private javax.swing.JLabel jlTitleInventario;
  private javax.swing.JLabel jlTotalSum;
  private javax.swing.JLabel jlTotalTitle;
  private javax.swing.JLabel jlUsuario;
  private javax.swing.JPanel jpAccionesInvantario;
  private javax.swing.JPanel jpContent;
  private javax.swing.JPanel jpInicio;
  private javax.swing.JPanel jpInventario;
  private javax.swing.JPanel jpListadoVentas;
  private javax.swing.JPanel jpLogin;
  private javax.swing.JPanel jpMenu;
  private javax.swing.JPanel jpOpciones;
  private javax.swing.JPanel jpRegistrarVenta;
  private javax.swing.JPanel jpReporteInventario;
  private javax.swing.JPanel jpReporteVenta;
  private javax.swing.JPanel jpVenta;
  private javax.swing.JScrollPane jspBusqueda;
  private javax.swing.JScrollPane jspListadoVentas;
  private javax.swing.JScrollPane jspReporteInventario;
  private javax.swing.JScrollPane jspReporteVenta;
  private javax.swing.JScrollPane jspVenta;
  private javax.swing.JTable jtBusqueda;
  private javax.swing.JTable jtInventario;
  private javax.swing.JTable jtListadoVentas;
  private javax.swing.JTable jtVenta;
  private javax.swing.JTextPane jtpReporteInventario;
  private javax.swing.JTextPane jtpReporteVenta;
  private javax.swing.JTextField txtBusquedaInventario;
  private javax.swing.JTextField txtBusquedaVenta;
  private javax.swing.JTextField txtCantidadInventario;
  private javax.swing.JTextField txtCantidadVenta;
  private javax.swing.JTextField txtContrasena;
  private javax.swing.JTextField txtNombreInventario;
  private javax.swing.JTextField txtPrecioInventario;
  private javax.swing.JTextField txtUsuario;
  // End of variables declaration//GEN-END:variables
}
