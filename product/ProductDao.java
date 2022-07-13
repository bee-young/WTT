package db.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.student.DBConnect;

public class ProductDao implements ProductInterface{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ProductDao() {
	}
	
	
	@Override
	public String insert(ProductVo vo) {
		conn = new DBConnect().getCon();
		String msg="";
		String sql = "insert into product(code, nal, ea, price, amt) "
				       + " values(?,?,?,?, ea*price) ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getNal());
			ps.setInt(3,  vo.getEa());
			ps.setInt(4, vo.getPrice());
			
			conn.setAutoCommit(false);
			int n = ps.executeUpdate();
			
			if(n>0) {
				conn.commit();
				msg = "자료가 저장되었습니다.";
			}else {
				conn.rollback();
				msg = "저장중 오류 발생";
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			msg = ex.getMessage();
			
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return msg;
	}

	@Override
	public String update(ProductVo vo) {
		conn = new DBConnect().getCon();
		String msg = "";
		String sql = "update product set code=?, nal=?, ea=?, price=?, amt=ea*price "
				       + " where sno=? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getNal());
			ps.setInt(3, vo.getEa());
			ps.setInt(4, vo.getPrice());
			ps.setInt(5, vo.getSno());
			
			conn.setAutoCommit(false);
			int n = ps.executeUpdate();
			
			if(n>0) {
				conn.commit();
				msg = "자료가 수정되었습니다.";
			}else {
				conn.rollback();
				msg = "자료 수정중 오류 발생";
			}
					
		}catch(Exception ex) {
			ex.printStackTrace();
			msg = ex.getMessage();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String delete(int sno) {
		conn = new DBConnect().getCon();
		String msg = "";
		String sql = "delete from product where sno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			conn.setAutoCommit(false);
			int n = ps.executeUpdate();
			
			if(n>0) {
				conn.commit();
				msg = "자료가 삭제되었습니다.";
			}else {
				conn.rollback();
				msg = "자료 삭제중 오류 발생";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			msg = ex.getMessage();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return msg;
	}

	@Override
	public List<ProductVo> select(String findStr) {
		conn = new DBConnect().getCon();
		List<ProductVo> list = new ArrayList<ProductVo>();
		String sql = " select pr.code, pr.price, sno, codeName, nal, spec, ea,  amt  "
	       + " from product pr join parts p "
	       + " on pr.code = p.code "
	       + " where pr.code like ? or codeName like ? "
	       + " or spec like ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + findStr + "%");
			ps.setString(2, "%" + findStr + "%");
			ps.setString(3, "%" + findStr + "%");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setSno(rs.getInt("sno"));
				vo.setCode(rs.getString("code"));
				vo.setCodeName(rs.getString("codeName"));
				vo.setSpec(rs.getString("spec"));
				vo.setNal(rs.getString("nal"));
				vo.setEa(rs.getInt("ea"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmt(rs.getInt("amt"));				
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
	public ProductVo selectOne(int sno) {
		conn = new DBConnect().getCon();
		ProductVo vo = null;
		String sql = " select pr.code, pr.price, pr.nal,  sno, codeName, spec, ea,  amt  "
				       + " from product pr join parts p "
				       + " on pr.code = p.code "
				       + " where sno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVo();
				vo.setSno(rs.getInt("sno"));
				vo.setCode(rs.getString("code"));
				vo.setCodeName(rs.getString("codeName"));
				vo.setSpec(rs.getString("spec"));
				vo.setNal(rs.getString("nal"));
				vo.setEa(rs.getInt("ea"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmt(rs.getInt("amt"));				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public ProductVo selectOne(String code) {
		conn = new DBConnect().getCon();
		ProductVo vo = null;
		String sql = " select * from parts where code = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo = new ProductVo();
				vo.setCode(rs.getString("code"));
				vo.setCodeName(rs.getString("codeName"));
				vo.setSpec(rs.getString("spec"));
				vo.setPrice(rs.getInt("price"));
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

	/*
	public ProductVo getVo(ResultSet rs) throws Exception{
		ProductVo vo = new ProductVo();
		vo.setSno(rs.getInt("sno"));
		vo.setCode(rs.getString("code"));
		vo.setCodeName(rs.getString("codeName"));
		vo.setSpec(rs.getString("spec"));
		vo.setNal(rs.getString("nal"));
		vo.setEa(rs.getInt("ea"));
		vo.setPrice(rs.getInt("price"));
		vo.setAmt(rs.getInt("amt"));
		
		return vo;
	}
*/	

}
