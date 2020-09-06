package integrador.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import integrador.interfaces.ClienteDAO;
import integrador.interfaces.FacturaDAO;
import integrador.interfaces.FacturaProductoDAO;
import integrador.interfaces.ProductoDAO;

public class DERBYDAOFactory extends DAOFactory {
	
	protected ClienteDAO clientes = null;
	protected FacturaDAO facturas = null;
	protected FacturaProductoDAO facturasProductos = null;
	protected ProductoDAO productos = null;
	
	protected Connection conn = null;
	
	final private String uri = "jdbc:derby:integrador;create=true";
	
	public DERBYDAOFactory() throws SQLException {
		// TODO Auto-generated constructor stub
		this.conn = DriverManager.getConnection(uri);
	}

	@Override
	public ClienteDAO obtenerClienteDAO() {
		// TODO Auto-generated method stub
		if (this.clientes == null)
			return new MySQLClienteDAO(conn);
		return clientes;
	}

	@Override
	public FacturaDAO obtenerFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaProductoDAO obtenerFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoDAO obtenerProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void crearTablas() {
		// TODO Auto-generated method stub
		try {
	
		String tablaCliente = "CREATE TABLE Cliente (\n" + 
					"    idCliente int NOT NULL,\n" + 
					"    nombre varchar(500) NOT NULL,\n" + 
					"    email varchar(150) NOT NULL,\n" + 
					"    CONSTRAINT Cliente_pk PRIMARY KEY (idCliente)\n" + 
					")";
		
		conn.prepareStatement(tablaCliente).execute();
		
		String tablaFactura = "CREATE TABLE Factura (\n" + 
				"    idFactura int NOT NULL,\n" + 
				"    idCliente int NOT NULL,\n" + 
				"    CONSTRAINT Factura_pk PRIMARY KEY (idFactura)\n" + 
				")";
		conn.prepareStatement(tablaFactura).execute();
		
		String tablaFacturaProducto = "CREATE TABLE Factura_Producto (\n" + 
				"    idProducto int NOT NULL,\n" + 
				"    idFactura int NOT NULL,\n" + 
				"    cantidad int NOT NULL,\n" + 
				"    CONSTRAINT Factura_Producto_pk PRIMARY KEY (idProducto,idFactura)\n" + 
				")";
		conn.prepareStatement(tablaFacturaProducto).execute();
		
		String tablaProducto = "CREATE TABLE Producto (\n" + 
				"    idProducto int NOT NULL,\n" + 
				"    nombre varchar(45) NOT NULL,\n" + 
				"    valor float NOT NULL,\n" + 
				"    CONSTRAINT Producto_pk PRIMARY KEY (idProducto)\n" + 
				")";
		
		conn.prepareStatement(tablaProducto).execute();
		
		String relacion_Factura_Cliente = "ALTER TABLE factura ADD CONSTRAINT fk_factura_cliente FOREIGN KEY fk_factura_cliente (idCliente)\n" + 
				"    REFERENCES cliente (idCliente);";
		conn.prepareStatement(relacion_Factura_Cliente).execute();
		
		
		String relacionFacturaProductoFactura = "ALTER TABLE factura_producto ADD CONSTRAINT fk_factura_producto_factura FOREIGN KEY fk_factura_producto_factura (idFactura)\n" + 
				"    REFERENCES factura (idFactura);";
		conn.prepareStatement(relacionFacturaProductoFactura).execute();
		
		String relacionFacturaProducto = "ALTER TABLE factura_producto ADD CONSTRAINT fk_factura_producto_producto FOREIGN KEY fk_factura_producto_producto (idProducto)\n" + 
				"    REFERENCES producto (idProducto);";
		conn.prepareStatement(relacionFacturaProducto).execute();
	
		conn.commit();	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
