package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageUploadTracking;
import com.example.projectTestDemo.repository.ManageUploadTrackingRepository;
import com.example.projectTestDemo.service.ManageZipService;
import com.example.projectTestDemo.tools.UtilityTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ManageZipImplement implements ManageZipService {
    private static final Logger logger = Logger.getLogger(ManageZipImplement.class);

    @Autowired
    ManageUploadTrackingRepository manageUploadTrackingRepository;

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

        ManageUploadTracking manageUploadTracking = new ManageUploadTracking();
        try {
            if(compareSize == 0 ||compareSize > configSizeForMac ){
                return new Response(false, "ขนาดไฟล์เอกสาร XML หรือ ZIP ที่อัปโหลดต้องไม่เกิน "+new DecimalFormat("##.#######").format(configSize)+" MB",
                        "500");
            }else {
                if (("application/octet-stream").equals(file.getContentType()) || ("application/x-zip-compressed").equals(file.getContentType()) ||
                        ("multipart/x-zip").equals(file.getContentType()) || ("application/zip-compressed").equals(file.getContentType()) ||
                        ("application/zip").equals(file.getContentType())) {
                        insertFileToDir(file.getOriginalFilename(),file.getBytes());
                }
                manageUploadTracking.setManageId(00);
                manageUploadTracking.setFileName(file.getOriginalFilename());
                manageUploadTracking.setType(file.getContentType());
                manageUploadTracking.setCpid(00);
                manageUploadTracking.setSendDate(new UtilityTools().getFormatsDateMilli());
                manageUploadTracking.setStatus(99);
                manageUploadTracking.setCreateDateTime(new UtilityTools().getFormatsDateMilli());
                this.manageUploadTrackingRepository.save(manageUploadTracking);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Response(false, e.getMessage(), "500");
        }
        logger.info("============================= Done Upload ZIP ==============================");
        return new Response(true, "upload done", "200");
    }

    @Override
    public void unzipTest() {
        this.unzip();
    }

    public void insertFileToDir(String fileName,byte[] file) throws IOException {
        String rootDir = "/Users/boonyaris/Desktop/zipproject";

        Path path = Paths.get(rootDir);
        // check have directory? or have File?
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        //create output path directory
        File outputPath = new File(rootDir + File.separator + fileName);

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

    public void unzip(){
        try {
            String fileZip = "/Users/boonyaris/Desktop/zipproject/etaxinvoiceschema.zip";
            File destinationDirectory = new File("/Users/boonyaris/Desktop/zipproject/unzip");
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                File newFile = newFile(destinationDirectory, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }
                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static File newFile(File destinationDirectory, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDirectory, zipEntry.getName());

        String destDirPath = destinationDirectory.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
