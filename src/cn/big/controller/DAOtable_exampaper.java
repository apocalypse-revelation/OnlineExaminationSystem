package cn.big.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.big.ifcontroller.IFExamPaper;
import cn.big.model.BeanExamPaper;
import cn.big.model.BeanExamResult;
import cn.big.model.BeanStudent;
import cn.big.util.DBUtil;

public class DAOtable_exampaper implements IFExamPaper{
	public static void main(String[] args) throws Exception {
//		DAOtable_exampaper t = new DAOtable_exampaper();
//		t.get20Qustion();
	}
	public List<BeanExamResult> loadExamResult() throws Exception
	{
		Connection conn=null;
		List<BeanExamResult> lber = new ArrayList<>();
		try{
			conn = DBUtil.getConnection();
			String sql="select * from BeanExamResult where sid=? order by beanexamresult.type desc";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, DAOtable_student.curStudent.getSid());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanExamResult ber = new BeanExamResult();
				ber.setSid(rs.getInt(1));
				ber.setType(rs.getString(3));
				ber.setAanswer(rs.getString(4));
				ber.setBanswer(rs.getString(5));
				ber.setCanswer(rs.getString(6));
				ber.setQuestion_id(rs.getInt(10));
				ber.setRightanswer(rs.getString(7));
				ber.setScore(rs.getInt(8));
				ber.setYouranswer(rs.getString(9));
				ber.setContent(rs.getString(2));
				lber.add(ber);
			}
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
		return lber;
	}
	public void delExamResultBySid(int sid) throws Exception
	{
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			CallableStatement proc = null;
			//10道选择题
			proc = conn.prepareCall("{call SYSTEM.PRO_DEL_EXAMRESULT(?)}");
			proc.setInt(1, sid);
			proc.execute();
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
	public BeanExamResult createExamResult(int sid,String content, String type, String a, String b, String c ,String right, int score, String yours,int eid) throws Exception
	{
		Connection conn = null;
		BeanExamResult ber = new BeanExamResult();
		try{
			conn = DBUtil.getConnection();
			CallableStatement proc = null;
			//10道选择题
			proc = conn.prepareCall("{call SYSTEM.PRO_CREATE_EXAMRESULT(?,?,?,?,?,?,?,?,?,?)}");
			proc.setInt(1, sid);
			proc.setString(2, content);
			proc.setString(3, type);
			proc.setString(4, a);
			proc.setString(5, b);
			proc.setString(6, c);
			proc.setString(7, right);
			proc.setInt(8, score);
			proc.setString(9, yours);
			proc.setInt(10, eid);
			proc.execute();
			//返回
			ber.setSid(sid);
			ber.setType(type);
			ber.setAanswer(a);
			ber.setBanswer(b);
			ber.setCanswer(c);
			ber.setQuestion_id(eid);
			ber.setRightanswer(right);
			ber.setScore(score);
			ber.setYouranswer(yours);
			ber.setContent(content);
			
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
		return ber;
	}
	public void deletePaper() throws Exception
	{
		Connection conn = null;
		try{
			conn=DBUtil.getConnection();
			//删除试卷表数据
			String sql = "delete from BeanExamPaper";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
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
	}
	public List<BeanExamPaper> get20Qustion() throws Exception
	{
		Connection conn = null;
		List<BeanExamPaper> lbp = new ArrayList<>();
		try {
			conn=DBUtil.getConnection();
			//调用存储过程
			CallableStatement proc = null;
			//10道选择题
			proc = conn.prepareCall("{call PRO_GETCHOOSE}");
			proc.execute();
			//10道判断题
			proc = conn.prepareCall("{call PRO_GETJUDGE}");
			proc.execute();
			
			//返回20道题的试卷
			String sql = "select * from BeanExamPaper";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanExamPaper bep = new BeanExamPaper();
				bep.setEid(rs.getInt(1));
				bep.setEcontent(rs.getString(2));
				bep.setEanswera(rs.getString(3));
				bep.setEanswerb(rs.getString(4));
				bep.setEanswerc(rs.getString(5));
				bep.setErightanswer(rs.getString(6));
				bep.setEtype(rs.getString(7));
				bep.setEmark(rs.getInt(8));
				lbp.add(bep);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		try{
//			conn=DBUtil.getConnection();
//			//10道选择题
//			String sql="insert into BEANEXAMPAPER(EID,ECONTENT,ETYPE,EANSWERA,EANSWERB,EANSWERC,ERIGHTANSWER,EMARK) select* from(select QUESTION_ID,QUESTION_BODY, QUESTION_FORM,BRANCH_A,BRANCH_B,BRANCH_C,QUESTION_KEY,QUESTION_MARK from BEANQUESTION order by dbms_random.value) where rownum <= 10 and QUESTION_FORM = 'C' ";
//			
//			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//			pst.execute();
//			pst.close();
//			//10道判断题
//			sql="insert into BEANEXAMPAPER(EID,ECONTENT,ETYPE,EANSWERA,EANSWERB,EANSWERC,ERIGHTANSWER,EMARK) select* from(select QUESTION_ID,QUESTION_BODY, QUESTION_FORM,BRANCH_A,BRANCH_B,BRANCH_C,QUESTION_KEY,QUESTION_MARK from BEANQUESTION order by dbms_random.value) where rownum <= 10 and QUESTION_FORM = 'J' ";
//			pst = conn.prepareStatement(sql);
//			pst.execute();
//			pst.close();
//			
//			//返回20道题的试卷
//			sql = "select * from BeanExamPaper";
//			pst = conn.prepareStatement(sql);
//			java.sql.ResultSet rs = pst.executeQuery();
//			while(rs.next())
//			{
//				BeanExamPaper bep = new BeanExamPaper();
//				bep.setEid(rs.getInt(1));
//				bep.setEcontent(rs.getString(2));
//				bep.setEanswera(rs.getString(3));
//				bep.setEanswerb(rs.getString(4));
//				bep.setEanswerc(rs.getString(5));
//				bep.setErightanswer(rs.getString(6));
//				bep.setEtype(rs.getString(7));
//				bep.setEmark(rs.getInt(8));
//				lbp.add(bep);
//			}
//		}catch(SQLException ex){
//			ex.printStackTrace();
//		}finally{
//			if(conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
		return lbp;
	}
}
