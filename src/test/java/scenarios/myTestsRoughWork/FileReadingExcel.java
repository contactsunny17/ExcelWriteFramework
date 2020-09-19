package scenarios.myTestsRoughWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReadingExcel {

	public static void main(String[] args) throws IOException {
		write();
		read();
	}

	private static void read() throws IOException{
		//Obtaining input bytes from the file
		FileInputStream filePath = new FileInputStream(new File("D:/work/selenium/workspace/framework_artifact_1/src/test/resources/sunny.xlsx"));
		//Creating workbook instanse that refers to .xls file
		HSSFWorkbook wb = new HSSFWorkbook(filePath);

		System.out.println("About to read/write into excel sheet");
	}

	private static void write() {
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		//Create blank sheet
		XSSFSheet sheet = workbook.createSheet("Future Data");

		//Data to be add on the built sheet
		Map<String,Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"SYMBOL", "EXPIR DATE", "RATIO", 999999});
		data.put("3", new Object[] {"ACC", "30/07/2020", "1.93"});
		data.put("4", new Object[] {"RELIANCE", "27/08/2020", "18.80"});
		data.put("5", new Object[] {"TATA MOTARS", "30/07/2020", "20"});
		data.put("2", new Object[] {"Adient", "37/08/2020", "9.52"});

		//Iterate over data and write to the sheet
		Set<String> myKeySet = data.keySet();
		int rownum = 0;
		for(String key : myKeySet) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		//Write the workbook in file system
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("D:/work/selenium/workspace/framework_artifact_1/src/test/resources/sunny.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("written successfully on disk.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}


