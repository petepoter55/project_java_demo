package com.example.projectTestDemo.service.implement;


import com.example.projectTestDemo.dtoResponse.ValidateXmlResponse;
import com.example.projectTestDemo.entity.ManageConfigXpath;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.ManageConfigXpathRepository;
import com.example.projectTestDemo.repository.ManageDocumentTypeRepository;
import com.example.projectTestDemo.service.ManageFileXmlService;
import com.example.projectTestDemo.tools.RetrieveXPath;
import com.example.projectTestDemo.tools.UtilityTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ManageFileXmLlmplement implements ManageFileXmlService {
    private static final Logger logger = Logger.getLogger(ManageFileXmLlmplement.class);
    @Autowired
    ManageConfigXpathRepository manageConfigXpathRepository;
    @Autowired
    ManageDocumentTypeRepository manageDocumentTypeRepository;

    @Override
    public String changeFormatXml(MultipartFile file) {
        String message = "";

        try {
            if (!file.isEmpty() && file != null) {
                ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());

                 message = new BufferedReader(
                 new InputStreamReader(stream, StandardCharsets.UTF_8))
                 .lines()
                 .collect(Collectors.joining("\n"));

            } else {
                throw new ResponseException();
            }
        } catch (Exception e) {
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }

        return message;
    }

    @Override
    public ValidateXmlResponse validateEbXml(String message) throws IOException {
        logger.info("===== Start validateEbXml =======");
        logger.info("xml :" + message);
        InputStream inputStreamXmlSignXpath = null;
        HashMap<String, String> result = new LinkedHashMap<String, String>();

        try {
            boolean wellForm = new UtilityTools().doValidateXml(message);
            if (wellForm) {
                byte[] dataEb = message.getBytes();
                inputStreamXmlSignXpath = new ByteArrayInputStream(dataEb);
                RetrieveXPath retrieveXPathSign = new RetrieveXPath(inputStreamXmlSignXpath);

                List<ManageConfigXpath> manageConfigXpathList = this.manageConfigXpathRepository.findAll();
                for (ManageConfigXpath d : manageConfigXpathList) {
                    String dataXpath = retrieveXPathSign.getString(d.getXpath());

                    if (dataXpath != null && !"".equals(dataXpath)) {
                        result.put(d.getDesc() + " : ", dataXpath);
                    } else {
                        result.put(d.getDesc(), "ไม่พบข้อมูล");
                    }
                }
            }
        } catch (Exception e) {
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
            return new ValidateXmlResponse(false, "validate xml fails", "500", result);
        } finally {
            if (inputStreamXmlSignXpath != null) {
                inputStreamXmlSignXpath.close();
            }
        }
        logger.info("===== Done validateEbXml =======");
        return new ValidateXmlResponse(true, "validate xml success", "200", result);
    }

//    public String verifyH2H(String xpath, String companyType, String companyTaxid, String data, String cpid)
//            throws Exception {
//        configBundle = ResourceBundle.getBundle(DEFAULT_CONFIG);
//        String ebversion = configBundle.getString("EB_VERSION");
//        String toparty = configBundle.getString("EB_TO_PARTY");
//        String torole = configBundle.getString("EB_TO_ROLE");
//        String ebservice = configBundle.getString("EB_SERVICE");
//        String error =null;
//
//        try {
//            if (("eb: Version").equalsIgnoreCase(xpath)) {
//                if (!data.equals(ebversion)) {
//                    error = "กรุณาตรวจสอบ Vesion EbXml";
//                }
//            }
//
//            if (("eb: From Party").equalsIgnoreCase(xpath)) {
//                if (!companyTaxid.equals(data)) {
//                    error = "กรุณาตรวจสอบข้อมูล เลขประจำตัวผู้เสียภาษีอากร";
//                }
//            }
//
//            if (("eb: From Role").equalsIgnoreCase(xpath)) {
//                if (!("HOST_TO_HOST").equals(data)) {
//                    error = "กรุณาตรวจสอบช่องทางที่ผู้ใช้งานเสียภาษี";
//                }
//            }
//
//            if (("eb: To Party").equalsIgnoreCase(xpath)) {
//                if (!data.equals(toparty)) {
//                    error = "กรุณาตรวจสอบ ข้อมูลหน่วยงานที่จะนำส่งข้อมูล";
//                }
//            }
//
//            if (("eb: To Role").equalsIgnoreCase(xpath)) {
//                if (!data.equals(torole)) {
//                    error = "กรุณาตรวจสอบ ข้อมูลหน่วยงานที่จะนำส่งข้อมูล";
//                }
//            }
//
//            if (("eb: CPAId").equalsIgnoreCase(xpath)) {
//                String cpaId = null;
//
//                if (!data.equals(cpaId) || cpaId == null) {
//                    error = "กรุณาตรวจสอบ CPA Id ให้ถูกต้อง";
//                }
//            }
//
//            if (("eb: Conversation ID").equalsIgnoreCase(xpath)) {
//                profiletraderTest = traderdao.getByTax(companyTaxid, "TT");
//                if (profiletraderTest == null || !(data.substring(0, 18).equals(profiletraderTest.getProfile())) || data.length() != 29) {
//                    error = "กรุณาตรวจสอบ เลขที่เอกสารนำส่ง";
//                }
//
//            }
//
//            if (("eb: Service").equalsIgnoreCase(xpath)) {
//                if (!("THRD.eTaxInvoice").equals(data)) {
//                    error = "กรุณาตรวจสอบ Service ที่ใช้ในการรับส่งข้อมูล";
//                }
//            }
//
//
//            if (("eb: Action").equalsIgnoreCase(xpath)) {
//                List<ManageDocumentType> datas = typeDao.findByMessageTypeCode(data);
//                if (datas == null || datas.size() == 0) {
//                    error = "กรุณาตรวจสอบ ประเภทเอกสาร";
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return error;
//    }

}
