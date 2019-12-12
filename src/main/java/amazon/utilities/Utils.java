package amazon.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class Utils{


    private static Map<String,String> parameters =new HashMap<String, String>();
    private static Map<String,Map> excel_data =new HashMap<String, Map>();
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static Logger utilsLogger = LogManager.getLogger(Utils.class);


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
            debugLog(utilsLogger,"Read "+filename+" successfully");
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
            debugLog(utilsLogger,"Extracted Android version via bash command");
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
     * Read excel file into HashMap
     * @param filename excel file to be extracted
     */
    public static Map<String,Map> readExcelFileToMap(String filename){
        try {
            String key;
            Map<String,String> val;
            FileInputStream fileInput;

            fileInput = new FileInputStream(new File(filename));
            wb = new XSSFWorkbook(fileInput);
            int row_count = getRowCount(0);

            for (int i = 1; i < row_count; i++) {
                key = "";
                val = new HashMap<String, String>();

                for (int j = 0; j < 5; j++) {
                    if (j == 0)
                        key = getData(0, i, j);
                    else
                        val.put(getData(0, 0, j),getData(0, i, j));
                }
                excel_data.put(key, val);
            }
        }
        catch (IOException io){
            io.printStackTrace();
        }

        return excel_data;
    }

    /**
     * Will read the specified parameter from the local data structure which
     * is loaded by readExcelFileToMap function
     *
     * @param param - parameter name to read from the map
     * @param field - field name to read from the map
     * @return  returns the value of the parameter
     */
    public static String getValue(String param, String field)
    {
        if(excel_data.containsKey(param))
            return excel_data.get(param).get(field).toString();
        else
            return "";
    }



    public static String getData(int sheetIndex, int row, int column){
        sheet = wb.getSheetAt(sheetIndex);
        Cell c = sheet.getRow(row).getCell(column);
        c.setCellType(CellType.STRING);
        return c.getStringCellValue();
    }


    public static int getRowCount(int sheetIndex){
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();

        row = row +1;

        return row;
    }

    public static void debugLog(Logger log, String message){
        log.debug(message);
    }






}
