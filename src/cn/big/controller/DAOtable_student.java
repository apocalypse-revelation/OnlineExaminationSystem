package cn.big.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import cn.big.ifcontroller.IFStudent;
import cn.big.model.BeanStudent;
import cn.big.util.DBUtil;

public class DAOtable_student implements IFStudent{

	public static BeanStudent curStudent = null;
	public String loadScore() throws Exception
	{
		Connection conn=null;
		String score = "���޳ɼ�";
		try{
			conn=DBUtil.getConnection();
			String sql="select sscore from BEANSTUDENT where sid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, curStudent.getSid());
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(rs.getString(1)!=null)
				score = rs.getString(1);
			rs.close();
			pst.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return score;
	}
	public BeanStudent login(String sidcard, String spwd) throws Exception
	{
		Connection conn=null;
		BeanStudent bs=new BeanStudent();
		try{
			conn=DBUtil.getConnection();
			String sql="select * from BEANSTUDENT where sidcard = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sidcard);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) 
				throw new Exception("�˺Ų�����");
			if(!rs.getString(5).equals(spwd))
			{
				JOptionPane.showMessageDialog(null, "�������", "��ʾ",JOptionPane.ERROR_MESSAGE);
				return null;
			}
			bs.setSid(rs.getInt(1));
			bs.setSname(rs.getString(2));
			bs.setSidcard(rs.getString(3));
			bs.setStelnum(rs.getString(4));
			bs.setSpwd(rs.getString(5));
			bs.setSphoto(rs.getString(6));
			rs.close();
			pst.close();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new Exception("���ݿ�����쳣");
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return bs;
	}
	public void reg(String sname, String sidcard, String stelnum, String spwd1,String spwd2,String sphoto) throws Exception
	{
//		final int ID_LENGTH_LIMITED = 6;
//		if("".equals(stelnum)||"".equals(sidcard)||"".equals(sname))
//			throw new Exception("������Ϣ����Ϊ�գ�");
//		if(spwd1==null || "".equals(spwd1)) throw new Exception("���벻��Ϊ��");
//		if(!spwd1.equals(spwd2)) throw new Exception("�������������Ҫһ��");
//		if(sid == 0 || "".equals(sid)) throw new Exception("����id����Ϊ��");
//		if(String.valueOf(sid).length()!=ID_LENGTH_LIMITED)
//			throw new Exception("ע��Ŀ���id�������6λ");
		
		Connection conn=null;
		BeanStudent bs=new BeanStudent();
		try{
			conn=DBUtil.getConnection();
			String sql="select * from BEANSTUDENT where sidcard = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, sidcard);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) 
//				throw new Exception("�����Ѿ�����");
			{
				JOptionPane.showMessageDialog(null, "�������֤�����Ѵ��ڣ�", "��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			CallableStatement proc = null;
			proc = conn.prepareCall("{ call SYSTEM.PRO_USER_REG(?,?,?,?,?)}");
			proc.setString(1, sname);
			proc.setString(2, sidcard);
			proc.setString(3, stelnum);
			proc.setString(4, spwd1);
			proc.setString(5, sphoto);
			//proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
//			sql="insert into BEANSTUDENT values(?,?,?,?,?,?,?)";
//			pst=conn.prepareStatement(sql);
//			pst.setInt(1, sid);
//			pst.setString(2, sname);
//			pst.setString(3, sidcard);
//			pst.setString(4, stelnum);
//			pst.setString(5, spwd1);
//			pst.setString(6, null);
//			pst.setString(7, sphoto);
//			pst.execute();
//			pst.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
