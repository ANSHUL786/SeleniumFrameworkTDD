package anshFramework.testsComponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readdatafromexcel {

	public Object[][] getDatafromExcel(String path) throws IOException {

		FileInputStream fis = new FileInputStream(path);
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int columnCount = sheet.getRow(0).getLastCellNum();

		// -1 is for exclude header data
		Object[][] data = new Object[rowCount - 1][columnCount];
		int i, j = 0;
		for (i = 0; i < rowCount - 1; i++) {

			XSSFRow row = sheet.getRow(i + 1);
			for (j = 0; j < columnCount; j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;
	}
}
