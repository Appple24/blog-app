package com.blogging.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(String path, MultipartFile file)throws IOException;
    InputStream getResource(String path,String fileName)throws IOException;
}
