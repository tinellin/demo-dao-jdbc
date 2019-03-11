package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dep) {

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO department " + "(Id, Name) " + "VALUES " + "(?, ?) ",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, dep.getId());
			ps.setString(2, dep.getName());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected <= 0) {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department dep) {

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE department " + "SET Name = ? " + "WHERE Id = ? ");

			ps.setString(1, dep.getName());
			ps.setInt(2, dep.getId());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected <= 0) {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE Id = ? ");

			ps.setInt(1, id);

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected <= 0) {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (DbIntegrityException e) {
			throw new DbIntegrityException(e.getMessage());

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM department " + "WHERE Id = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
				return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Department> findAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM department");

			rs = ps.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());

		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {

		Department dep = new Department();

		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}
}