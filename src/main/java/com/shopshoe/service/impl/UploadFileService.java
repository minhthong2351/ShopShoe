package com.shopshoe.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {
    private String folder="src\\main\\resources\\static\\img\\";
    private String folderadm="src\\main\\resources\\static\\admin\\img\\";
    public String saveImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte [] bytes=file.getBytes();
            Path path = Paths.get(folder+file.getOriginalFilename());
            Path pathadm = Paths.get(folderadm+file.getOriginalFilename());
            Files.write(path, bytes);
            Files.write(pathadm, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public void deleteImage(String nombre) {
        String ruta="img//";
        File file= new File(ruta+nombre);
        file.delete();
    }
}
