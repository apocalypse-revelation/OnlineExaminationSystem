package cn.big.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.big.util.ImportExcelUtil;
import jxl.read.biff.BiffException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class FrmFileChooseExcel extends JFrame{

public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFileChooseExcel frame = new FrmFileChooseExcel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String text;
    public FrmFileChooseExcel() {
        JFrame jf = new JFrame("导入题库(仅支持xls格式)");
        jf.setSize(400, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 创建文本区域, 用于显示相关信息
        final JTextArea msgTextArea = new JTextArea(10, 30);
        msgTextArea.setLineWrap(true);
        panel.add(msgTextArea);

        JButton openBtn = new JButton("选择路径");
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileOpenDialog(jf, msgTextArea);
            }
        });
        panel.add(openBtn);
        
        jf.setContentPane(panel);
        jf.setVisible(true);
        JButton saveBtn = new JButton("确认");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(text==null||text.equals(""))
            	{
            		JOptionPane.showMessageDialog(null, "没有选择路径不能导入！", "提示",JOptionPane.ERROR_MESSAGE);
					return;
            	}
            	ImportExcelUtil ieu = new ImportExcelUtil();
        		try {
					ieu.insert(text, "BeanQuestion");
					JOptionPane.showMessageDialog(null, "导入题库成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
					panel.setVisible(false);
				} catch (BiffException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        panel.add(saveBtn);
    }

    /*
     * 打开文件
     */
    private static void showFileOpenDialog(Component parent, JTextArea msgTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("xls(*.xls)", "xls"));

        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            text = file.getAbsolutePath();
            msgTextArea.append("选取的路径为--->" + text);
            
        }
    }
}