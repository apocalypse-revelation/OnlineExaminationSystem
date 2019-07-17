package cn.big.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.big.controller.DAOtable_student;
import cn.big.util.FuncUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_account;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(75, 78, 75, 25);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(96, 136, 54, 25);
		contentPane.add(label_1);
		
		textField_account = new JTextField();
		textField_account.setBounds(159, 80, 154, 21);
		contentPane.add(textField_account);
		textField_account.setColumns(10);
		
		JButton button_reg = new JButton("\u6CE8\u518C");
		button_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmReg fr = new FrmReg();
				fr.setVisible(true);
			}
		});
		button_reg.setBounds(47, 185, 93, 23);
		contentPane.add(button_reg);
		
		JButton button_login = new JButton("\u767B\u5F55");
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_account.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "id账号不能为空", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(new String(passwordField.getPassword()).equals(""))
				{
					JOptionPane.showMessageDialog(null, "密码不能为空", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String sidcard = textField_account.getText();
				String spwd = new String(passwordField.getPassword());
				try {
					DAOtable_student.curStudent = FuncUtil.studentController.login(sidcard, spwd);
					if(DAOtable_student.curStudent == null)
						return;
					FrmMain fm = new FrmMain();
					fm.setVisible(true);
					//删除以前的试卷，防止上一次答题不正常退出导致试卷未清空
					FuncUtil.examController.delExamResultBySid(DAOtable_student.curStudent.getSid());
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_login.setBounds(165, 185, 93, 23);
		contentPane.add(button_login);
		
		JButton button_back = new JButton("\u9000\u51FA");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_back.setBounds(281, 185, 93, 23);
		contentPane.add(button_back);
		
		JLabel label_2 = new JLabel("\u673A\u52A8\u8F66\u8003\u8BD5\u7CFB\u7EDF");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		label_2.setBounds(159, 21, 154, 25);
		contentPane.add(label_2);
		
		JButton button = new JButton("\u7BA1\u7406\u5458\u901A\u9053");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			FrmAdminLogin fal = new FrmAdminLogin();
			fal.setVisible(true);
			}
		});
		button.setBounds(0, 0, 114, 23);
		contentPane.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 138, 154, 21);
		contentPane.add(passwordField);
	}
}
