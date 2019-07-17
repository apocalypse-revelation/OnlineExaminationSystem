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
 * excel数据导入到oracle 
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
     *            要解析的excel文件路径 
      * @param dataTable 
     *            要写入到数据库中的表名           
     * @throws BiffException 
     * @throws IOException 
     * @throws SQLException 
     */  
    public void insert(String path,String dataTable) throws BiffException, IOException, SQLException 
    {  
        File file = new File(path);           
        // 创建新的Excel 工作簿  
        Workbook rwb = null;  
        rwb = Workbook.getWorkbook(file);  
          
        // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...  
        Sheet sheet = rwb.getSheets()[0];  
        int rsColumns = sheet.getColumns();// 列数  
        int rsRows = sheet.getRows();// 行数  
        String simNumber = "" ;//每个单元格中的数据  
          
        //DBUtils jdbc=new DBUtils(); 
        Connection conn = null;
        conn = DBUtil.getConnection();
        java.sql.PreparedStatement pst = null;
        String str="";//拼接要插入的列  
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
            String sql = "insert into "+dataTable+"("+str+") values(";//拼接sql  
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
            pst.executeUpdate(sql);//执行sql 
              
        }  
        pst.close();
        conn.close();
    }  
  
}  