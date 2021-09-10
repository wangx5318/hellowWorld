package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    /**
     * multipartFile 转 file
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public static File multipartFileToFile (MultipartFile multipartFile) throws Exception{
        if(multipartFile == null || multipartFile.equals("") || multipartFile.getSize() < 1){
            return null;
        }else{
            InputStream is = multipartFile.getInputStream();
            File file = new File(multipartFile.getOriginalFilename());
            inputStreamToFile(is, file);
            is.close();
            return file;
        }
    }

    public static void inputStreamToFile(InputStream is, File file) throws Exception{
        OutputStream os = new FileOutputStream(file);
        int byteRead = 0;
        byte [] buffer = new byte[8129];
        while ((byteRead = is.read(buffer, 0, 8129)) != -1){
            os.write(buffer, 0, byteRead);
        }
        os.close();
        is.close();

    }
}
