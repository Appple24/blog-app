package com.blogging.Service.Impl;

import com.blogging.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name= file.getOriginalFilename();
        String randomId= UUID.randomUUID().toString();

        String fileName=randomId.concat(name.substring(name.lastIndexOf(".")));
        String filepath=path+ File.separator+fileName;
        File f=new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filepath));
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException {
        String fullpath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullpath);
        return is;
    }
}
