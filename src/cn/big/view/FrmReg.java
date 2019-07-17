package cn.big.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.big.util.FuncUtil;
import cn.big.util.VerifyIdValidUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class FrmReg extends JFrame {

	private JPanel contentPane;
	private JTextField textField_sname;
	private JTextField textField_sidcard;
	private JTextField textField_stelnum;
	public static JLabel label_sphoto;
	private JPasswordField passwordField_spwd1;
	private JPasswordField passwordField_spwd2;
	//正则表达式判断String是否都是数字
	public boolean isNumeric(String str){
		   Pattern pattern = Pattern.compile("[0-9]*");
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false;
		   }
		   return true;
		}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReg frame = new FrmReg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmReg() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8003\u751F\u59D3\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 15));
		label.setBounds(11, 101, 80, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8003\u751F\u8EAB\u4EFD\u8BC1\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 15));
		label_1.setBounds(11, 56, 103, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8003\u751F\u6CE8\u518C");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		label_2.setBounds(182, 21, 94, 24);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u8003\u751F\u7535\u8BDD\uFF1A");
		label_3.setFont(new Font("宋体", Font.BOLD, 15));
		label_3.setBounds(11, 233, 94, 18);
		contentPane.add(label_3);
		
		textField_sname = new JTextField();
		textField_sname.setColumns(10);
		textField_sname.setBounds(126, 100, 150, 21);
		contentPane.add(textField_sname);
		
		textField_sidcard = new JTextField();
		textField_sidcard.setColumns(10);
		textField_sidcard.setBounds(126, 55, 150, 21);
		contentPane.add(textField_sidcard);
		
		textField_stelnum = new JTextField();
		textField_stelnum.setColumns(10);
		textField_stelnum.setBounds(126, 232, 150, 21);
		contentPane.add(textField_stelnum);
		
		JButton button_confirm = new JButton("\u786E\u5B9A");
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int sid = 0;
//				//final int ID_LENGTH_LIMITED = 6;
//				if(isNumeric(textField_sid.getText())&&!textField_sid.getText().equals(""))
//					sid = Integer.parseInt(textField_sid.getText());
//				else 
//				{
//					JOptionPane.showMessageDialog(null, "id不能含有字符或为空", "提示",JOptionPane.ERROR_MESSAGE);
//					return;
//				}
				String sname = textField_sname.getText();
				String sidcard = textField_sidcard.getText();
				String stelnum = textField_stelnum.getText();
				String spwd1 = new String(passwordField_spwd1.getPassword());
				String spwd2 = new String(passwordField_spwd2.getPassword());
				String sphoto = FrmFileChoose.text;
				
				if("".equals(stelnum)||"".equals(sidcard)||"".equals(sname))
				{
					JOptionPane.showMessageDialog(null, "个人信息不能为空！", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(spwd1==null || "".equals(spwd1))
				{
					JOptionPane.showMessageDialog(null, "密码不能为空", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!spwd1.equals(spwd2)) 
				{
					JOptionPane.showMessageDialog(null, "两次输入的密码要一致", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
//				if(sid == 0)
//				{
//					JOptionPane.showMessageDialog(null, "考生id不能为0", "提示",JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				if(textField_sid.getText().equals(""))
//				{
//					JOptionPane.showMessageDialog(null, "考生id不能为空", "提示",JOptionPane.ERROR_MESSAGE);
//					return;
//				}
				
				if(stelnum.length()!=11)
				{
					JOptionPane.showMessageDialog(null, "电话号码为11位", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!VerifyIdValidUtil.validate(sidcard))
				{
					JOptionPane.showMessageDialog(null, "身份证号码格式不正确", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					FuncUtil.studentController.reg(sname, sidcard, stelnum, spwd1, spwd2,sphoto);
					JOptionPane.showMessageDialog(null, "注册成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_confirm.setBounds(82, 276, 93, 23);
		contentPane.add(button_confirm);
		
		JButton button_back = new JButton("\u9000\u51FA");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_back.setBounds(239, 276, 93, 23);
		contentPane.add(button_back);
		
		JLabel label_4 = new JLabel("\u8D26\u53F7\u5BC6\u7801\uFF1A");
		label_4.setFont(new Font("宋体", Font.BOLD, 15));
		label_4.setBounds(10, 143, 103, 18);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_5.setFont(new Font("宋体", Font.BOLD, 15));
		label_5.setBounds(11, 186, 103, 18);
		contentPane.add(label_5);
		
		label_sphoto = new JLabel("");
		label_sphoto.setFont(new Font("新宋体", Font.BOLD, 99));
		label_sphoto.setHorizontalAlignment(SwingConstants.CENTER);
		label_sphoto.setToolTipText("\u8BF7\u4E0A\u4F20125*160\u5C3A\u5BF8\u4E2A\u4EBA\u5934\u50CF");
		label_sphoto.setBounds(299, 61, 125, 160);
		contentPane.add(label_sphoto);
		
		JButton button_upload = new JButton("\u4E0A\u4F20\u5934\u50CF");
		button_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFileChoose ffc = new FrmFileChoose();
				ffc.setVisible(true);
			}
		});
		button_upload.setToolTipText("\u8BF7\u4E0A\u4F20125*160\u5C3A\u5BF8\u4E2A\u4EBA\u5934\u50CF");
		button_upload.setBounds(319, 232, 93, 23);
		contentPane.add(button_upload);
		
		passwordField_spwd1 = new JPasswordField();
		passwordField_spwd1.setBounds(126, 142, 150, 21);
		contentPane.add(passwordField_spwd1);
		
		passwordField_spwd2 = new JPasswordField();
		passwordField_spwd2.setBounds(126, 185, 150, 21);
		contentPane.add(passwordField_spwd2);
	}
}
