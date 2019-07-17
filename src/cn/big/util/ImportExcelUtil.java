package cn.big.util;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import cn.big.view.FrmFileChoose;
import cn.big.view.FrmFileChooseExcel;
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
/** 
 * excel���ݵ��뵽oracle 
 * @author sh 
 * 2010-05-11 
 */  
public class ImportExcelUtil {  
    public static void main(String[] args) throws Exception {  
    	ImportExcelUtil in = new ImportExcelUtil();  
        in.insert(FrmFileChooseExcel.text,"BeanQuestion");  
  
    }  
  
    /** 
     *  
     * @param path 
     *            Ҫ������excel�ļ�·�� 
      * @param dataTable 
     *            Ҫд�뵽���ݿ��еı���           
     * @throws BiffException 
     * @throws IOException 
     * @throws SQLException 
     */  
    public void insert(String path,String dataTable) throws BiffException, IOException, SQLException 
    {  
        File file = new File(path);           
        // �����µ�Excel ������  
        Workbook rwb = null;  
        rwb = Workbook.getWorkbook(file);  
          
        // �õ��������еĵ�һ����������Ϊexcel�µ�sheet1,sheet2,sheet3...  
        Sheet sheet = rwb.getSheets()[0];  
        int rsColumns = sheet.getColumns();// ����  
        int rsRows = sheet.getRows();// ����  
        String simNumber = "" ;//ÿ����Ԫ���е�����  
          
        //DBUtils jdbc=new DBUtils(); 
        Connection conn = null;
        conn = DBUtil.getConnection();
        java.sql.PreparedStatement pst = null;
        String str="";//ƴ��Ҫ�������  
            for (int j = 0; j <rsColumns; j++) 
            {  
                Cell cell = sheet.getCell(j, 0);  
                simNumber = cell.getContents();  
                if(j==rsColumns-1)
                {  
                    str +=  simNumber  ;  
                }else
                {  
                    str +=  simNumber+",";  
                }  
                  
            }  
        for (int i = 1; i < rsRows; i++) 
        {  
            String sql = "insert into "+dataTable+"("+str+") values(";//ƴ��sql  
//            System.out.println(str);  
            for (int j = 0; j < rsColumns; j++) 
            {  
                Cell cell = sheet.getCell(j, i);  
                simNumber = cell.getContents();  
                if(j==rsColumns-1)
                {  
                    sql += "'"+ simNumber+"'" ;  
                }else
                {  
                    sql +="'"+ simNumber+"',";  
                }  
                  
            }  
            sql += " )"; 
            pst = conn.prepareStatement(sql);
            pst.executeUpdate(sql);//ִ��sql 
              
        }  
        pst.close();
        conn.close();
    }  
  
}  