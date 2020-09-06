package integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador.beans.ProductoDTO;
import integrador.interfaces.ProductoDAO;

public class MySQLProductoDAO implements ProductoDAO {

	private Connection conn;
	
	public MySQLProductoDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public ProductoDTO obtenerProducto(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarProducto(ProductoDTO p) {
		// TODO Auto-generated method stub
		String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insert);
			ps.setInt(1, p.getIdProducto());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
			ps.executeUpdate();//
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}

	@Override
	public List<ProductoDTO> obtenerFacturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarProductos(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			try {
				this.agregarProducto(new ProductoDTO(Integer.parseInt(row.get("idProducto")), row.get("nombre"), Float.parseFloat(row.get("valor"))));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
