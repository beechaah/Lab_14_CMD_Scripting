import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File file;
        String rec = "";
        int line = 0;
        int charCnt = 0;
        int wordCnt = 0;
        String[] words;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            String absoluteFilePath = workingDirectory + File.separator + "src";
            file = new File(absoluteFilePath);
            chooser.setCurrentDirectory(file);

            if (args.length > 0)
            {
                absoluteFilePath += File.separator + args[0];
                file = new File(absoluteFilePath);
                System.out.println("File path: " + file.getAbsolutePath());
            }
            else
            {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    file = chooser.getSelectedFile();
                }
                else
                {
                    System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
                    return;
                }
            }

            Path path = file.toPath();
            InputStream in = new BufferedInputStream(Files.newInputStream(path, CREATE));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while (reader.ready())
            {
                rec = reader.readLine();
                line++;
                charCnt += rec.length();
                words = rec.split(" ");
                wordCnt += words.length;

                // echo to screen
                System.out.printf("\nLine %4d %-60s ", line, rec);
            }
            reader.close();
            System.out.println("\n\nData file read!");

            System.out.println("The total number of lines is " + line);
            System.out.println("The total number of words is " + wordCnt);
            System.out.println("The total number of characters is " + charCnt);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}