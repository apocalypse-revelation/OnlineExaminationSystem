package cn.big.util;

import cn.big.controller.DAOtable_exampaper;
import cn.big.controller.DAOtable_student;
import cn.big.ifcontroller.IFExamPaper;
import cn.big.ifcontroller.IFStudent;

public class FuncUtil {

	public static IFStudent studentController = new DAOtable_student();
	//public static IFQuestion questionController = new DAOtable_question();
	public static IFExamPaper examController = new DAOtable_exampaper();
	
}
