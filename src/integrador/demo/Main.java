package integrador.demo;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import integrador.beans.ClienteDTO;
import integrador.beans.FacturaDTO;
import integrador.beans.FacturaProductoDTO;
import integrador.beans.ProductoDTO;
import integrador.dao.DAOFactory;
import integrador.interfaces.ClienteDAO;
import integrador.interfaces.FacturaDAO;
import integrador.interfaces.FacturaProductoDAO;
import integrador.interfaces.ProductoDAO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAOFactory fabrica = DAOFactory.obtenerDAOFactory(1, "root", "password");
		
		fabrica.crearTablas();
		
		ClienteDAO clientes = fabrica.obtenerClienteDAO();
		ProductoDAO productos = fabrica.obtenerProductoDAO();
		FacturaDAO facturas = fabrica.obtenerFacturaDAO();
		FacturaProductoDAO facturasProducto = fabrica.obtenerFacturaProductoDAO();

		cargarBD(fabrica, clientes, productos, facturas, facturasProducto);
		
	}

	private static void cargarBD(DAOFactory fabrica, ClienteDAO clientes, ProductoDAO productos, FacturaDAO facturas,
			FacturaProductoDAO facturasProducto) {
		// TODO Auto-generated method stub
		try {
			CSVParser clientesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/clientes.csv"));
			clientes.agregarClientes(clientesCSV);
			
			CSVParser productosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/productos.csv"));
			productos.agregarProductos(productosCSV);
			
			CSVParser facturasCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/facturas.csv"));
			facturas.agregarFacturas(facturasCSV);
			
			CSVParser facturasProductosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/facturas-productos.csv"));
			facturasProducto.agregarFacturasProductos(facturasProductosCSV);	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
