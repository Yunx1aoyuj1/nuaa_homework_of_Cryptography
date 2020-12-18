package cn.encrypt.utils;

/**
 * BufferedInputStream:缓冲输入流
 * FileInputStream:文件输入流
 **/

import java.io.File;
import java.io.FileWriter;
        import java.io.IOException;

public class Midway{
    public static void main( String[] args ){
        try{
            String data = " This content will append to the end of the file";

            File file =new File("javaio-appendfile.txt");

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            fileWritter.write(data);
            fileWritter.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Write2File(String filename, String data){
        try{
            File file =new File(filename+".txt");

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            fileWritter.write(data);
            fileWritter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

