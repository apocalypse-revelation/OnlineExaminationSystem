package cn.big.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.big.controller.DAOtable_exampaper;
import cn.big.controller.DAOtable_student;
import cn.big.model.BeanExamPaper;
import cn.big.model.BeanExamResult;
import cn.big.model.BeanStudent;
import cn.big.util.FuncUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmExamMain extends JFrame {

	private JPanel contentPane;
	String contentitem = "";//题目文本内容
	List<String> ls = new ArrayList<>();
	String item1 = "";//选项a文本内容
	String item2 = "";//选项b文本内容
	String item3 = "";//选项c文本内容
	String typeitem = "题型";
	private int score = 0;//总得分
	private int seq = 0;//题目顺序
	private int redisplay_seq = 0;
	List<BeanExamPaper> lbep;
	static String rightcontent;//选择题的正确答案
	int singleScore = 0;//一道题的得分
	List<BeanExamResult> lber;
	// 定义HTML标签的正则表达式
	private static final String regEx_html = "<[^>]+>"; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmExamMain frame = new FrmExamMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//正则表达式过滤<html>标签
	public static String delhtml(String htmlStr) 
	{
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        htmlStr = htmlStr.replaceAll(" ", ""); //过滤 
        return htmlStr.trim(); // 返回文本字符串
	}
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrmExamMain() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_redisplay = new JButton("\u8BD5\u9898\u91CD\u73B0");
		button_redisplay.setBounds(187, 471, 100, 23);
		contentPane.add(button_redisplay);
		
		JButton button_back = new JButton("\u9000\u51FA");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//退出则删除该考生的考卷
				try {
					FuncUtil.examController.delExamResultBySid(DAOtable_student.curStudent.getSid());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		button_back.setBounds(187, 498, 100, 23);
		contentPane.add(button_back);
		
		JButton btnNewButton_submit = new JButton("\u4EA4\u5377");
		btnNewButton_submit.setBounds(187, 419, 100, 23);
		contentPane.add(btnNewButton_submit);
		btnNewButton_submit.setVisible(false);
		button_back.setVisible(false);
		button_redisplay.setVisible(false);
		
		JButton button_forward = new JButton("\u505A\u4E0B\u4E00\u9898");
		button_forward.setBounds(179, 445, 118, 23);
		contentPane.add(button_forward);
		
		JButton button_up = new JButton("\u4E0A\u4E00\u9898");
		
		button_up.setBounds(77, 445, 93, 23);
		contentPane.add(button_up);
		button_up.setVisible(false);
		
		JButton button_down = new JButton("\u4E0B\u4E00\u9898");
		
		button_down.setBounds(307, 445, 93, 23);
		contentPane.add(button_down);
		button_down.setVisible(false);
		
		JLabel label_score = new JLabel("");
		label_score.setFont(new Font("宋体", Font.BOLD, 25));
		label_score.setHorizontalAlignment(SwingConstants.CENTER);
		label_score.setBounds(157, 183, 145, 114);
		contentPane.add(label_score);
		label_score.setVisible(false);
		
		JLabel label_yours = new JLabel("");
		label_yours.setBounds(10, 350, 455, 65);
		contentPane.add(label_yours);
		label_yours.setVisible(false);
		
		JLabel label_etype = new JLabel();
		label_etype.setHorizontalAlignment(SwingConstants.CENTER);
		label_etype.setFont(new Font("宋体", Font.BOLD, 25));
		label_etype.setBounds(175, 0, 123, 46);
		contentPane.add(label_etype);
		
		JLabel lblNewLabel_econtent = new JLabel();
		lblNewLabel_econtent.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_econtent.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_econtent.setBounds(10, 56, 455, 65);
		contentPane.add(lblNewLabel_econtent);
		
		
		JRadioButton rdbtna = new JRadioButton();
		rdbtna.setVerticalAlignment(SwingConstants.TOP);
		rdbtna.setBounds(10, 127, 455, 65);
		contentPane.add(rdbtna);
		
		JRadioButton rdbtnb = new JRadioButton();
		rdbtnb.setVerticalAlignment(SwingConstants.TOP);
		rdbtnb.setBounds(10, 201, 455, 65);
		contentPane.add(rdbtnb);
		
		JRadioButton rdbtnc = new JRadioButton();
		rdbtnc.setVerticalAlignment(SwingConstants.TOP);
		rdbtnc.setBounds(10, 279, 455, 65);
		contentPane.add(rdbtnc);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtna);
		bg.add(rdbtnb);
		bg.add(rdbtnc);
		//rdbtna.setSelected(true);
		
		btnNewButton_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeanExamPaper.curScore = score;
				JOptionPane.showMessageDialog(null, "您得了"+score+"分！", "提示",JOptionPane.INFORMATION_MESSAGE);
				
				try {
					FuncUtil.examController.deletePaper();
					button_back.setVisible(true);
					button_redisplay.setVisible(true);
					btnNewButton_submit.setVisible(false);
					lblNewLabel_econtent.setVisible(false);
					label_etype.setText("考后界面");
					rdbtna.setVisible(false);
					rdbtnb.setVisible(false);
					rdbtnc.setVisible(false);
					label_score.setVisible(true);
					label_score.setText("分数： "+score);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		//第一道题的题目显示
		new Thread(new Runnable() {
            @Override
            public void run() {
            	try {
            		lbep = FuncUtil.examController.get20Qustion();
    				//题目内容
    				contentitem = lbep.get(seq).getEcontent();
    				lblNewLabel_econtent.setText("<html>"+contentitem+"</html>");
    				//选择题
    				if(lbep.get(seq).getEtype().equals("C"))
    				{
    					item1 = lbep.get(seq).getEanswera();
    					item2 = lbep.get(seq).getEanswerb();
    					item3 = lbep.get(seq).getEanswerc();
    					//为正确答案的内容赋值
    					if(lbep.get(seq).getErightanswer().equals("A"))
    						rightcontent = item1;
    					else if(lbep.get(seq).getErightanswer().equals("B"))
    						rightcontent = item2;
    					else if(lbep.get(seq).getErightanswer().equals("C"))
    						rightcontent = item3;
    					System.out.println("参考答案："+rightcontent);
    					ls.add(item1);
    					ls.add(item2);
    					ls.add(item3);
    					//打乱顺序以实现随机选项的效果
    					Collections.shuffle(ls);
    					typeitem = "选择题";
    					
    					label_etype.setText(typeitem);
    					rdbtna.setText("<html>"+ls.get(0)+"</html>");
    					rdbtnb.setText("<html>"+ls.get(1)+"</html>");
    					rdbtnc.setText("<html>"+ls.get(2)+"</html>");
    					ls.clear();
    				}
    				//判断题
    				else if(lbep.get(seq).getEtype().equals("J"))
    				{
    					typeitem = "判断题";
    					label_etype.setText(typeitem);
    					rdbtna.setText("正确");
    					rdbtnb.setText("错误");
    					rdbtnc.setVisible(false);
    				}
            		Thread.sleep(100);    
            	} catch (InterruptedException e) {
            		e.printStackTrace();
            	} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	             
            }
		}).start();
		//”下一题“按钮监听
				button_forward.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						/*
						 * 判题区
						 */
						//选择题
						if(lbep.get(seq).getEtype().equals("C"))
						{
							if(!rdbtna.isSelected()&&!rdbtnb.isSelected()&&!rdbtnc.isSelected())
							{
								JOptionPane.showMessageDialog(null, "请选择！", "提示",JOptionPane.ERROR_MESSAGE);
								return;
							}
							if(rdbtna.isSelected())
							{
								if(rdbtna.getText().equals("<html>"+rightcontent+"</html>"))
								{	singleScore = 1;
									score = score + lbep.get(seq).getEmark();
								}
								else
									singleScore = 0;
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, item1, item2, item3, rightcontent, singleScore,delhtml(rdbtna.getText()),lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else if(rdbtnb.isSelected())
							{
								if(rdbtnb.getText().equals("<html>"+rightcontent+"</html>"))
								{	
									singleScore = 1;
									score = score + lbep.get(seq).getEmark();
								}
								else
									singleScore = 0;
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, item1, item2, item3, rightcontent, singleScore,delhtml(rdbtnb.getText()),lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else if(rdbtnc.isSelected())
							{
								if(rdbtnc.getText().equals("<html>"+rightcontent+"</html>"))
								{	
									singleScore = 1;
									score = score + lbep.get(seq).getEmark();
								}
								else
									singleScore = 0;
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, item1, item2, item3, rightcontent, singleScore,delhtml(rdbtnc.getText()),lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						//判断题
						else if(lbep.get(seq).getEtype().equals("J"))
						{
							if(!rdbtna.isSelected()&&!rdbtnb.isSelected())
							{
								JOptionPane.showMessageDialog(null, "请选择！", "提示",JOptionPane.ERROR_MESSAGE);
								return;
							}
							//答案正确又选了正确：正确
							if(lbep.get(seq).getErightanswer().equals("A")&&rdbtna.isSelected())
							{
								singleScore = 1;
								score = score + lbep.get(seq).getEmark();
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, null, null, null, "正确", singleScore,"正确",lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							//答案正确却选了错误：错误
							else if(lbep.get(seq).getErightanswer().equals("A")&&rdbtnb.isSelected())
							{
								singleScore = 0;
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, null, null, null, "正确", singleScore,"错误",lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							//答案错误又选了错误：正确
							if(lbep.get(seq).getErightanswer().equals("B")&&rdbtnb.isSelected())
							{
								singleScore = 1;
								score = score + lbep.get(seq).getEmark();
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, null, null, null, "错误", singleScore,"错误",lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							//答案错误却选了正确：错误
							else if(lbep.get(seq).getErightanswer().equals("B")&&rdbtna.isSelected())
							{
								singleScore = 0;
								try {
									FuncUtil.examController.createExamResult(DAOtable_student.curStudent.getSid(), contentitem, typeitem, null, null, null, "错误", singleScore,"正确",lbep.get(seq).getEid());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						/*
						 * 交卷区
						 */
						seq++;
						//完成最后一道题不能点下一题
						if(seq == 20)
						{
							label_etype.setVisible(false);
							lblNewLabel_econtent.setVisible(false);
							rdbtna.setVisible(false);
							rdbtnb.setVisible(false);
							rdbtnc.setVisible(false);
							JOptionPane.showMessageDialog(null, "您已完成试卷，请提交！", "提示",JOptionPane.INFORMATION_MESSAGE);
							button_forward.setVisible(false);
							btnNewButton_submit.setVisible(true);
							lbep.clear();
							ls.clear();
							return;
						}
						/*
						 * 题目显示区
						 */
						//题目内容
						contentitem = lbep.get(seq).getEcontent();
						lblNewLabel_econtent.setText("<html>"+contentitem+"</html>");
						//选择题内容
						if(lbep.get(seq).getEtype().equals("C"))
						{
							item1 = lbep.get(seq).getEanswera();
							item2 = lbep.get(seq).getEanswerb();
							item3 = lbep.get(seq).getEanswerc();
							ls.add(item1);
							ls.add(item2);
							ls.add(item3);
							//为正确答案的内容赋值
							if(lbep.get(seq).getErightanswer().equals("A"))
								rightcontent = item1;
							else if(lbep.get(seq).getErightanswer().equals("B"))
								rightcontent = item2;
							else if(lbep.get(seq).getErightanswer().equals("C"))
								rightcontent = item3;
							//打印参考答案
							System.out.println("参考答案："+rightcontent);
							//打乱顺序以实现随机选项的效果
							Collections.shuffle(ls);
							
							typeitem = "选择题";
							label_etype.setText(typeitem);
							rdbtna.setText("<html>"+ls.get(0)+"</html>");
							rdbtnb.setText("<html>"+ls.get(1)+"</html>");
							rdbtnc.setText("<html>"+ls.get(2)+"</html>");
							ls.clear();
						}
						//判断题内容
						else if(lbep.get(seq).getEtype().equals("J"))
						{
							typeitem = "判断题";
							label_etype.setText(typeitem);
							rdbtna.setText("正确");
							rdbtnb.setText("错误");
							rdbtnc.setVisible(false);
							//参考答案
							if(lbep.get(seq).getErightanswer().equals("A"))
							{
								System.out.println("参考答案：正确");
							}
							else if(lbep.get(seq).getErightanswer().equals("B"))
							{
								System.out.println("参考答案：错误");
							}
						}
						bg.clearSelection();
					}
				});
				button_redisplay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						button_redisplay.setVisible(false);
						button_down.setVisible(true);
						label_score.setVisible(false);
						try {
							lber = FuncUtil.examController.loadExamResult();
							lblNewLabel_econtent.setVisible(true);
							label_etype.setVisible(true);
							rdbtna.setVisible(true);
							rdbtnb.setVisible(true);
							rdbtnc.setVisible(true);
							label_yours.setVisible(true);
							
							lblNewLabel_econtent.setText("<html>"+lber.get(redisplay_seq).getContent()+"</html>");
							label_etype.setText(lber.get(redisplay_seq).getType());
							if(!lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
							{
								label_yours.setText("<html><br>[回答错误]</br><br>您的答案是:  "+lber.get(0).getYouranswer()+"</br></html>");
								label_yours.setForeground(Color.green);
							}
							else if(lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
							{
								label_yours.setText("<html><br>[回答正确]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
								label_yours.setForeground(Color.RED);
							}
							
							rdbtna.setText("<html>"+lber.get(redisplay_seq).getAanswer()+"</html>");
							rdbtnb.setText("<html>"+lber.get(redisplay_seq).getBanswer()+"</html>");
							rdbtnc.setText("<html>"+lber.get(redisplay_seq).getCanswer()+"</html>");
							
							if(lber.get(redisplay_seq).getAanswer().equals(lber.get(redisplay_seq).getRightanswer()))
							{
								rdbtna.setText("<html><br>"+lber.get(redisplay_seq).getAanswer()+"</br><br>（正确答案）</br></html>");
								rdbtna.setForeground(Color.RED);
							}
							if(lber.get(redisplay_seq).getBanswer().equals(lber.get(redisplay_seq).getRightanswer()))
							{
								rdbtnb.setText("<html><br>"+lber.get(redisplay_seq).getBanswer()+"</br><br>（正确答案）</br></html>");
								rdbtnb.setForeground(Color.RED);
							}
							if(lber.get(redisplay_seq).getCanswer().equals(lber.get(redisplay_seq).getRightanswer()))
							{
								rdbtnc.setText("<html><br>"+lber.get(redisplay_seq).getCanswer()+"</br><br>（正确答案）</br></html>");
								rdbtnc.setForeground(Color.RED);
							}
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				button_down.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						redisplay_seq++;
						if(redisplay_seq >= 1)
							button_up.setVisible(true);
						if(redisplay_seq == 19)
							button_down.setVisible(false);
						try {
							lber = FuncUtil.examController.loadExamResult();
							//选择题情况
							if(lber.get(redisplay_seq).getType().equals("选择题"))
							{
								rdbtna.setForeground(Color.BLACK);
								rdbtnb.setForeground(Color.BLACK);
								rdbtnc.setForeground(Color.BLACK);
								lblNewLabel_econtent.setText("<html>"+lber.get(redisplay_seq).getContent()+"</html>");
								label_etype.setText(lber.get(redisplay_seq).getType());
								if(!lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答错误]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.green);
								}
								else if(lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答正确]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.RED);
								}
								
								rdbtna.setText("<html>"+lber.get(redisplay_seq).getAanswer()+"</html>");
								rdbtnb.setText("<html>"+lber.get(redisplay_seq).getBanswer()+"</html>");
								rdbtnc.setText("<html>"+lber.get(redisplay_seq).getCanswer()+"</html>");
								//如果选项A是正确答案就加红
								if(lber.get(redisplay_seq).getAanswer().equals(lber.get(redisplay_seq).getRightanswer()))
								{
									rdbtna.setText("<html><br>"+lber.get(redisplay_seq).getAanswer()+"</br><br>（正确答案）</br></html>");
									rdbtna.setForeground(Color.RED);
								}
								//如果选项B是正确答案就加红
								if(lber.get(redisplay_seq).getBanswer().equals(lber.get(redisplay_seq).getRightanswer()))
								{
									rdbtnb.setText("<html><br>"+lber.get(redisplay_seq).getBanswer()+"</br><br>（正确答案）</br></html>");
									rdbtnb.setForeground(Color.RED);
								}
								//如果选项C是正确答案就加红
								if(lber.get(redisplay_seq).getCanswer().equals(lber.get(redisplay_seq).getRightanswer()))
								{
									rdbtnc.setText("<html><br>"+lber.get(redisplay_seq).getCanswer()+"</br><br>（正确答案）</br></html>");
									rdbtnc.setForeground(Color.RED);
								}
								
							}
							else if(lber.get(redisplay_seq).getType().equals("判断题"))
							{
								rdbtna.setForeground(Color.BLACK);
								rdbtnb.setForeground(Color.BLACK);
								rdbtnc.setVisible(false);
								lblNewLabel_econtent.setText("<html>"+lber.get(redisplay_seq).getContent()+"</html>");
								label_etype.setText(lber.get(redisplay_seq).getType());
								if(!lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答错误]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.green);
								}
								else if(lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答正确]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.RED);
								}
								
								rdbtna.setText("正确");
								rdbtnb.setText("错误");
								//如果正确，a就加红
								if(lber.get(redisplay_seq).getRightanswer().equals("正确"))
									rdbtna.setForeground(Color.RED);
								//如果错误，b就加红
								if(lber.get(redisplay_seq).getRightanswer().equals("错误"))
									rdbtnb.setForeground(Color.RED);
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				button_up.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						redisplay_seq--;
						System.out.println(redisplay_seq);
						if(redisplay_seq < 20 || redisplay_seq >= 0)
							button_down.setVisible(true);
						if(redisplay_seq == 0)
							button_up.setVisible(false);
						
						try {
							lber = FuncUtil.examController.loadExamResult();
							/*
							 * 选择题
							 */
							if(lber.get(redisplay_seq).getType().equals("选择题"))
							{
								rdbtna.setForeground(Color.BLACK);
								rdbtnb.setForeground(Color.BLACK);
								rdbtnc.setForeground(Color.BLACK);
								lblNewLabel_econtent.setText("<html>"+lber.get(redisplay_seq).getContent()+"</html>");
								label_etype.setText(lber.get(redisplay_seq).getType());
								if(!lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答错误]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.green);
								}
								else if(lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答正确]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.RED);
								}
								rdbtna.setText("<html>"+lber.get(redisplay_seq).getAanswer()+"</html>");
								rdbtnb.setText("<html>"+lber.get(redisplay_seq).getBanswer()+"</html>");
								rdbtnc.setText("<html>"+lber.get(redisplay_seq).getCanswer()+"</html>");
								//如果选项A是正确答案就加红
								if(lber.get(redisplay_seq).getAanswer().equals(lber.get(redisplay_seq).getRightanswer()))
								{
									rdbtna.setText("<html><br>"+lber.get(redisplay_seq).getAanswer()+"</br><br>（正确答案）</br></html>");
									rdbtna.setForeground(Color.RED);
								}
								//如果选项B是正确答案就加红
								if(lber.get(redisplay_seq).getBanswer().equals(lber.get(redisplay_seq).getRightanswer()))
								{
									rdbtnb.setText("<html><br>"+lber.get(redisplay_seq).getBanswer()+"</br><br>（正确答案）</br></html>");
									rdbtnb.setForeground(Color.RED);
								}
								//如果选项C是正确答案就加红
								if(lber.get(redisplay_seq).getCanswer().equals(lber.get(redisplay_seq).getRightanswer()))
								{
									rdbtnc.setText("<html><br>"+lber.get(redisplay_seq).getCanswer()+"</br><br>（正确答案）</br></html>");
									rdbtnc.setForeground(Color.RED);
								}
								
							}
							/*
							 * 判断题
							 */
							else if(lber.get(redisplay_seq).getType().equals("判断题"))
							{
								rdbtna.setForeground(Color.BLACK);
								rdbtnb.setForeground(Color.BLACK);
								rdbtnc.setVisible(false);
								lblNewLabel_econtent.setText("<html>"+lber.get(redisplay_seq).getContent()+"</html>");
								label_etype.setText(lber.get(redisplay_seq).getType());
								if(!lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答错误]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.green);
								}
								else if(lber.get(redisplay_seq).getRightanswer().equals(lber.get(redisplay_seq).getYouranswer()))
								{
									label_yours.setText("<html><br>[回答正确]</br><br>您的答案是:  "+lber.get(redisplay_seq).getYouranswer()+"</br></html>");
									label_yours.setForeground(Color.RED);
								}
								rdbtna.setText("正确");
								rdbtnb.setText("错误");
								//如果正确，a就加红
								if(lber.get(redisplay_seq).getRightanswer().equals("正确"))
									rdbtna.setForeground(Color.RED);
								//如果错误，b就加红
								if(lber.get(redisplay_seq).getRightanswer().equals("错误"))
									rdbtnb.setForeground(Color.RED);
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
	}
}