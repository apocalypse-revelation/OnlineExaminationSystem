package cn.big.ifcontroller;

import cn.big.model.BeanStudent;

public interface IFStudent {
	
	public BeanStudent login(String sidcard, String spwd) throws Exception;
	public void reg(String sname, String sidcard, String stelnum, String spwd1,String spwd2,String sphoto) throws Exception;
	public String loadScore() throws Exception;
}
