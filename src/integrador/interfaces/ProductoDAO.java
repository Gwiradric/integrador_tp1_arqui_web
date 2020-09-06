package integrador.interfaces;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import integrador.beans.ProductoDTO;

public interface ProductoDAO {

	public ProductoDTO obtenerProducto(int id);
	
	public void agregarProducto(ProductoDTO p);
	
	public void agregarProductos(CSVParser c);
	
	public List<ProductoDTO> obtenerFacturas();
}
