package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {


    private static Map<String,String> parameters =new HashMap<String, String>();

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

}
