package db.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.student.DBConnect;

public class PartsDao implements PartsInterface{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public PartsDao() {
	}

	@Override
	public boolean insert(PartsVo vo) {
		boolean b = false;
		conn = new DBConnect().getCon();
		
		String sql = "insert into parts(code, codeName, spec, price) "
				       + " values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getCodeName());
			ps.setString(3, vo.getSpec());
			ps.setInt(4, vo.getPrice());
			
			conn.setAutoCommit(false);
			int n = ps.executeUpdate();
			if(n>0) {
				conn.commit();
				b=true;
			}else {
				conn.rollback();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return b;
	}

	@Override
	public List<PartsVo> select(String findStr) {
		conn = new DBConnect().getCon();

		List<PartsVo> list = new ArrayList<PartsVo>();
		String sql = "select * from parts where code like ? "
				       + " or codeName like ? or spec like ? order by codeName";
		int no=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + findStr + "%");
			ps.setString(2, "%" + findStr + "%");
			ps.setString(3, "%" + findStr + "%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				no++;
				PartsVo vo = getVo(rs);
				vo.setNo(no);
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public PartsVo selectOne(String code) {
		conn = new DBConnect().getCon();
		PartsVo vo = null;
		String sql = "select * from parts where code = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = getVo(rs);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public boolean update(PartsVo vo) {
		conn = new DBConnect().getCon();
		boolean b = false;
		String sql = "update parts set codeName=? , spec=?, price=? "
				       + " where code = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCodeName());
			ps.setString(2, vo.getSpec());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getCode());
			
			conn.setAutoCommit(false);
			int n = ps.executeUpdate();
			if(n>0) {
				conn.commit();
				b=true;
			}else {
				conn.rollback();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public boolean delete(String code) {
		conn = new DBConnect().getCon();
		boolean b = false;
		String sql = "delete from parts where code=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			
			conn.setAutoCommit(false);
			int n = ps.executeUpdate();
			if(n>0) {
				conn.commit();
				b=true;
			}else {
				conn.rollback();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	
	public PartsVo getVo(ResultSet rs) throws Exception {
		
		PartsVo vo = new PartsVo(
				rs.getString("code"),
				rs.getString("codeName"),
				rs.getString("spec"),
				rs.getInt("price")
				);
		return vo;
		
	}

}
