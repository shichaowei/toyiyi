package com.wsc.qa.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
/**   
 *【待整理】<br>
* 功能描述： excel文件读取
* 创建人：冯建峰  
* 创建时间：2016年2月3日 下午3:20:09  
* 修改备注：  新增<br>
* Excel放在Data文件夹下<br>
* Excel命名方式：测试类名.xls<br>
* Excel的sheet命名方式：测试方法名<br>
* Excel第一行为Map键值<br>
* 代码参考郑鸿志的Blog
* @see <a href="http://www.zhenghongzhi.cn">www.zhenghongzhi.cn</a>
*/
@SuppressWarnings("all")
public class ExcelDataProvider implements Iterator<Map> {

    private Workbook book         = null;
    private Sheet    sheet        = null;
    private int      rowNum       = 0;
	private int      currentRowNo = 0;
    private int      columnNum    = 0;
    private String[] columnnName;
    
    public int getRowNum() {
		return rowNum;
	}


	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	

    @SuppressWarnings("all")
    public ExcelDataProvider(String xlsname, String sheetname) {
    	
        try {
            int dotNum = xlsname.indexOf(".");
            if (dotNum > 0) {
                xlsname = xlsname.substring(xlsname.lastIndexOf(".") + 1,
                        xlsname.length());
            }
            
            String path = xlsname + ".xls";
            WorkbookSettings setting = new WorkbookSettings();
            setting.setSuppressWarnings(true);
            book=Workbook.getWorkbook(ResourceUtils.getFile("classpath:student.xls"), setting);
            //InputStream inputStream = new FileInputStream(path);
            //book = Workbook.getWorkbook(inputStream);
            
            
            
            sheet = book.getSheet(sheetname);
            //sheet = book.getSheet(0);
            rowNum = sheet.getRows();
            Cell[] cell = sheet.getRow(0);
            columnNum = cell.length;
            columnnName = new String[cell.length];

            for (int i = 0; i < cell.length; i++) {
                columnnName[i] = cell[i].getContents().toString();
            }
            this.currentRowNo++;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public boolean hasNext() {

        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {

            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            // sheet下一行内容为空判定结束
            try {
					if ((sheet.getRow(currentRowNo))[0].getContents().equals(""))
					    return false;
					} catch (Exception e) {
					// TODO Auto-generated catch block
						//e.printStackTrace();
						return false;
					}
            return true;
        }
    }
    
	public Map<String, String> next() {

        Cell[] c = sheet.getRow(this.currentRowNo);
        Map<String, String> data = new HashMap<String, String>();
        // List<String> list = new ArrayList<String>();

        for (int i = 0; i < this.columnNum; i++) {

            String temp = "";

            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }

            // if(temp != null&& !temp.equals(""))
            // list.add(temp);
            data.put(this.columnnName[i], temp);
        }
        Object object[] = new Object[1];
        object[0] = data;
        this.currentRowNo++;
        return data;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}