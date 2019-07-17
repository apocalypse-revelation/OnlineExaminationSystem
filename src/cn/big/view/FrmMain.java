package cn.big.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.big.controller.DAOtable_student;
import cn.big.util.FuncUtil;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrmMain() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel label = new JLabel("\u60A8\u7684\u5206\u6570\u4E3A\uFF1A");
//		label.setFont(new Font("ËÎÌו", Font.BOLD, 30));
//		label.setBounds(14, -1, 195, 68);
//		contentPane.add(label);
		
		JLabel label_score = new JLabel();
		label_score.setFont(new Font("ËÎÌו", Font.BOLD, 30));
		label_score.setBounds(224, -1, 195, 68);
		contentPane.add(label_score);
		//label_score.setText(FuncUtil.studentController.loadScore());
		
		JButton button_startexam = new JButton("\u5F00\u59CB\u8003\u8BD5");
		button_startexam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmExamMain fem;
				try {
					FuncUtil.examController.deletePaper();
					//FrmExamMain.lbep = FuncUtil.examController.get20Qustion();
					fem = new FrmExamMain();
					fem.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		button_startexam.setFont(new Font("ËÎÌו", Font.BOLD, 15));
		button_startexam.setBounds(151, 77, 120, 120);
		contentPane.add(button_startexam);
		
		JButton button_back = new JButton("\u9000\u51FA");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_back.setBounds(165, 222, 93, 23);
		contentPane.add(button_back);
	}

}
