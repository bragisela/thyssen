/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gusta
 */
@Controller
public class FileUploadController {
    

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public @ResponseBody String updaloadFile(@RequestParam("file") MultipartFile file) {
            
        String folderName;
        String pathName = "";
        String result = null;
        try {

            Properties properties= new Properties();
            properties.load(new FileInputStream(new File("local.properties")));
            String prefixPath = properties.get("url.base.upload.file").toString();
        
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());

            folderName = prefixPath + "/"+date;            
            Path path = Paths.get(folderName);

            if (!Files.exists(path)) {
                Files.createDirectory(path);
                System.out.println("New Directory created !   "+folderName);
             } else {
                System.out.println("Directory already exists");
            }
                        
            System.out.println("*** upload file ***");
            System.out.println("*** file:"+file.getOriginalFilename());
            if (!file.getOriginalFilename().isEmpty()) {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(folderName, file.getOriginalFilename())));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
                pathName = folderName + "/"+file.getOriginalFilename();
                result = "Successful|";
            } else {
                result = "Error|";
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "Error|";
                    
        }
                         
        return result+pathName;
    }
    
}
