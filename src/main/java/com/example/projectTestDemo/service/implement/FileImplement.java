package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoResponse.ImportExcelManageUserResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.service.FileService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileImplement implements FileService {
    private static final Logger logger = Logger.getLogger(FileImplement.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public ImportExcelManageUserResponse importExcel(MultipartFile file) throws IOException {
        ImportExcelManageUserResponse manageUserList;
        if(("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet").equals(file.getContentType())){
            manageUserList = this.importDataExcel(file);
        }else {
            return new ImportExcelManageUserResponse(false, "import fail Because file not Excel", "500",null);

        }
        return manageUserList;
    }

    @Override
    public void exportExcel(ExportExcelRequest exportExcelRequest, HttpServletResponse response) {
        try {
            this.exportSearchUserByApproved(response, exportExcelRequest);
        } catch (Exception e) {
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
    }

    @Override
    public String moveFile(MultipartFile file) throws IOException {
            String result = null;
            //create output path directory
            File outputPath = new File("/Users/boonyaris/Desktop/" + file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(outputPath);

            try {
                fos.write(file.getBytes());
            }catch (IOException ex){
                logger.error(String.format(Constant.THROW_EXCEPTION,ex.getMessage()));
            }finally {
                if (fos != null) {
                    fos.close();
                }
            }
        result = Constant.SUCCESS;
        return result;
    }

    public void exportSearchUserByApproved(HttpServletResponse response, ExportExcelRequest dto) throws IOException {
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "keyword_master" + ".xlsx");
        OutputStream outStream = null;

        try {
            // fix column name
            String[] columns = {
                    "UID",
                    "FirstName",
                    "LastName"
            };

            Workbook workbook = new XSSFWorkbook();

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("master");

            // set style Header
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            // create header cell
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            List<ManageUser> list = this.userRepository.findByApproved(dto.getApproved());

            // initialize data in row
            for (ManageUser d : list) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(d.getUid().toString());
                row.createCell(1).setCellValue(d.getFirstName());
                row.createCell(2).setCellValue(d.getLastName());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // output response file name
            outStream = response.getOutputStream();
            workbook.write(outStream);
            outStream.flush();
            workbook.close();

        } catch (Exception e) {
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        } finally {
            if (outStream != null) {
                outStream.close();
            }
        }
    }

    public ImportExcelManageUserResponse importDataExcel(MultipartFile files) throws IOException {
        List<ManageUser> manageUserList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        // get sheet in Excel By index
        XSSFSheet worksheet = workbook.getSheetAt(0);
        try {
            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    ManageUser manageUser = new ManageUser();

                    XSSFRow row = worksheet.getRow(index);

                    manageUser.setUid(new BigInteger(row.getCell(0).getStringCellValue()));
                    manageUser.setFirstName(row.getCell(1).getStringCellValue());
                    manageUser.setLastName(row.getCell(2).getStringCellValue());
                    manageUserList.add(manageUser);
                }
            }
        } catch (Exception e) {
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        } finally {
            workbook.close();
        }
        return new ImportExcelManageUserResponse(true, "import success", "200",manageUserList);
    }
}
