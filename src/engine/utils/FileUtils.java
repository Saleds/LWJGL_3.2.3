package engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    public static String loadAsString(String path)
    {
        StringBuilder result = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getModule().getResourceAsStream(path))))
        {
            String line = "";
            while((line = reader.readLine()) != null)
            {
                result.append(line).append("\n");
            }
        }   catch(IOException ex)
        {
            System.err.println("Engine | FileUtils | Error | Couldn't find file at "+path);
            ex.printStackTrace();
        }
        return result.toString();
    }
}
