package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {


    private static Map<String,String> parameters =new HashMap<String, String>();
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;


    /**
     * Will read the specified parameter from the local data structure which
     * is loaded by readEntirePropertyFile function
     *
     * @param param - parameter name to read from the file
     * @return  returns the value of the parameter
     */
    public static String getValueForParam(String param)
    {
        if(parameters.containsKey(param.toLowerCase()))
            return parameters.get(param).trim();
        else
            return "";
    }


    /**
     * Will read the entire property file in local data structure
     * which can then be read using readInternalHash function
     *
     * @param filename - name of the file to read
     */
    public static void readEntirePropertyFile(String filename)
    {
        try {
            FileInputStream fileInput = new FileInputStream(new File(filename));
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            Enumeration<?> e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                parameters.put(key.toLowerCase(), value);
            }
            System.out.println("Reading "+filename);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * Run Bash Command
     * @param command command to be run
     */
    public static String runBashCommands(String[] command) {
        String line=null;
        try {
            Process runCommand=Runtime.getRuntime().exec(command);
            runCommand.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(runCommand.getInputStream()));
            line  = reader.readLine();
            System.out.println("Done!");
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * Get Android Version using Adb commands
     */
    public static String findAndroidVersion(){
        String androidVersion=null;
        try {
            String[] command={"bash", "-c","adb shell getprop ro.build.version.release"};
            androidVersion=runBashCommands(command);
            if(androidVersion!=null)
                androidVersion=androidVersion.trim();
            return androidVersion;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return androidVersion;
    }

    /**
     * Read excel file into 2D array
     * @param filename excel file to be extracted
     */
    public static Object[][] readExcelFile(String filename) throws IOException {
            FileInputStream fileInput = new FileInputStream(new File(filename));
            wb = new XSSFWorkbook(fileInput);

            sheet = wb.getSheetAt(0);

            int row_count = sheet.getLastRowNum();
            Object[][] data = new Object[row_count][2];

            for (int i = 0; i < row_count; i++) {
                data[i][0] = sheet.getRow(i).getCell(0).getStringCellValue();
                data[0][i] = sheet.getRow(i).getCell(0).getStringCellValue();
            }

        return data;
    }




}
