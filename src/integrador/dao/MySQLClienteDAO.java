package integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador.beans.ClienteDTO;
import integrador.interfaces.ClienteDAO;

public class MySQLClienteDAO implements ClienteDAO {
	
	private Connection conn;
	
	public MySQLClienteDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public ClienteDTO obtenerCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarCliente(ClienteDTO c) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)");
			ps.setInt(1, c.getIdCliente());
			ps.setString(2,c.getNombre());
			ps.setString(3, c.getEmail());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	@Override
	public List<ClienteDTO> obtenerClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarClientes(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			this.agregarCliente(new ClienteDTO(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email")));
		}
	}

}
