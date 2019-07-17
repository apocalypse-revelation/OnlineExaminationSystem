package cn.big.ifcontroller;

import java.util.List;

import cn.big.model.BeanExamPaper;
import cn.big.model.BeanExamResult;

public interface IFExamPaper {

	public List<BeanExamPaper> get20Qustion() throws Exception;
	public void deletePaper() throws Exception;
	public BeanExamResult createExamResult(int sid,String content, String type, String a, String b, String c ,String right, int score, String yours,int eid) throws Exception;
	public void delExamResultBySid(int sid) throws Exception;
	public List<BeanExamResult> loadExamResult() throws Exception;
}
