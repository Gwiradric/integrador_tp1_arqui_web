package integrador.interfaces;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import integrador.beans.FacturaProductoDTO;

public interface FacturaProductoDAO {
	
	public FacturaProductoDTO obtenerCliente(int id);
	
	public void agregarFacturaProducto(FacturaProductoDTO f);
	
	public void agregarFacturasProductos(CSVParser c);
	
	public List<FacturaProductoDTO> obtenerFacturaProductos();
}
