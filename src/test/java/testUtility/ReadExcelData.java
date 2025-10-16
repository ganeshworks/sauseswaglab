package testUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class ReadExcelData {

	private static Workbook workbook;
	private static Sheet sheetname;
	
	public static Object[][] getExcelData(String filename,String sheet) throws Exception{
			FileInputStream fis=new FileInputStream(filename);
			workbook=WorkbookFactory.create(fis);
			sheetname=workbook.getSheet(sheet);
			
			int rowcount=sheetname.getPhysicalNumberOfRows();
			int colcount=sheetname.getRow(0).getPhysicalNumberOfCells();
			
			
			Object data[][]=new Object[rowcount-1][colcount];
			
			for(int i=1;i<rowcount;i++) {
				for (int j = 0; j < colcount; j++) {
			        Cell cell = sheetname.getRow(i).getCell(j);
			        DataFormatter formatter = new DataFormatter();
			        data[i-1][j] = formatter.formatCellValue(cell); // ✅ keeps "1213231" as is
			    }
			}
			workbook.close();
			fis.close();
			return data;
	
	}
		
	
}
