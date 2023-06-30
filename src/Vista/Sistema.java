package Vista;

import Estructura.Cliente;
import Estructura.ClienteDAO;
import Estructura.ComboProveedor;
import Estructura.Conductor;
import Estructura.ConductorDAO;
import Estructura.Empresa;
import Estructura.Detalle;
import Estructura.DetalleEg;
import Estructura.Eventos;
import Estructura.LoginDAO;
import Estructura.Producto;
import Estructura.ProductoDAO;
import Estructura.Proveedor;
import Estructura.ProveedorDAO;
import Estructura.Vehiculo;
import Estructura.VehiculoDAO;
import Estructura.Facturacion;
import Estructura.FacturacionDAO;
import Estructura.EmicionGuiaDAO;
import Estructura.EmisionGuia;
import Estructura.EmpresaDAO;
import Estructura.Login;
import Reportes.Grafico;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhair
 */

public final class Sistema extends javax.swing.JFrame {
    Date fechaFacturacion = new Date();
    Date fechaEg = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaFacturacion);
    
    Cliente cl = new Cliente();
    ClienteDAO cliDao = new ClienteDAO();
    
    Conductor cr = new Conductor();
    ConductorDAO conductDao = new ConductorDAO();
    
    Vehiculo vl = new Vehiculo();
    VehiculoDAO vehicDao = new VehiculoDAO();
    
    Proveedor pr = new Proveedor();
    ProveedorDAO PrDao = new ProveedorDAO();
   
    Producto pro = new Producto();
    ProductoDAO proDao = new ProductoDAO();
    
    Facturacion fact = new Facturacion();
    FacturacionDAO factDao = new FacturacionDAO();
    
    EmisionGuia emisGuia = new EmisionGuia();
    EmicionGuiaDAO emisGuiaDao = new EmicionGuiaDAO();
    
    Detalle Dv = new Detalle();
    DetalleEg DvEg = new DetalleEg();
    
    Empresa empres = new Empresa();
    EmpresaDAO empresDao = new EmpresaDAO();
    
    Eventos event = new Eventos();
    
    Login lg = new Login();
    LoginDAO login = new LoginDAO();
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;
    

    public Sistema() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("img/LogoPrincipal.jpg")).getImage());
    }
    public Sistema (Login priv){
        initComponents();
        //Titulo Sistema
        this.setTitle("MARVISUR EXPRESS ");
        
        //Ubicacion Sistema
        this.setLocationRelativeTo(null);
        
        //txt Id ocultos
        Midate.setDate(fechaFacturacion);
        Midate1.setDate(fechaEg);
        txtIdCliente.setVisible(false);
        txtIdEnvio.setVisible(false);
        txtIdPro.setVisible(false);
        txtIdproducto.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtIdConfig.setVisible(false);
        txtIdCV.setVisible(false);
        txtIdCG.setVisible(false);
        txtIdProEg.setVisible(false);
        txtIdConductorEg.setVisible(false);
        txtIdVehiculo.setVisible(false);
        txtIdVG.setVisible(false);
        txtIdConductor.setVisible(false);
        ListarEmpresa();
        //Permisos Asistente
        if (priv.getRol().equals("Asistente")) {
            //btnProductos.setEnabled(false);
            btnProveedor.setEnabled(false);
            btnConductor.setEnabled(false);
            btnVehiculo.setEnabled(false);
            btnEmpresa.setEnabled(false);
            MenuUsuarios.setEnabled(false);
            MenuHistorialEnvios.setEnabled(false);
            LabelVendedor.setText(priv.getNombre());
        }else{
            LabelVendedor.setText(priv.getNombre());
        }
    }
    //Listar Cliente
    public void ListarCliente() {
        List<Cliente> ListarCl = cliDao.ListarCliente();
        modelo = (DefaultTableModel) TableCliente.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getId();
            ob[1] = ListarCl.get(i).getDni();
            ob[2] = ListarCl.get(i).getNombre();
            ob[3] = ListarCl.get(i).getTelefono();
            ob[4] = ListarCl.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TableCliente.setModel(modelo);
    }

    //Listar Conductor
    public void ListarConductor() {
        List<Conductor> ListarCR = conductDao.ListarConductor();
        modelo = (DefaultTableModel) TableConductor.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < ListarCR.size(); i++) {
            ob[0] = ListarCR.get(i).getId();
            ob[1] = ListarCR.get(i).getDni();
            ob[2] = ListarCR.get(i).getNombre();
            ob[3] = ListarCR.get(i).getLicencia();
            ob[4] = ListarCR.get(i).getDireccion();
            ob[5] = ListarCR.get(i).getTelefono();
            ob[6] = ListarCR.get(i).getVehiculoCond();
            modelo.addRow(ob);
        }
        TableConductor.setModel(modelo);
    }
    //Listar Vehiculo
    public void ListarVehiculo() {
        List<Vehiculo> ListarVl = vehicDao.ListarVehiculo();
        modelo = (DefaultTableModel) TableVehiculo.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarVl.size(); i++) {
            ob[0] = ListarVl.get(i).getId();
            ob[1] = ListarVl.get(i).getPlaca();
            ob[2] = ListarVl.get(i).getMarca();
            ob[3] = ListarVl.get(i).getColor();
            ob[4] = ListarVl.get(i).getConfiguracion();
            modelo.addRow(ob);
        }
        TableVehiculo.setModel(modelo);
    }

    //Listar proveedor
    public void ListarProveedor() {
        List<Proveedor> ListarPr = PrDao.ListarProveedor();
        modelo = (DefaultTableModel) TableProveedor.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getId();
            ob[1] = ListarPr.get(i).getRuc();
            ob[2] = ListarPr.get(i).getNombre();
            ob[3] = ListarPr.get(i).getTelefono();
            ob[4] = ListarPr.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TableProveedor.setModel(modelo);
    }
    
    //Listar usuarios
    public void ListarUsuarios() {
        List<Login> Listar = login.ListarUsuarios();
        modelo = (DefaultTableModel) TableUsuarios.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < Listar.size(); i++) {
            ob[0] = Listar.get(i).getId();
            ob[1] = Listar.get(i).getNombre();
            ob[2] = Listar.get(i).getCorreo();
            ob[3] = Listar.get(i).getRol();
            modelo.addRow(ob);
        }
        TableUsuarios.setModel(modelo);
    }
    
    //Listar Producto
    public void ListarProductos() {
        List<Producto> ListarPro = proDao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        Object[] ob = new Object[11];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getNombre();
            ob[3] = ListarPro.get(i).getProveedorPro();
            ob[4] = ListarPro.get(i).getClientePro();
            ob[5] = ListarPro.get(i).getStock();
            ob[6] = ListarPro.get(i).getPrecio();
            ob[7] = ListarPro.get(i).getVolumen();
            ob[8] = ListarPro.get(i).getPeso();
            ob[9] = ListarPro.get(i).getOrigenPro();
            ob[10] = ListarPro.get(i).getDestinoPro();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);
    }

    //Listar Empresa
    public void ListarEmpresa() {
        empres = empresDao.BuscarDatos();
        txtIdConfig.setText("" + empres.getId());
        txtRucEmpresa.setText("" + empres.getRuc());
        txtNombreEmpresa.setText("" + empres.getNombre());
        txtTelefonoEmpresa.setText("" + empres.getTelefono());
        txtDireccionEmpresa.setText("" + empres.getDireccion());
        txtMensajeEmpresa.setText("" + empres.getMensaje());
    }

    //Listar envios
    public void ListarEnvios() {
        List<Facturacion> ListarVenta = factDao.ListarFactura();
        modelo = (DefaultTableModel) TableEnvios.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarVenta.size(); i++) {
            ob[0] = ListarVenta.get(i).getId();
            ob[1] = ListarVenta.get(i).getNombre_cli();
            ob[2] = ListarVenta.get(i).getVendedor();
            ob[3] = ListarVenta.get(i).getTotal();
            modelo.addRow(ob);
        }
        TableEnvios.setModel(modelo);
    }

    //Limpiar tablas
    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
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

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        PanelBotones = new javax.swing.JPanel();
        LOGO = new javax.swing.JLabel();
        LabelVendedor = new javax.swing.JLabel();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnEmpresa = new javax.swing.JButton();
        tipo = new javax.swing.JLabel();
        btnVehiculo = new javax.swing.JButton();
        btnConductor = new javax.swing.JButton();
        btnNuevaVenta1 = new javax.swing.JButton();
        lblLinea = new javax.swing.JPanel();
        lblEncabezadoPrincipal = new javax.swing.JLabel();
        panelCubre = new javax.swing.JPanel();
        ContenedorPaneles = new javax.swing.JTabbedPane();
        Facturacion = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoFacturacion = new javax.swing.JTextField();
        txtNombreFacturacion = new javax.swing.JTextField();
        txtCantidadFacturacion = new javax.swing.JTextField();
        txtPrecioFacturacion = new javax.swing.JTextField();
        txtStockDisponible = new javax.swing.JTextField();
        jScrollPane1dd = new javax.swing.JScrollPane();
        TableFacturacion = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRucClienteFacturacion = new javax.swing.JTextField();
        txtNombreClienteFacturacion = new javax.swing.JTextField();
        btnGenerarFacturacion = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        btnGraficar = new javax.swing.JButton();
        Midate = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtIdPro = new javax.swing.JTextField();
        txtIdCV = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtOrigenFacturacion = new javax.swing.JTextField();
        txtDestinoFacturacion = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtDireccionFacturacion = new javax.swing.JTextField();
        EmisionGuia = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtCodigoEg = new javax.swing.JTextField();
        txtDescripcionEg = new javax.swing.JTextField();
        txtPrecioEg = new javax.swing.JTextField();
        jScrollPane1dd1 = new javax.swing.JScrollPane();
        TableEg = new javax.swing.JTable();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtRucEg = new javax.swing.JTextField();
        txtNombreClienteEg = new javax.swing.JTextField();
        btnGenerarEg = new javax.swing.JButton();
        Midate1 = new com.toedter.calendar.JDateChooser();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel74 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txtNombreConductorEg = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtLicenciaConductorEg = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txtPlacaEg = new javax.swing.JTextField();
        txtIdConductorEg = new javax.swing.JTextField();
        txtIdCG = new javax.swing.JTextField();
        txtIdProEg = new javax.swing.JTextField();
        txtOrigenEg = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txtDestinoEg = new javax.swing.JTextField();
        txtDireccionClienteEg = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        txtMarcaEg = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txtConfigVehicularEg = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        txtVolumenEg = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        txtPesoEg = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        txtIdVG = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txtDniCEG = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtProveedorProEg = new javax.swing.JTextField();
        btnBuscarC = new javax.swing.JButton();
        btnBuscarP = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        btnBuscarC1 = new javax.swing.JButton();
        btnBuscarV = new javax.swing.JButton();
        Cliente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDirecionCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        Proveedor = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtRucProveedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNombreproveedor = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDireccionProveedor = new javax.swing.JTextField();
        btnguardarProveedor = new javax.swing.JButton();
        btnEditarProveedor = new javax.swing.JButton();
        btnNuevoProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        Vehiculo = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableVehiculo = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        txtPlacaVehiculo = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtMarcaVehiculo = new javax.swing.JTextField();
        txtColorVehiculo = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtConfiguracionVehiculo = new javax.swing.JTextField();
        txtIdVehiculo = new javax.swing.JTextField();
        btnGuardarVehiculo = new javax.swing.JButton();
        btnEditarVehiculo = new javax.swing.JButton();
        btnEliminarVehiculo = new javax.swing.JButton();
        btnNuevoVehiculo = new javax.swing.JButton();
        Conductor = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableConductor = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtDniConductor = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtNombreConductor = new javax.swing.JTextField();
        txtLicenciaConductor = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtDireccionConductor = new javax.swing.JTextField();
        txtIdConductor = new javax.swing.JTextField();
        btnGuardarConductor = new javax.swing.JButton();
        btnEditarConductor = new javax.swing.JButton();
        btnEliminarConductor = new javax.swing.JButton();
        btnNuevoConductor = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        txtTelefonoConductor = new javax.swing.JTextField();
        txtVehiculoCond = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Producto = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtOrigenPro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCantPro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtPrecioPro = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cbxProveedorPro = new javax.swing.JComboBox<>();
        btnGuardarProducto = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnNuevoProducto = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtPesoPro = new javax.swing.JTextField();
        txtVolumenPro = new javax.swing.JTextField();
        txtIdproducto = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtNombrePro = new javax.swing.JTextField();
        txtDestPro = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        Empresa = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtRucEmpresa = new javax.swing.JTextField();
        txtDireccionEmpresa = new javax.swing.JTextField();
        txtMensajeEmpresa = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txtIdConfig = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        txtNombreEmpresa = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        btnActualizarEmpresa = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        Usuarios = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnIniciar = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        cbxRol = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableUsuarios = new javax.swing.JTable();
        Envios = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableEnvios = new javax.swing.JTable();
        btnPDFEnvios = new javax.swing.JButton();
        txtIdEnvio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        btnMenuBar = new javax.swing.JMenu();
        MenuUsuarios = new javax.swing.JMenuItem();
        MenuHistorialEnvios = new javax.swing.JMenuItem();
        btnRegresar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBotones.setBackground(new java.awt.Color(0, 102, 102));

        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoPrincipalMarvisur.png"))); // NOI18N

        LabelVendedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelVendedor.setForeground(new java.awt.Color(255, 255, 255));
        LabelVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelVendedor.setText("PROGRAMADOR");

        btnNuevaVenta.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoFacturacion.png"))); // NOI18N
        btnNuevaVenta.setText("Facturación");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.setFocusable(false);
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoClientes.png"))); // NOI18N
        btnClientes.setText(" Cliente");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.setFocusable(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnProveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnProveedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoProveedor.png"))); // NOI18N
        btnProveedor.setText("Proveedor");
        btnProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedor.setFocusable(false);
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnProductos.setBackground(new java.awt.Color(255, 255, 255));
        btnProductos.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoEncomienda.png"))); // NOI18N
        btnProductos.setText("Ingreso de carga");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.setFocusable(false);
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
        });
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        btnEmpresa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoEmpresa.png"))); // NOI18N
        btnEmpresa.setText("Empresa");
        btnEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmpresa.setFocusable(false);
        btnEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpresaActionPerformed(evt);
            }
        });

        tipo.setForeground(new java.awt.Color(255, 255, 255));

        btnVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        btnVehiculo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoVehiculo.png"))); // NOI18N
        btnVehiculo.setText(" Vehículo");
        btnVehiculo.setActionCommand("Vehiculo");
        btnVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVehiculo.setFocusable(false);
        btnVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculoActionPerformed(evt);
            }
        });

        btnConductor.setBackground(new java.awt.Color(255, 255, 255));
        btnConductor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnConductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoConductor.png"))); // NOI18N
        btnConductor.setText(" Conductor");
        btnConductor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConductor.setFocusable(false);
        btnConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConductorActionPerformed(evt);
            }
        });

        btnNuevaVenta1.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnNuevaVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoGuia.png"))); // NOI18N
        btnNuevaVenta1.setText("Emisión de Guía");
        btnNuevaVenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta1.setFocusable(false);
        btnNuevaVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVenta1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBotonesLayout = new javax.swing.GroupLayout(PanelBotones);
        PanelBotones.setLayout(PanelBotonesLayout);
        PanelBotonesLayout.setHorizontalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevaVenta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVehiculo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConductor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelBotonesLayout.createSequentialGroup()
                        .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipo)
                .addGap(16, 16, 16))
            .addGroup(PanelBotonesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelBotonesLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(LOGO))
                    .addComponent(LabelVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelBotonesLayout.setVerticalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotonesLayout.createSequentialGroup()
                .addGroup(PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelBotonesLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(tipo))
                    .addGroup(PanelBotonesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LOGO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNuevaVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        getContentPane().add(PanelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 640));
        PanelBotones.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout lblLineaLayout = new javax.swing.GroupLayout(lblLinea);
        lblLinea.setLayout(lblLineaLayout);
        lblLineaLayout.setHorizontalGroup(
            lblLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );
        lblLineaLayout.setVerticalGroup(
            lblLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(lblLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 990, 10));

        lblEncabezadoPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/EncabezadoPrincipalMarvisur.png"))); // NOI18N
        getContentPane().add(lblEncabezadoPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, -20, 1000, 170));

        javax.swing.GroupLayout panelCubreLayout = new javax.swing.GroupLayout(panelCubre);
        panelCubre.setLayout(panelCubreLayout);
        panelCubreLayout.setHorizontalGroup(
            panelCubreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelCubreLayout.setVerticalGroup(
            panelCubreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(panelCubre, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 140, 50));

        ContenedorPaneles.setBackground(new java.awt.Color(255, 255, 255));

        Facturacion.setBackground(java.awt.SystemColor.controlHighlight);
        Facturacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("CÓDIGO");
        Facturacion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Descripción:");
        Facturacion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 90, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Cantidad:");
        Facturacion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Precio:");
        Facturacion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Disponible");
        Facturacion.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        txtCodigoFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoFacturacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoFacturacionKeyTyped(evt);
            }
        });
        Facturacion.add(txtCodigoFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 30));

        txtNombreFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreFacturacionKeyTyped(evt);
            }
        });
        Facturacion.add(txtNombreFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 190, 30));

        txtCantidadFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadFacturacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadFacturacionKeyTyped(evt);
            }
        });
        Facturacion.add(txtCantidadFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 60, 30));

        txtPrecioFacturacion.setEditable(false);
        Facturacion.add(txtPrecioFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 60, 30));

        txtStockDisponible.setEditable(false);
        txtStockDisponible.setBackground(new java.awt.Color(204, 204, 204));
        Facturacion.add(txtStockDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 60, 30));

        TableFacturacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIPCION", "CANT", "PRECIO", "TOTAL", "CLIENTE", "DIRECCION", "ORIGEN", "DESTINO"
            }
        ));
        jScrollPane1dd.setViewportView(TableFacturacion);
        if (TableFacturacion.getColumnModel().getColumnCount() > 0) {
            TableFacturacion.getColumnModel().getColumn(0).setPreferredWidth(5);
            TableFacturacion.getColumnModel().getColumn(1).setPreferredWidth(70);
            TableFacturacion.getColumnModel().getColumn(2).setPreferredWidth(20);
            TableFacturacion.getColumnModel().getColumn(3).setPreferredWidth(40);
            TableFacturacion.getColumnModel().getColumn(4).setPreferredWidth(40);
            TableFacturacion.getColumnModel().getColumn(5).setPreferredWidth(50);
            TableFacturacion.getColumnModel().getColumn(6).setPreferredWidth(50);
            TableFacturacion.getColumnModel().getColumn(7).setPreferredWidth(40);
            TableFacturacion.getColumnModel().getColumn(8).setPreferredWidth(40);
        }

        Facturacion.add(jScrollPane1dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 630, 330));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("DNI/RUC");
        Facturacion.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Nombre:");
        Facturacion.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, 10));

        txtRucClienteFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucClienteFacturacionKeyPressed(evt);
            }
        });
        Facturacion.add(txtRucClienteFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 110, 30));

        txtNombreClienteFacturacion.setEditable(false);
        Facturacion.add(txtNombreClienteFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 170, 30));

        btnGenerarFacturacion.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerarFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoImprimir.png"))); // NOI18N
        btnGenerarFacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarFacturacionActionPerformed(evt);
            }
        });
        Facturacion.add(btnGenerarFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 430, 60, 45));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoVenta.png"))); // NOI18N
        jLabel10.setText("TOTAL A PAGAR:");
        Facturacion.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, -1, -1));

        LabelTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LabelTotal.setText("...");
        Facturacion.add(LabelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 90, -1));

        btnGraficar.setBackground(new java.awt.Color(255, 255, 255));
        btnGraficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGraficoCircular.png"))); // NOI18N
        btnGraficar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });
        Facturacion.add(btnGraficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 50, 40));
        Facturacion.add(Midate, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 150, 30));

        jLabel11.setText("Seleccionar:");
        Facturacion.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, -1, -1));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(153, 153, 153));
        jLabel42.setText("Encomienda");
        Facturacion.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        Facturacion.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 680, 10));

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 153));
        jLabel48.setText("Cliente");
        Facturacion.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        Facturacion.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 300, 10));
        Facturacion.add(txtIdPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 20, -1));
        Facturacion.add(txtIdCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 20, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("ORIGEN");
        Facturacion.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        txtOrigenFacturacion.setEditable(false);
        txtOrigenFacturacion.setText("ABANCAY");
        Facturacion.add(txtOrigenFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 110, 30));

        txtDestinoFacturacion.setEditable(false);
        Facturacion.add(txtDestinoFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 110, 30));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("DESTINO");
        Facturacion.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 80, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel43.setText("Dirección:");
        Facturacion.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, 10));

        txtDireccionFacturacion.setEditable(false);
        Facturacion.add(txtDireccionFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 300, 30));

        ContenedorPaneles.addTab("1", Facturacion);

        EmisionGuia.setBackground(java.awt.SystemColor.controlHighlight);
        EmisionGuia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("CÓDIGO:");
        EmisionGuia.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("DESCRIPCION:");
        EmisionGuia.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("FECHA:");
        EmisionGuia.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        EmisionGuia.add(txtCodigoEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 90, 30));

        txtDescripcionEg.setEditable(false);
        EmisionGuia.add(txtDescripcionEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 160, 30));

        txtPrecioEg.setEditable(false);
        EmisionGuia.add(txtPrecioEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 70, 30));

        TableEg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESCRIPCION", "CONDUCTOR", "PLACA", "ORIGEN", "DESTINO", "PROVEEDOR", "CLIENTE", "PRECIO"
            }
        ));
        jScrollPane1dd1.setViewportView(TableEg);
        if (TableEg.getColumnModel().getColumnCount() > 0) {
            TableEg.getColumnModel().getColumn(0).setPreferredWidth(4);
            TableEg.getColumnModel().getColumn(1).setPreferredWidth(70);
            TableEg.getColumnModel().getColumn(2).setPreferredWidth(40);
            TableEg.getColumnModel().getColumn(3).setPreferredWidth(40);
            TableEg.getColumnModel().getColumn(4).setPreferredWidth(40);
            TableEg.getColumnModel().getColumn(5).setPreferredWidth(40);
            TableEg.getColumnModel().getColumn(6).setPreferredWidth(10);
            TableEg.getColumnModel().getColumn(7).setPreferredWidth(12);
            TableEg.getColumnModel().getColumn(8).setPreferredWidth(4);
        }

        EmisionGuia.add(jScrollPane1dd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 570, 200));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel68.setText("DNI/RUC:");
        EmisionGuia.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel69.setText("NOMBRE:");
        EmisionGuia.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, -1, 10));
        EmisionGuia.add(txtRucEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 90, 30));

        txtNombreClienteEg.setEditable(false);
        EmisionGuia.add(txtNombreClienteEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 100, 30));

        btnGenerarEg.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerarEg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoImprimir.png"))); // NOI18N
        btnGenerarEg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarEg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarEgActionPerformed(evt);
            }
        });
        EmisionGuia.add(btnGenerarEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 435, 60, 50));
        EmisionGuia.add(Midate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 140, 30));
        EmisionGuia.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 340, 10));

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(153, 153, 153));
        jLabel72.setText("Vehiculo");
        EmisionGuia.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(153, 153, 153));
        jLabel73.setText("Encomienda");
        EmisionGuia.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        EmisionGuia.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 930, 10));

        jLabel74.setBackground(new java.awt.Color(255, 255, 255));
        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(153, 153, 153));
        jLabel74.setText("Proveedor - Cliente");
        EmisionGuia.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));
        EmisionGuia.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 930, 10));

        txtNombreConductorEg.setEditable(false);
        EmisionGuia.add(txtNombreConductorEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 110, 30));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel75.setText("NOMBRE:");
        EmisionGuia.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, 10));

        txtLicenciaConductorEg.setEditable(false);
        EmisionGuia.add(txtLicenciaConductorEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 110, 30));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel76.setText("LICENCIA:");
        EmisionGuia.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel77.setText("PLACA:");
        EmisionGuia.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));
        EmisionGuia.add(txtPlacaEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 90, 30));
        EmisionGuia.add(txtIdConductorEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 450, 20, -1));
        EmisionGuia.add(txtIdCG, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, 20, 20));
        EmisionGuia.add(txtIdProEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, 20, -1));

        txtOrigenEg.setEditable(false);
        EmisionGuia.add(txtOrigenEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 140, 30));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel79.setText("ORIGEN:");
        EmisionGuia.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 10));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel80.setText("DESTINO:");
        EmisionGuia.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, 10));

        txtDestinoEg.setEditable(false);
        EmisionGuia.add(txtDestinoEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 150, 30));

        txtDireccionClienteEg.setEditable(false);
        EmisionGuia.add(txtDireccionClienteEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 130, 30));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel81.setText("DIRECCION:");
        EmisionGuia.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 80, 30));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel82.setText("MARCA:");
        EmisionGuia.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, 10));

        txtMarcaEg.setEditable(false);
        EmisionGuia.add(txtMarcaEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 110, 30));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel83.setText("CONFIGURACION VEHICULAR:");
        EmisionGuia.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 20));

        txtConfigVehicularEg.setEditable(false);
        EmisionGuia.add(txtConfigVehicularEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 150, 30));
        EmisionGuia.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 340, 10));

        jLabel84.setBackground(new java.awt.Color(255, 255, 255));
        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(153, 153, 153));
        jLabel84.setText("Conductor");
        EmisionGuia.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel85.setText("VOLUMEN:");
        EmisionGuia.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, -1));

        txtVolumenEg.setEditable(false);
        EmisionGuia.add(txtVolumenEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 90, 30));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel86.setText("PESO:");
        EmisionGuia.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, -1, -1));

        txtPesoEg.setEditable(false);
        EmisionGuia.add(txtPesoEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 100, 30));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setText("PRECIO:");
        EmisionGuia.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, -1, -1));
        EmisionGuia.add(txtIdVG, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 20, 20));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel78.setText("DNI:");
        EmisionGuia.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 40, -1));
        EmisionGuia.add(txtDniCEG, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 90, 30));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel71.setText("PROVEEDOR:");
        EmisionGuia.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 10));

        txtProveedorProEg.setEditable(false);
        EmisionGuia.add(txtProveedorProEg, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 110, 30));

        btnBuscarC.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoBuscar.png"))); // NOI18N
        btnBuscarC.setText("Buscar");
        btnBuscarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCActionPerformed(evt);
            }
        });
        EmisionGuia.add(btnBuscarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 110, -1));

        btnBuscarP.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoBuscar.png"))); // NOI18N
        btnBuscarP.setText("Buscar");
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });
        EmisionGuia.add(btnBuscarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 110, -1));

        btnGenerar.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGenerar.png"))); // NOI18N
        btnGenerar.setText("GENERAR");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        EmisionGuia.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, -1, 40));

        btnBuscarC1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoBuscar.png"))); // NOI18N
        btnBuscarC1.setText("Buscar");
        btnBuscarC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarC1ActionPerformed(evt);
            }
        });
        EmisionGuia.add(btnBuscarC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 110, -1));

        btnBuscarV.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoBuscar.png"))); // NOI18N
        btnBuscarV.setText("Buscar");
        btnBuscarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVActionPerformed(evt);
            }
        });
        EmisionGuia.add(btnBuscarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 110, -1));

        ContenedorPaneles.addTab("2", EmisionGuia);

        Cliente.setBackground(new java.awt.Color(78, 172, 172));
        Cliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI/RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableCliente);
        if (TableCliente.getColumnModel().getColumnCount() > 0) {
            TableCliente.getColumnModel().getColumn(0).setPreferredWidth(5);
            TableCliente.getColumnModel().getColumn(1).setPreferredWidth(40);
            TableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableCliente.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableCliente.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        Cliente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 570, 210));

        jPanel9.setBackground(new java.awt.Color(215, 233, 234));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Cliente"));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Dni/Ruc:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Nombre:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Télefono:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Dirección:");

        btnGuardarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGuardar.png"))); // NOI18N
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoActualizar.png"))); // NOI18N
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoEliminar.png"))); // NOI18N
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoNuevo.png"))); // NOI18N
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(43, 43, 43)
                                .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(46, 46, 46)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(40, 40, 40)
                                .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(38, 38, 38)
                                .addComponent(txtDirecionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel12))
                    .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13))
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel15))
                    .addComponent(txtDirecionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnGuardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnNuevoCliente)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Cliente.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 270, 330));

        ContenedorPaneles.addTab("3", Cliente);

        Proveedor.setBackground(new java.awt.Color(78, 172, 172));
        Proveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        TableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableProveedor);
        if (TableProveedor.getColumnModel().getColumnCount() > 0) {
            TableProveedor.getColumnModel().getColumn(0).setPreferredWidth(5);
            TableProveedor.getColumnModel().getColumn(1).setPreferredWidth(40);
            TableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProveedor.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableProveedor.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        Proveedor.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 30, 590, 220));

        jPanel10.setBackground(new java.awt.Color(215, 233, 234));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Proveedor"));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Ruc:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Nombre:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Teléfono:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Dirección:");

        btnguardarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnguardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGuardar.png"))); // NOI18N
        btnguardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarProveedorActionPerformed(evt);
            }
        });

        btnEditarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoActualizar.png"))); // NOI18N
        btnEditarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });

        btnNuevoProveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoNuevo.png"))); // NOI18N
        btnNuevoProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoEliminar.png"))); // NOI18N
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRucProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(txtNombreproveedor)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(txtDireccionProveedor))))
                .addGap(251, 251, 251))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardarProveedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoProveedor)
                    .addComponent(btnEditarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(btnguardarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
        );

        Proveedor.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 270, 340));

        ContenedorPaneles.addTab("4", Proveedor);

        Vehiculo.setBackground(new java.awt.Color(78, 172, 172));
        Vehiculo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableVehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PLACA", "MARCA", "COLOR", "CONFIGURACION VEHICULAR"
            }
        ));
        TableVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVehiculoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TableVehiculo);
        if (TableVehiculo.getColumnModel().getColumnCount() > 0) {
            TableVehiculo.getColumnModel().getColumn(0).setPreferredWidth(5);
            TableVehiculo.getColumnModel().getColumn(1).setPreferredWidth(10);
            TableVehiculo.getColumnModel().getColumn(2).setPreferredWidth(40);
            TableVehiculo.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableVehiculo.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        Vehiculo.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 560, 260));

        jPanel12.setBackground(new java.awt.Color(215, 233, 234));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Vehiculo"));
        jPanel12.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jPanel12ComponentRemoved(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Placa:");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Marca:");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Color:");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Configuración:");

        btnGuardarVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGuardar.png"))); // NOI18N
        btnGuardarVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVehiculoActionPerformed(evt);
            }
        });

        btnEditarVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoActualizar.png"))); // NOI18N
        btnEditarVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVehiculoActionPerformed(evt);
            }
        });

        btnEliminarVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoEliminar.png"))); // NOI18N
        btnEliminarVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVehiculoActionPerformed(evt);
            }
        });

        btnNuevoVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoNuevo.png"))); // NOI18N
        btnNuevoVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addGap(18, 18, 18)
                                .addComponent(txtConfiguracionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtColorVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(txtMarcaVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(txtPlacaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnGuardarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevoVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtPlacaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(13, 13, 13)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtConfiguracionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(txtIdVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardarVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnEditarVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(83, 83, 83))
        );

        Vehiculo.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 310, 380));

        ContenedorPaneles.addTab("5", Vehiculo);

        Conductor.setBackground(new java.awt.Color(78, 172, 172));
        Conductor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableConductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "NOMBRE", "LICENCIA", "DIRECCION", "TELEFONO", "VEHICULO"
            }
        ));
        TableConductor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableConductorMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(TableConductor);
        if (TableConductor.getColumnModel().getColumnCount() > 0) {
            TableConductor.getColumnModel().getColumn(0).setPreferredWidth(2);
            TableConductor.getColumnModel().getColumn(1).setPreferredWidth(10);
            TableConductor.getColumnModel().getColumn(2).setPreferredWidth(70);
            TableConductor.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableConductor.getColumnModel().getColumn(4).setPreferredWidth(50);
            TableConductor.getColumnModel().getColumn(5).setPreferredWidth(10);
            TableConductor.getColumnModel().getColumn(6).setPreferredWidth(10);
        }

        Conductor.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 560, 260));

        jPanel15.setBackground(new java.awt.Color(215, 233, 234));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Conductor"));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Dni:");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Nombre:");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Licencia:");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Dirección:");

        btnGuardarConductor.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarConductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGuardar.png"))); // NOI18N
        btnGuardarConductor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarConductorActionPerformed(evt);
            }
        });

        btnEditarConductor.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarConductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoActualizar.png"))); // NOI18N
        btnEditarConductor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarConductorActionPerformed(evt);
            }
        });

        btnEliminarConductor.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarConductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoEliminar.png"))); // NOI18N
        btnEliminarConductor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarConductorActionPerformed(evt);
            }
        });

        btnNuevoConductor.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoConductor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoNuevo.png"))); // NOI18N
        btnNuevoConductor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoConductorActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Teléfono:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("VEHICULO:");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoBuscar.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarConductor)
                            .addComponent(btnGuardarConductor))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNuevoConductor)
                            .addComponent(btnEditarConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel57))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDniConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccionConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLicenciaConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(txtVehiculoCond, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtTelefonoConductor))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtDniConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtNombreConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(txtLicenciaConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel59))
                    .addComponent(txtDireccionConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel60))
                    .addComponent(txtTelefonoConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtIdConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVehiculoCond)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGuardarConductor)
                            .addComponent(btnEditarConductor))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoConductor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        Conductor.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 300, 400));

        ContenedorPaneles.addTab("6", Conductor);

        Producto.setBackground(new java.awt.Color(78, 172, 172));
        Producto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "DESCRIPCIÓN", "PROVEEDOR", "CLIENTE", "CANT", "PRECIO", "VOLUMEN", "PESO", "ORIGEN", "DESTINO"
            }
        ));
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(60);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(5).setPreferredWidth(40);
            TableProducto.getColumnModel().getColumn(6).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(7).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(8).setPreferredWidth(50);
        }

        Producto.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 640, 260));

        jPanel11.setBackground(new java.awt.Color(215, 233, 234));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Encomienda"));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Código:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Descripción:");

        txtOrigenPro.setEditable(false);
        txtOrigenPro.setText("ABANCAY");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Cantidad:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Precio:");

        txtPrecioPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("PROVEEDOR:");
        jLabel26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnGuardarProducto.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoGuardar.png"))); // NOI18N
        btnGuardarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        btnEditarProducto.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoActualizar.png"))); // NOI18N
        btnEditarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoEliminar.png"))); // NOI18N
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnNuevoProducto.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoNuevo.png"))); // NOI18N
        btnNuevoProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Peso:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Volumen:");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Destino:");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Origen:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("CLIENTE:");
        jLabel45.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoBuscar.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel39)
                            .addComponent(jLabel62)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDestPro, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOrigenPro, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCantPro)
                                    .addComponent(txtVolumenPro, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel38)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPesoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnGuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxProveedorPro, 0, 159, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtCodigoCliente)
                                .addGap(24, 24, 24)
                                .addComponent(jButton2)))))
                .addGap(91, 91, 91))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVolumenPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(txtPesoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel62)
                    .addComponent(txtOrigenPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtDestPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cbxProveedorPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardarProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditarProducto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        Producto.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 460));

        ContenedorPaneles.addTab("7", Producto);

        Empresa.setBackground(new java.awt.Color(78, 172, 172));
        Empresa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("RUC");
        Empresa.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("DIRECCIÓN");
        Empresa.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("MENSAJE");
        Empresa.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));
        Empresa.add(txtRucEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 110, 30));
        Empresa.add(txtDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 110, 30));
        Empresa.add(txtMensajeEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 280, 30));

        jPanel8.setBackground(new java.awt.Color(236, 242, 242));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("TELÉFONO");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("NOMBRE");

        btnActualizarEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoActualizar.png"))); // NOI18N
        btnActualizarEmpresa.setText("ACTUALIZAR");
        btnActualizarEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnActualizarEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIdConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(txtTelefonoEmpresa))
                .addGap(79, 79, 79))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel28)
                .addGap(13, 13, 13)
                .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel29)
                .addGap(13, 13, 13)
                .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdConfig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        Empresa.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 300, 310));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Empresa.png"))); // NOI18N
        Empresa.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 600, 410));

        ContenedorPaneles.addTab("8", Empresa);

        Usuarios.setBackground(new java.awt.Color(78, 172, 172));
        Usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(236, 242, 242));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoUser.jpg"))); // NOI18N
        jLabel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Usuario:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Password:");

        btnIniciar.setBackground(new java.awt.Color(204, 204, 204));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoLogin.png"))); // NOI18N
        btnIniciar.setText("Registrar");
        btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Nombre:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Rol:");

        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Asistente" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel36)
                                .addComponent(jLabel35)
                                .addComponent(txtCorreo)
                                .addComponent(txtPass)
                                .addComponent(jLabel37)
                                .addComponent(txtNombre)
                                .addComponent(cbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel33))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addGap(2, 2, 2)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        Usuarios.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 330, 400));

        TableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Correo", "Rol"
            }
        ));
        TableUsuarios.setRowHeight(20);
        jScrollPane7.setViewportView(TableUsuarios);

        Usuarios.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 550, 190));

        ContenedorPaneles.addTab("9", Usuarios);

        Envios.setBackground(new java.awt.Color(78, 172, 172));
        Envios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableEnvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        TableEnvios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEnviosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableEnvios);
        if (TableEnvios.getColumnModel().getColumnCount() > 0) {
            TableEnvios.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableEnvios.getColumnModel().getColumn(1).setPreferredWidth(60);
            TableEnvios.getColumnModel().getColumn(2).setPreferredWidth(60);
            TableEnvios.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        Envios.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 890, 220));

        btnPDFEnvios.setBackground(new java.awt.Color(255, 255, 255));
        btnPDFEnvios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoPdf.png"))); // NOI18N
        btnPDFEnvios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDFEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFEnviosActionPerformed(evt);
            }
        });
        Envios.add(btnPDFEnvios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 50, -1));
        Envios.add(txtIdEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 10, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Historial de Envios");
        Envios.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 350, -1));

        ContenedorPaneles.addTab("10", Envios);

        getContentPane().add(ContenedorPaneles, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 970, 520));

        MenuBar.setBackground(java.awt.SystemColor.controlHighlight);

        btnMenuBar.setText("Menú");
        btnMenuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        MenuUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoUsuario.png"))); // NOI18N
        MenuUsuarios.setText("Usuarios");
        MenuUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuUsuariosActionPerformed(evt);
            }
        });
        btnMenuBar.add(MenuUsuarios);

        MenuHistorialEnvios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoEnvios.png"))); // NOI18N
        MenuHistorialEnvios.setText("Historial Envios");
        MenuHistorialEnvios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuHistorialEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuHistorialEnviosActionPerformed(evt);
            }
        });
        btnMenuBar.add(MenuHistorialEnvios);

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/IconoVolver.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        btnMenuBar.add(btnRegresar);

        MenuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoSALIR.jpg"))); // NOI18N
        MenuSalir.setText("Salir");
        MenuSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSalirActionPerformed(evt);
            }
        });
        btnMenuBar.add(MenuSalir);

        MenuBar.add(btnMenuBar);

        jMenu2.setText("Ayuda");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoAyuda.png"))); // NOI18N
        jMenuItem4.setText("Configuración");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.add(jMenuItem4);

        MenuBar.add(jMenu2);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

        LimpiarTable();
        ListarCliente();
        btnEditarCliente.setEnabled(false);
        btnEliminarCliente.setEnabled(false);
        LimpiarCliente();
        ContenedorPaneles.setSelectedIndex(2);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed

        LimpiarTable();
        ListarProveedor();
        ContenedorPaneles.setSelectedIndex(3);
        btnEditarProveedor.setEnabled(false);
        btnEliminarProveedor.setEnabled(false);
        LimpiarProveedor();
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed

        LimpiarTable();
        ListarProductos();
        ContenedorPaneles.setSelectedIndex(6);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        btnGuardarProducto.setEnabled(true);
        LimpiarProductos();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed

        ContenedorPaneles.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpresaActionPerformed

        ContenedorPaneles.setSelectedIndex(7);
    }//GEN-LAST:event_btnEmpresaActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked

        cbxProveedorPro.removeAllItems();
        llenarProveedor();
        
    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if(txtNombre.getText().equals("") || txtCorreo.getText().equals("") || txtPass.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Todo los campos son requeridos");
        }else{
            if(txtPass.getText().length() < 8){
                JOptionPane.showMessageDialog(null, "Contraseña debil!");
            }else{
                String correo = txtCorreo.getText();
                String pass = String.valueOf(txtPass.getPassword());
                String nom = txtNombre.getText();
                String rol = cbxRol.getSelectedItem().toString();
                lg.setNombre(nom);
                lg.setCorreo(correo);
                lg.setPass(pass);
                lg.setRol(rol);
                login.Registrar(lg);
                JOptionPane.showMessageDialog(null, "Usuario Registrado");
                LimpiarTable();
                ListarUsuarios();
                nuevoUsuario();
            }
        }
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnActualizarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpresaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtRucEmpresa.getText()) || !"".equals(txtNombreEmpresa.getText()) || !"".equals(txtTelefonoEmpresa.getText()) || !"".equals(txtDireccionEmpresa.getText())) {
            empres.setRuc(txtRucEmpresa.getText());
            empres.setNombre(txtNombreEmpresa.getText());
            empres.setTelefono(txtTelefonoEmpresa.getText());
            empres.setDireccion(txtDireccionEmpresa.getText());
            empres.setMensaje(txtMensajeEmpresa.getText());
            empres.setId(Integer.parseInt(txtIdConfig.getText()));
            empresDao.ModificarDatos(empres);
            JOptionPane.showMessageDialog(null, "Datos de la empresa modificado");
            ListarEmpresa();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnActualizarEmpresaActionPerformed

    private void btnPDFEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFEnviosActionPerformed

        if(txtIdEnvio.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }else{
            fact = factDao.BuscarFactura(Integer.parseInt(txtIdEnvio.getText()));
            factDao.pdfV(fact.getId(), fact.getCliente(), fact.getTotal(), fact.getVendedor());
        }
    }//GEN-LAST:event_btnPDFEnviosActionPerformed

    private void TableEnviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEnviosMouseClicked

        int fila = TableEnvios.rowAtPoint(evt.getPoint());
        txtIdEnvio.setText(TableEnvios.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TableEnviosMouseClicked

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked

        btnEditarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(true);
        btnGuardarProducto.setEnabled(true);
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdproducto.setText(TableProducto.getValueAt(fila, 0).toString());
        pro = proDao.BuscarId(Integer.parseInt(txtIdproducto.getText()));
        txtCodigoPro.setText(pro.getCodigo());
        txtNombrePro.setText(pro.getNombre());
        txtCodigoCliente.setText(TableProducto.getValueAt(fila, 4).toString());
        txtCantPro.setText("" + pro.getStock());
        txtPrecioPro.setText("" + pro.getPrecio());
        txtVolumenPro.setText(TableProducto.getValueAt(fila, 7).toString());
        txtPesoPro.setText(TableProducto.getValueAt(fila, 8).toString());
        txtOrigenPro.setText(TableProducto.getValueAt(fila, 9).toString());
        txtDestPro.setText(TableProducto.getValueAt(fila, 10).toString());
        
        cbxProveedorPro.setSelectedItem(new ComboProveedor(pro.getProveedor(), pro.getProveedorPro()));
        
    }//GEN-LAST:event_TableProductoMouseClicked

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed

        if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                PrDao.EliminarProveedor(id);
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed

        LimpiarProveedor();
        btnEditarProveedor.setEnabled(false);
        btnEliminarProveedor.setEnabled(false);
        btnguardarProveedor.setEnabled(true);
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed

        if ("".equals(txtIdProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreproveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())) {
                pr.setRuc(txtRucProveedor.getText());
                pr.setNombre(txtNombreproveedor.getText());
                pr.setTelefono(txtTelefonoProveedor.getText());
                pr.setDireccion(txtDireccionProveedor.getText());
                pr.setId(Integer.parseInt(txtIdProveedor.getText()));
                PrDao.ModificarProveedor(pr);
                JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
                btnEditarProveedor.setEnabled(false);
                btnEliminarProveedor.setEnabled(false);
                btnguardarProveedor.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnEditarProveedorActionPerformed

    private void btnguardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarProveedorActionPerformed

        if (!"".equals(txtRucProveedor.getText())  || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())) {
            pr.setRuc(txtRucProveedor.getText());
            pr.setNombre(txtNombreproveedor.getText());
            pr.setTelefono(txtTelefonoProveedor.getText());
            pr.setDireccion(txtDireccionProveedor.getText());
            if(txtRucProveedor.getText().length() == 8 || txtRucProveedor.getText().length() == 11 ){
                PrDao.RegistrarProveedor(pr);
                JOptionPane.showMessageDialog(null, "Proveedor Registrado");
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
                btnEditarProveedor.setEnabled(false);
                btnEliminarProveedor.setEnabled(false);
                btnguardarProveedor.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Documento invalido!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
        }
    }//GEN-LAST:event_btnguardarProveedorActionPerformed

    private void TableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProveedorMouseClicked

        btnEditarProveedor.setEnabled(true);
        btnEliminarProveedor.setEnabled(true);
        btnguardarProveedor.setEnabled(false);
        int fila = TableProveedor.rowAtPoint(evt.getPoint());
        txtIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
        txtRucProveedor.setText(TableProveedor.getValueAt(fila, 1).toString());
        txtNombreproveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
        txtTelefonoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
        txtDireccionProveedor.setText(TableProveedor.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableProveedorMouseClicked

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed

        LimpiarCliente();
        btnEditarCliente.setEnabled(false);
        btnEliminarCliente.setEnabled(false);
        btnGuardarCliente.setEnabled(true);
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
   
        if (!"".equals(txtIdCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdCliente.getText());
                cliDao.EliminarCliente(id);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed

        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText())) {
                cl.setDni(txtDniCliente.getText());
                cl.setNombre(txtNombreCliente.getText());
                cl.setTelefono(txtTelefonoCliente.getText());
                cl.setDireccion(txtDirecionCliente.getText());
                cl.setId(Integer.parseInt(txtIdCliente.getText()));
                cliDao.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
            }
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed

        if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDirecionCliente.getText())) {
            cl.setDni(txtDniCliente.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            cl.setDireccion(txtDirecionCliente.getText());
            if(txtDniCliente.getText().length() == 8 || txtDniCliente.getText().length() == 11 ){
                cliDao.RegistrarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Registrado");
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
                btnEditarCliente.setEnabled(false);
                btnEliminarCliente.setEnabled(false);
                btnGuardarCliente.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Documento invalido!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked

        btnEditarCliente.setEnabled(true);
        btnEliminarCliente.setEnabled(true);
        btnGuardarCliente.setEnabled(false);
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtIdCliente.setText(TableCliente.getValueAt(fila, 0).toString());
        txtDniCliente.setText(TableCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
        txtTelefonoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
        txtDirecionCliente.setText(TableCliente.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableClienteMouseClicked

    private void btnVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculoActionPerformed
       
        LimpiarTable();
        ListarVehiculo();
        btnEditarVehiculo.setEnabled(false);
        btnEliminarVehiculo.setEnabled(false);
        LimpiarVehiculo();
        ContenedorPaneles.setSelectedIndex(4);
    }//GEN-LAST:event_btnVehiculoActionPerformed

    private void btnConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConductorActionPerformed
        LimpiarTable();
        ListarConductor();
        btnEditarConductor.setEnabled(false);
        btnEliminarConductor.setEnabled(false);
        LimpiarConductor();
        ContenedorPaneles.setSelectedIndex(5);
    }//GEN-LAST:event_btnConductorActionPerformed

    private void MenuHistorialEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHistorialEnviosActionPerformed
        ContenedorPaneles.setSelectedIndex(9);
        LimpiarTable();
        ListarEnvios();
    }//GEN-LAST:event_MenuHistorialEnviosActionPerformed

    private void MenuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuUsuariosActionPerformed
        ContenedorPaneles.setSelectedIndex(8);
        LimpiarTable();
        ListarUsuarios();
    }//GEN-LAST:event_MenuUsuariosActionPerformed

    private void TableVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVehiculoMouseClicked

        btnEditarVehiculo.setEnabled(true);
        btnEliminarVehiculo.setEnabled(true);
        btnGuardarVehiculo.setEnabled(false);
        int fila = TableVehiculo.rowAtPoint(evt.getPoint());
        txtIdVehiculo.setText(TableVehiculo.getValueAt(fila, 0).toString());
        txtPlacaVehiculo.setText(TableVehiculo.getValueAt(fila, 1).toString());
        txtMarcaVehiculo.setText(TableVehiculo.getValueAt(fila, 2).toString());
        txtColorVehiculo.setText(TableVehiculo.getValueAt(fila, 3).toString());
        txtConfiguracionVehiculo.setText(TableVehiculo.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableVehiculoMouseClicked

    private void btnGuardarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVehiculoActionPerformed
        
        if (!"".equals(txtPlacaVehiculo.getText()) || !"".equals(txtMarcaVehiculo.getText()) || !"".equals(txtColorVehiculo.getText()) || !"".equals(txtConfiguracionVehiculo.getText())) {
            vl.setPlaca(txtPlacaVehiculo.getText());
            vl.setMarca(txtMarcaVehiculo.getText());
            vl.setColor(txtColorVehiculo.getText());
            vl.setConfiguracion(txtConfiguracionVehiculo.getText());
            vehicDao.RegistrarVehiculo(vl);
            JOptionPane.showMessageDialog(null, "Vehiculo Registrado");
            LimpiarTable();
            LimpiarVehiculo();
            ListarVehiculo();
            btnEditarVehiculo.setEnabled(false);
            btnEliminarVehiculo.setEnabled(false);
            btnGuardarVehiculo.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarVehiculoActionPerformed

    private void btnEditarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVehiculoActionPerformed

        if ("".equals(txtIdVehiculo.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtPlacaVehiculo.getText()) || !"".equals(txtMarcaVehiculo.getText()) || !"".equals(txtColorVehiculo.getText())) {
                vl.setPlaca(txtPlacaVehiculo.getText());
                vl.setMarca(txtMarcaVehiculo.getText());
                vl.setColor(txtColorVehiculo.getText());
                vl.setConfiguracion(txtConfiguracionVehiculo.getText());
                vl.setId(Integer.parseInt(txtIdVehiculo.getText()));
                vehicDao.ModificarVehiculo(vl);
                JOptionPane.showMessageDialog(null, "Vehiculo Modificado");
                LimpiarTable();
                LimpiarVehiculo();
                ListarVehiculo();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
            }
        }
    }//GEN-LAST:event_btnEditarVehiculoActionPerformed

    private void btnEliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVehiculoActionPerformed
        if (!"".equals(txtIdVehiculo.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdVehiculo.getText());
                vehicDao.EliminarVehiculo(id);
                LimpiarTable();
                LimpiarVehiculo();
                ListarVehiculo();
            }
        }
    }//GEN-LAST:event_btnEliminarVehiculoActionPerformed

    private void btnNuevoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoVehiculoActionPerformed
        LimpiarVehiculo();
        btnEditarVehiculo.setEnabled(false);
        btnEliminarVehiculo.setEnabled(false);
        btnGuardarVehiculo.setEnabled(true);
    }//GEN-LAST:event_btnNuevoVehiculoActionPerformed

    private void TableConductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableConductorMouseClicked

        btnEditarConductor.setEnabled(true);
        btnEliminarConductor.setEnabled(true);
        btnGuardarConductor.setEnabled(false);
        int fila = TableConductor.rowAtPoint(evt.getPoint());
        txtIdConductor.setText(TableConductor.getValueAt(fila, 0).toString());
        txtDniConductor.setText(TableConductor.getValueAt(fila, 1).toString());
        txtNombreConductor.setText(TableConductor.getValueAt(fila, 2).toString());
        txtLicenciaConductor.setText(TableConductor.getValueAt(fila, 3).toString());
        txtDireccionConductor.setText(TableConductor.getValueAt(fila, 4).toString());
        txtTelefonoConductor.setText(TableConductor.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_TableConductorMouseClicked

    private void btnGuardarConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarConductorActionPerformed
        if(!"".equals(txtVehiculoCond.getText())){
            if (!"".equals(txtDniConductor.getText()) || !"".equals(txtDireccionConductor.getText())|| !"".equals(txtTelefonoConductor.getText()))  {

                cr.setDni(txtDniConductor.getText());
                cr.setNombre(txtNombreConductor.getText());
                cr.setLicencia(txtLicenciaConductor.getText());
                cr.setDireccion(txtDireccionConductor.getText());
                cr.setTelefono(txtTelefonoConductor.getText());
                cr.setVehiculoCond(txtVehiculoCond.getText());
                if(txtDniConductor.getText().length() == 8 ){
                    String placa = txtVehiculoCond.getText();
                    vl = vehicDao.BuscarVehiculo(placa);
                    if (vl.getMarca() != null ){
                        conductDao.RegistrarConductor(cr);
                        JOptionPane.showMessageDialog(null, "Conductor Registrado");
                        LimpiarTable();
                        LimpiarConductor();
                        ListarConductor();
                        btnEditarConductor.setEnabled(false);
                        btnEliminarConductor.setEnabled(false);
                        btnGuardarConductor.setEnabled(true);
                    }else{
                        txtVehiculoCond.setText("");
                        JOptionPane.showMessageDialog(null, "El vehiculo no existe");
                        txtVehiculoCond.requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "DNI invalido!");
                }
            }else {
               JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Complete el campo Vehiculo");
        }

    }//GEN-LAST:event_btnGuardarConductorActionPerformed

    private void btnEditarConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarConductorActionPerformed
        if ("".equals(txtIdConductor.getText())) {
            JOptionPane.showMessageDialog(null, "seleccione una fila");
        } else {

            if (!"".equals(txtDniConductor.getText()) || !"".equals(txtNombreConductor.getText()) || !"".equals(txtLicenciaConductor.getText())) {
                cr.setDni(txtDniConductor.getText());
                cr.setNombre(txtNombreConductor.getText());
                cr.setLicencia(txtLicenciaConductor.getText());
                cr.setDireccion(txtDireccionConductor.getText());
                cr.setTelefono(txtTelefonoConductor.getText());
                cr.setVehiculoCond(txtVehiculoCond.getText());
                cr.setId(Integer.parseInt(txtIdConductor.getText()));
                conductDao.ModificarConductor(cr);
                JOptionPane.showMessageDialog(null, "Conductor Modificado");
                LimpiarTable();
                LimpiarConductor();
                ListarConductor();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
            }
        }
    }//GEN-LAST:event_btnEditarConductorActionPerformed

    private void btnEliminarConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarConductorActionPerformed
                if (!"".equals(txtIdConductor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdConductor.getText());
                conductDao.EliminarConductor(id);
                LimpiarTable();
                LimpiarConductor();
                ListarConductor();
            }
        }
    }//GEN-LAST:event_btnEliminarConductorActionPerformed

    private void btnNuevoConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoConductorActionPerformed
        LimpiarCliente();
        btnEditarConductor.setEnabled(false);
        btnEliminarConductor.setEnabled(false);
        btnGuardarConductor.setEnabled(true);
    }//GEN-LAST:event_btnNuevoConductorActionPerformed

    private void btnNuevaVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVenta1ActionPerformed
        ContenedorPaneles.setSelectedIndex(1);
    }//GEN-LAST:event_btnNuevaVenta1ActionPerformed

    private void btnGenerarEgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarEgActionPerformed
        if (TableEg.getRowCount() > 0) {
            if (!"".equals(txtNombreClienteEg.getText()) || !"".equals(txtMarcaEg.getText()) || !"".equals(txtLicenciaConductorEg.getText())) {
                //RegistrarVenta();
                RegistrarDetalleEg();
                //ActualizarStock();
                LimpiarTableEg();
                LimpiarClienteEg();
                LimpiarConductorEg();
            } else {
                JOptionPane.showMessageDialog(null, "Debes completar los campos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay la encomienda ");
        }
    }//GEN-LAST:event_btnGenerarEgActionPerformed

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed

        String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(Midate.getDate());
        Grafico.Graficar(fechaReporte);
    }//GEN-LAST:event_btnGraficarActionPerformed

    private void btnGenerarFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarFacturacionActionPerformed

        if (TableFacturacion.getRowCount() > 0) {
            if (!"".equals(txtNombreClienteFacturacion.getText())) {
                RegistrarFactura();
                RegistrarDetalle();
                ActualizarStock();
                LimpiarTableFactura();
                LimpiarClienteFactura();
            } else {
                JOptionPane.showMessageDialog(null, "Debes completar los campos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay la encomienda ");
        }
    }//GEN-LAST:event_btnGenerarFacturacionActionPerformed

    private void txtCantidadFacturacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadFacturacionKeyTyped

        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadFacturacionKeyTyped

    private void txtCantidadFacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadFacturacionKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadFacturacion.getText()) || !"0".equals(txtCantidadFacturacion.getText())) {
                int id = Integer.parseInt(txtIdPro.getText());
                String descripcion = txtNombreFacturacion.getText();
                String cliente = txtNombreClienteFacturacion.getText();
                String direccion = txtDireccionFacturacion.getText();
                String origen = txtOrigenFacturacion.getText();
                String destino = txtDestinoFacturacion.getText();
                int cant = Integer.parseInt(txtCantidadFacturacion.getText());
                double precio = Double.parseDouble(txtPrecioFacturacion.getText());
                //double plus  = (Double.parseDouble(txtVolumenPro.getText()) * Double.parseDouble(txtPesoPro.getText()))/100;

                double total = (cant * precio)/2;
                int stock = Integer.parseInt(txtStockDisponible.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) TableFacturacion.getModel();
                    for (int i = 0; i < TableFacturacion.getRowCount(); i++) {
                        if (TableFacturacion.getValueAt(i, 1).equals(txtNombreFacturacion.getText())) {
                            JOptionPane.showMessageDialog(null, "La encomienda ya esta registrado");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    lista.add(cliente);
                    lista.add(direccion);
                    lista.add(origen);
                    lista.add(destino);

                    Object[] O = new Object[9];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    O[5] = lista.get(6);
                    O[6] = lista.get(7);
                    O[7] = lista.get(8);
                    O[8] = lista.get(9);
                    
     

                    tmp.addRow(O);
                    TableFacturacion.setModel(tmp);
                    TotalPagar();
                    LimpiarFactura();
                    txtCodigoFacturacion.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
            }

        }
    }//GEN-LAST:event_txtCantidadFacturacionKeyPressed

    private void txtNombreFacturacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreFacturacionKeyTyped

        event.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreFacturacionKeyTyped

    private void txtCodigoFacturacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoFacturacionKeyTyped

        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtCodigoFacturacionKeyTyped

    private void txtCodigoFacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoFacturacionKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoFacturacion.getText())) {
                String cod = txtCodigoFacturacion.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getNombre() != null) {
                    txtIdPro.setText("" + pro.getId());
                    txtNombreFacturacion.setText("" + pro.getNombre());
                    txtOrigenFacturacion.setText("" + pro.getOrigenPro());
                    txtDestinoFacturacion.setText("" + pro.getDestinoPro());
                    txtPrecioFacturacion.setText("" + pro.getPrecio());
                    txtStockDisponible.setText("" + pro.getStock());
                    txtCantidadFacturacion.requestFocus();
                } else {
                    LimpiarFactura();
                    txtCodigoFacturacion.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del productos");
                txtCodigoFacturacion.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoFacturacionKeyPressed

    private void jPanel12ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel12ComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12ComponentRemoved

    private void MenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSalirActionPerformed
        //System.exit(WIDTH);
        dispose();
    }//GEN-LAST:event_MenuSalirActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        LimpiarProductos();
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed

        if (!"".equals(txtIdproducto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Seguro de eliminar la Encomienda?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdproducto.getText());
                proDao.EliminarProductos(id);
                LimpiarTable();
                LimpiarProductos();
                ListarProductos();
                btnEditarProducto.setEnabled(false);
                btnEliminarProducto.setEnabled(false);
                btnGuardarProducto.setEnabled(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed

        if ("".equals(txtIdproducto.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtNombrePro.getText()) || !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText()) || !"".equals(txtCodigoCliente.getText()) || !"".equals(txtVolumenPro.getText()) || !"".equals(txtPesoPro.getText())|| !"".equals(txtPrecioPro.getText()) || !"".equals(txtOrigenPro.getText()) || !"".equals(txtDestPro.getText())) {
                pro.setCodigo(txtCodigoPro.getText());
                pro.setNombre(txtNombrePro.getText());
                ComboProveedor itemP = (ComboProveedor) cbxProveedorPro.getSelectedItem();
                pro.setProveedor(itemP.getId());
                pro.setClientePro(txtCodigoCliente.getText());
                pro.setStock(Integer.parseInt(txtCantPro.getText()));
                pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
                pro.setVolumen(Double.parseDouble(txtVolumenPro.getText()));
                pro.setPeso(Double.parseDouble(txtPesoPro.getText()));
                pro.setOrigenPro((txtOrigenPro.getText()));
                pro.setDestinoPro((txtDestPro.getText()));
                pro.setId(Integer.parseInt(txtIdproducto.getText()));
                proDao.ModificarProductos(pro);
                JOptionPane.showMessageDialog(null, "Encomienda Modificado");
                LimpiarTable();
                ListarProductos();
                LimpiarProductos();
                cbxProveedorPro.removeAllItems();
                llenarProveedor();
                btnEditarProducto.setEnabled(false);
                btnEliminarProducto.setEnabled(false);
                btnGuardarProducto.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        if(!"".equals(txtCodigoCliente.getText())){
            if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtNombrePro.getText()) || !"".equals(cbxProveedorPro.getSelectedItem()) ||!"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())|| !"".equals(txtVolumenPro.getText())|| !"".equals(txtPesoPro.getText())|| !"".equals(txtOrigenPro.getText())|| !"".equals(txtDestPro.getText())) {

                    pro.setCodigo(txtCodigoPro.getText());
                    pro.setNombre(txtNombrePro.getText());
                    ComboProveedor itemP = (ComboProveedor) cbxProveedorPro.getSelectedItem();
                    pro.setProveedor(itemP.getId());
                    pro.setClientePro(txtCodigoCliente.getText());
                    pro.setStock(Integer.parseInt(txtCantPro.getText()));
                    pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
                    pro.setVolumen(Double.parseDouble(txtVolumenPro.getText()));
                    pro.setPeso(Double.parseDouble(txtPesoPro.getText()));
                    pro.setOrigenPro(txtOrigenPro.getText());
                    pro.setDestinoPro(txtDestPro.getText());


                int dni = Integer.parseInt(txtCodigoCliente.getText());
                cl = cliDao.Buscarcliente(dni);
                if (cl.getNombre() != null) {
                        proDao.RegistrarProductos(pro);
                        JOptionPane.showMessageDialog(null, "Encomienda Registrado");
                        LimpiarTable();
                        ListarProductos();
                        LimpiarProductos();
                        cbxProveedorPro.removeAllItems();
                        llenarProveedor();
                        btnEditarProducto.setEnabled(false);
                        btnEliminarProducto.setEnabled(false);
                        btnGuardarProducto.setEnabled(true);
                }else{
                    txtCodigoCliente.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                    txtCodigoCliente.requestFocus();
                }

            }else {
               JOptionPane.showMessageDialog(null, "Los campos estan vacíos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Complete el campo Cliente");
        }
   
    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void txtPrecioProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyTyped

        event.numberDecimalKeyPress(evt, txtPrecioPro);
    }//GEN-LAST:event_txtPrecioProKeyTyped

    private void txtRucClienteFacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucClienteFacturacionKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRucClienteFacturacion.getText())) {
                int dni = Integer.parseInt(txtRucClienteFacturacion.getText());
                cl = cliDao.Buscarcliente(dni);
                if (cl.getNombre() != null) {
                    txtNombreClienteFacturacion.setText("" + cl.getNombre());
                    txtDireccionFacturacion.setText("" + cl.getDireccion());
                    txtIdCV.setText("" + cl.getId());
                    txtCantidadFacturacion.requestFocus();
                } else {
                    txtRucClienteFacturacion.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txtRucClienteFacturacionKeyPressed

    private void btnBuscarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCActionPerformed

            if (!"".equals(txtRucEg.getText())) {
                int dni = Integer.parseInt(txtRucEg.getText());
                cl = cliDao.Buscarcliente(dni);
                if (cl.getNombre() != null) {
                    txtNombreClienteEg.setText("" + cl.getNombre());
                    txtDireccionClienteEg.setText("" + cl.getDireccion());
                    txtIdCG.setText("" + cl.getId());
                    
                } else {
                    txtRucEg.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        
    }//GEN-LAST:event_btnBuscarCActionPerformed

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed

            if (!"".equals(txtCodigoEg.getText())) {
                String cod = txtCodigoEg.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getNombre() != null) {
                    txtIdPro.setText("" + pro.getId());
                    txtDescripcionEg.setText("" + pro.getNombre());
                    txtOrigenEg.setText("" + pro.getOrigenPro());
                    txtDestinoEg.setText("" + pro.getDestinoPro());
                    txtPrecioEg.setText("" + pro.getPrecio());
                    txtVolumenEg.setText("" + pro.getVolumen());
                    txtPesoEg.setText("" + pro.getPeso());
                List<Proveedor> lista = PrDao.ListarProveedor();
                 for (int i = 0; i < lista.size(); i++) {
                     int id = lista.get(i).getId();
                        String nombre = lista.get(i).getNombre();
                            txtProveedorProEg.setText(""+new ComboProveedor(id, nombre));
                  }
                    txtRucEg.setText("" + pro.getClientePro());
                    
                } else {
                    LimpiarFactura();
                    txtCodigoEg.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                txtCodigoEg.requestFocus();
            }
       
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
                
            if (!"".equals(txtCodigoEg.getText()) && !"".equals(txtRucEg.getText()) && !"".equals(txtPlacaEg.getText())&& !"".equals(txtDniCEG.getText())) {
                int id = Integer.parseInt(txtIdVG.getText());
                String descripcion = txtDescripcionEg.getText();
                String cliente = txtNombreClienteEg.getText();
                String proveedor = txtProveedorProEg.getText();
                String placa = txtPlacaEg.getText();
                String conductor = txtNombreConductorEg.getText();
                String origen = txtOrigenEg.getText();
                String destino = txtDestinoEg.getText();
                double precio = Double.parseDouble(txtPrecioEg.getText());
                
                tmp = (DefaultTableModel) TableEg.getModel();
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(id);
                    lista.add(descripcion);
                    lista.add(conductor);
                    lista.add(placa);
                    lista.add(origen);
                    lista.add(destino);
                    lista.add(proveedor);
                    lista.add(cliente);
                    lista.add(precio);

                    Object[] O = new Object[9];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    O[5] = lista.get(6);
                    O[6] = lista.get(7);
                    O[7] = lista.get(8);
                    O[8] = lista.get(9);

                    tmp.addRow(O);
                    TableEg.setModel(tmp);
            } else {
                JOptionPane.showMessageDialog(null, "Complete los campos");  
            }

    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
dispose();  
        Vista.Login lg = new Vista.Login();
               this.setVisible(false);
               lg.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarC1ActionPerformed
            if (!"".equals(txtDniCEG.getText())) {
                int dni = Integer.parseInt(txtDniCEG.getText());
                cr = conductDao.BuscarConductor(dni);
                if (cr.getNombre() != null) {
                    txtNombreConductorEg.setText("" + cr.getNombre());
                    txtLicenciaConductorEg.setText("" + cr.getLicencia());
                    txtIdConductorEg.setText("" + cr.getId());
                    txtPlacaEg.setText("" + cr.getVehiculoCond());
                    
                } else {
                    txtDniCEG.setText("");
                    JOptionPane.showMessageDialog(null, "El conductor no existe");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del conductor");
                txtCodigoEg.requestFocus();
            }
        
    }//GEN-LAST:event_btnBuscarC1ActionPerformed

    private void btnBuscarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVActionPerformed

            if (!"".equals(txtPlacaEg.getText())) {
                String placa = txtPlacaEg.getText();
                vl = vehicDao.BuscarVehiculo(placa);
                if (vl.getMarca() != null) {
                    txtMarcaEg.setText("" + vl.getMarca());
                    txtConfigVehicularEg.setText("" + vl.getConfiguracion());
                    txtIdVG.setText("" + vl.getId());
                    
                } else {
                    txtPlacaEg.setText("");
                    JOptionPane.showMessageDialog(null, "La placa no existe");
                }
            }
    }//GEN-LAST:event_btnBuscarVActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        VistaVehiculo v1 = new VistaVehiculo();
        v1.setVisible(true);      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VistaCliente c1 = new VistaCliente();
        c1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cliente;
    private javax.swing.JPanel Conductor;
    private javax.swing.JTabbedPane ContenedorPaneles;
    private javax.swing.JPanel EmisionGuia;
    private javax.swing.JPanel Empresa;
    private javax.swing.JPanel Envios;
    private javax.swing.JPanel Facturacion;
    private javax.swing.JLabel LOGO;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel LabelVendedor;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem MenuHistorialEnvios;
    private javax.swing.JMenuItem MenuSalir;
    private javax.swing.JMenuItem MenuUsuarios;
    private com.toedter.calendar.JDateChooser Midate;
    private com.toedter.calendar.JDateChooser Midate1;
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JPanel Producto;
    private javax.swing.JPanel Proveedor;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTable TableConductor;
    private javax.swing.JTable TableEg;
    private javax.swing.JTable TableEnvios;
    private javax.swing.JTable TableFacturacion;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable TableProveedor;
    private javax.swing.JTable TableUsuarios;
    private javax.swing.JTable TableVehiculo;
    private javax.swing.JPanel Usuarios;
    private javax.swing.JPanel Vehiculo;
    private javax.swing.JButton btnActualizarEmpresa;
    private javax.swing.JButton btnBuscarC;
    private javax.swing.JButton btnBuscarC1;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnBuscarV;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConductor;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarConductor;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEditarVehiculo;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarConductor;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarVehiculo;
    private javax.swing.JButton btnEmpresa;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGenerarEg;
    private javax.swing.JButton btnGenerarFacturacion;
    private javax.swing.JButton btnGraficar;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarConductor;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnGuardarVehiculo;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JMenu btnMenuBar;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevaVenta1;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoConductor;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnNuevoVehiculo;
    private javax.swing.JButton btnPDFEnvios;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JMenuItem btnRegresar;
    private javax.swing.JButton btnVehiculo;
    private javax.swing.JButton btnguardarProveedor;
    private javax.swing.JComboBox<Object> cbxProveedorPro;
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1dd;
    private javax.swing.JScrollPane jScrollPane1dd1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblEncabezadoPrincipal;
    private javax.swing.JPanel lblLinea;
    private javax.swing.JPanel panelCubre;
    private javax.swing.JLabel tipo;
    private javax.swing.JTextField txtCantPro;
    private javax.swing.JTextField txtCantidadFacturacion;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoEg;
    private javax.swing.JTextField txtCodigoFacturacion;
    private javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextField txtColorVehiculo;
    private javax.swing.JTextField txtConfigVehicularEg;
    private javax.swing.JTextField txtConfiguracionVehiculo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDescripcionEg;
    private javax.swing.JTextField txtDestPro;
    private javax.swing.JTextField txtDestinoEg;
    private javax.swing.JTextField txtDestinoFacturacion;
    private javax.swing.JTextField txtDireccionClienteEg;
    private javax.swing.JTextField txtDireccionConductor;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtDireccionFacturacion;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDirecionCliente;
    private javax.swing.JTextField txtDniCEG;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtDniConductor;
    private javax.swing.JTextField txtIdCG;
    private javax.swing.JTextField txtIdCV;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdConductor;
    private javax.swing.JTextField txtIdConductorEg;
    private javax.swing.JTextField txtIdConfig;
    private javax.swing.JTextField txtIdEnvio;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextField txtIdProEg;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdVG;
    private javax.swing.JTextField txtIdVehiculo;
    private javax.swing.JTextField txtIdproducto;
    private javax.swing.JTextField txtLicenciaConductor;
    private javax.swing.JTextField txtLicenciaConductorEg;
    private javax.swing.JTextField txtMarcaEg;
    private javax.swing.JTextField txtMarcaVehiculo;
    private javax.swing.JTextField txtMensajeEmpresa;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteEg;
    private javax.swing.JTextField txtNombreClienteFacturacion;
    private javax.swing.JTextField txtNombreConductor;
    private javax.swing.JTextField txtNombreConductorEg;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNombreFacturacion;
    private javax.swing.JTextField txtNombrePro;
    private javax.swing.JTextField txtNombreproveedor;
    private javax.swing.JTextField txtOrigenEg;
    private javax.swing.JTextField txtOrigenFacturacion;
    private javax.swing.JTextField txtOrigenPro;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtPesoEg;
    private javax.swing.JTextField txtPesoPro;
    private javax.swing.JTextField txtPlacaEg;
    private javax.swing.JTextField txtPlacaVehiculo;
    private javax.swing.JTextField txtPrecioEg;
    private javax.swing.JTextField txtPrecioFacturacion;
    private javax.swing.JTextField txtPrecioPro;
    private javax.swing.JTextField txtProveedorProEg;
    private javax.swing.JTextField txtRucClienteFacturacion;
    private javax.swing.JTextField txtRucEg;
    private javax.swing.JTextField txtRucEmpresa;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtStockDisponible;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoConductor;
    private javax.swing.JTextField txtTelefonoEmpresa;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtVehiculoCond;
    private javax.swing.JTextField txtVolumenEg;
    private javax.swing.JTextField txtVolumenPro;
    // End of variables declaration//GEN-END:variables
    
    private void LimpiarCliente() {
        txtIdCliente.setText("");
        txtDniCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDirecionCliente.setText("");
    }

    private void LimpiarProveedor() {
        txtIdProveedor.setText("");
        txtRucProveedor.setText("");
        txtNombreproveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
    }
    
    private void LimpiarVehiculo() {
        txtIdVehiculo.setText("");
        txtPlacaVehiculo.setText("");
        txtMarcaVehiculo.setText("");
        txtColorVehiculo.setText("");
        txtConfiguracionVehiculo.setText("");
    }
    
    private void LimpiarConductor() {
        txtIdConductor.setText("");
        txtDniConductor.setText("");
        txtNombreConductor.setText("");
        txtLicenciaConductor.setText("");
        txtDireccionConductor.setText("");
        txtTelefonoConductor.setText("");
        txtVehiculoCond.setText("");
    }

    private void LimpiarProductos() {
        txtIdPro.setText("");
        txtCodigoPro.setText("");
        cbxProveedorPro.setSelectedItem(null);
        txtCodigoCliente.setText("");
        txtOrigenPro.setText("");
        txtCantPro.setText("");
        txtPrecioPro.setText("");
        txtVolumenPro.setText("");
        txtPesoPro.setText("");
        txtNombrePro.setText("");
        txtDestPro.setText("");
        txtOrigenPro.setText("ABANCAY");
    }

    private void TotalPagar() {
        Totalpagar = 0.00;
        int numFila = TableFacturacion.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TableFacturacion.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + cal;
        }
        LabelTotal.setText(String.format("%.2f", Totalpagar));
    }


    private void LimpiarFactura() {
        txtCodigoFacturacion.setText("");
        txtNombreFacturacion.setText("");
        txtCantidadFacturacion.setText("");
        txtStockDisponible.setText("");
        txtPrecioFacturacion.setText("");
        txtIdEnvio.setText("");
        txtOrigenFacturacion.setText("");
        txtDestinoFacturacion.setText("");
    }

    private void RegistrarFactura() {
        int cliente = Integer.parseInt(txtIdCV.getText());
        String vendedor = LabelVendedor.getText();
        double monto = Totalpagar;
        fact.setCliente(cliente);
        fact.setVendedor(vendedor);
        fact.setTotal(monto);
        fact.setFecha(fechaActual);
        factDao.RegistrarFactura(fact);
    }

    private void RegistrarDetalle() {
        int id = factDao.IdFactura();
        for (int i = 0; i < TableFacturacion.getRowCount(); i++) {
            int id_pro = Integer.parseInt(TableFacturacion.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TableFacturacion.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(TableFacturacion.getValueAt(i, 3).toString());
            Dv.setId_pro(id_pro);
            Dv.setCantidad(cant);
            Dv.setPrecio(precio);
            Dv.setId(id);
            factDao.RegistrarDetalle(Dv);
        }
        int cliente = Integer.parseInt(txtIdCV.getText());
        factDao.pdfV(id, cliente, Totalpagar, LabelVendedor.getText());
    }
    
    private void RegistrarDetalleEg() {
        int id = emisGuiaDao.idFactura();
        for (int i = 0; i < TableEg.getRowCount(); i++) {
            int id_pro = Integer.parseInt(TableEg.getValueAt(i, 0).toString());
            int cant = 1;
            double precio = Double.parseDouble(TableEg.getValueAt(i, 8).toString());
            DvEg.setId_pro(id_pro);
            DvEg.setCantidad(cant);
            DvEg.setPrecio(precio);
            DvEg.setId(id);
            emisGuiaDao.RegistrarDetalle(Dv);
        }
        int cliente = Integer.parseInt(txtIdCG.getText());
        emisGuiaDao.pdfV(id, cliente, Totalpagar, LabelVendedor.getText());
    }
    
    private void ActualizarStock() {
        for (int i = 0; i < TableFacturacion.getRowCount(); i++) {
            int id = Integer.parseInt(TableFacturacion.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(TableFacturacion.getValueAt(i, 2).toString());
            pro = proDao.BuscarId(id);
            int StockActual = pro.getStock() - cant;
            factDao.ActualizarStock(StockActual, id);
        }
    }
    
    private void LimpiarTableFactura() {
        tmp = (DefaultTableModel) TableFacturacion.getModel();
        int fila = TableFacturacion.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
    private void LimpiarTableEg() {
        tmp = (DefaultTableModel) TableEg.getModel();
        int fila = TableEg.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
    private void LimpiarClienteFactura() {
        txtRucClienteFacturacion.setText("");
        txtNombreClienteFacturacion.setText("");
        txtIdCV.setText("");
    }
    private void LimpiarClienteEg() {
        txtRucEg.setText("");
        txtCodigoEg.setText("");
    }
    private void LimpiarConductorEg() {
        txtPlacaEg.setText("");
        txtDniCEG.setText("");
    }

    private void nuevoUsuario(){
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPass.setText("");
    }
    
    private void llenarProveedor(){
        List<Proveedor> lista = PrDao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            cbxProveedorPro.addItem(new ComboProveedor(id, nombre));
        }
    }
}
