package wrapper;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider{
	
	
	
	public static Object[][] getValue(String sExcelName) throws Exception {
		
		Object[][] data = null ;

			String text = "./data/"+sExcelName+".xlsx";
			FileInputStream fis = new FileInputStream(text);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);	

			// get the number of rows 3
			int rowCount = sheet.getLastRowNum();

			// get the number of columns 2
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];

			// loop through the rows
			for(int i=1; i <rowCount+1; i++){
				
					XSSFRow row = sheet.getRow(i);
					for(int j=0; j <columnCount; j++){ // loop through the columns
						
							String cellValue = "";
							
								cellValue = row.getCell(j).getStringCellValue();
					
							data[i-1][j]  = cellValue; // add to the data array data[0][0] = Samsung,data[0][] = Cell
										
					}

				 
			}
			fis.close();
			workbook.close();
		return data;

	}
}
