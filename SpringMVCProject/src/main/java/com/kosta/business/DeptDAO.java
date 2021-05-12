package com.kosta.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;
import com.kosta.util.DBUtil;

@Repository("deptDAO_jdbc")
public class DeptDAO implements DeptDAOInterface{
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	JdbcTemplate jdbcTemplatet;	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DeptVO> findAll() {
		List<DeptVO> deptlist = new ArrayList<>();
		String sql = "select * from departments order by 1";
		deptlist = jdbcTemplatet.query(sql, new RowMapper() {

			@SuppressWarnings("unused")
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeptVO dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt(4));
				return null;
			}
		});
		return deptlist;
	}

	
	public List<ManagerVO> findAllManager() {
		List<ManagerVO> mlist = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = 
				"select employee_id, first_name||last_name fullname "+
				"from EMPLOYEES "+
				"where employee_id in ("+ 
				"		select distinct manager_id"+
				"		from employees )";
				
		try {
			conn = datasource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ManagerVO vo = new ManagerVO(rs.getInt(1),rs.getString(2));			 
				mlist.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return mlist;
	}
	public List<LocationVO> findAllLocation() {
		List<LocationVO> loclist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from Locations order by 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				LocationVO vo = new LocationVO();
				vo.setLocation_id(rs.getInt(1));
				vo.setCity(rs.getString("city"));
				loclist.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return loclist;
	}
	
	/*public List<DeptVO> findAll() {
		List<DeptVO> deptlist = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments order by 1";
		try {
			conn = datasource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				DeptVO dept = new DeptVO(rs.getInt(1), 
						rs.getString(2), rs.getInt(3), rs.getInt(4));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return deptlist;
	}*/
	
	public int insert(DeptVO dept) {
		String sql="insert into departments values(?,?,?,?) "; 
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2,  dept.getDepartment_name());
			st.setInt(3,  dept.getManager_id());
			st.setInt(4,  dept.getLocation_id());
			result = st.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}

	public DeptVO findById(int i_deptid) {
		DeptVO dept = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where department_id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, i_deptid);
			rs = st.executeQuery();
			while (rs.next()) {
				dept = new DeptVO(rs.getInt(1), 
						rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return dept;
	}

	public int update(DeptVO dept) {
		String sql=" update departments "
				+ " set Department_name=?, Manager_id=?,Location_id=? "
				+ " where Department_id=? "; 
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(4, dept.getDepartment_id());
			st.setString(1,  dept.getDepartment_name());
			st.setInt(2,  dept.getManager_id());
			st.setInt(3,  dept.getLocation_id());
			result = st.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}

	public int delete(int deptid) {
		String sql=" delete from departments "
				+ " where Department_id=? "; 
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			result = st.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}	
}
