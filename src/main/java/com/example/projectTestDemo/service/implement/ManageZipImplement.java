package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.service.ManageZipService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

@Service
public class ManageZipImplement implements ManageZipService {
    private static final Logger logger = Logger.getLogger(ManageZipImplement.class);

    @Override
    public Response uploadZipFile(MultipartFile file) {
        logger.info("============================= Start Upload ZIP ==============================");
        logger.info("File Name : " + file.getOriginalFilename());
        logger.info("File Size : " + file.getSize());
        logger.info("File Content Type : " + file.getContentType());
        double configSize = Double.valueOf(3);
        double configSizeForMac =  Double.valueOf(configSize + 0.3);
        double bytes = file.getSize();
        double kilobytes = (bytes / 1024);
        double megabytes = (kilobytes / 1024);
        double compareSize = Double.valueOf(new DecimalFormat("##.#######").format(megabytes));

        try {
            if(compareSize == 0 ||compareSize > configSizeForMac ){
                return new Response(false, "ขนาดไฟล์เอกสาร XML หรือ ZIP ที่อัปโหลดต้องไม่เกิน "+new DecimalFormat("##.#######").format(configSize)+" MB",
                        "500");
            }else {
                if (("application/octet-stream").equals(file.getContentType()) || ("application/x-zip-compressed").equals(file.getContentType()) ||
                        ("multipart/x-zip").equals(file.getContentType()) || ("application/zip-compressed").equals(file.getContentType()) ||
                        ("application/zip").equals(file.getContentType())) {
                        insertFileToDir(file.getName(),file.getBytes());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Response(false, e.getMessage(), "500");
        }
        logger.info("============================= Done Upload ZIP ==============================");
        return new Response(true, "upload done", "200");
    }

    public void insertFileToDir(String fileName,byte[] file) throws IOException {
        String rootDir = "/Users/boonyaris/Desktop/zipproject";
        String nameFile = fileName + ".zip";
        Path path = Paths.get(rootDir);
        // check have directory? or have File?
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        //create output path directory
        File outputPath = new File(rootDir + File.separator + nameFile);

        logger.info("output path : " + outputPath);
        FileOutputStream fos = new FileOutputStream(outputPath);

        try {
            fos.write(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
