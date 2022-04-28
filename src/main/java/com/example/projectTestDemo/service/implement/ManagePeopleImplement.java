package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleDetailResponse;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageMasterDistrict;
import com.example.projectTestDemo.entity.ManagePeopleVat;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.ManageMasterDistrictRepository;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.repository.ManagePeopleVatRepository;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.service.ManagePeopleService;
import com.example.projectTestDemo.tools.UtilityTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagePeopleImplement implements ManagePeopleService {
    private static final Logger logger = Logger.getLogger(ManagePeopleImplement.class);
    @Autowired
    private  ManagePeopleDetailRepository managePeopleDetailRepository;
    @Autowired
    private  ManagePeopleVatRepository managePeopleVatRepository;
    @Autowired
    private  ManageMasterDistrictRepository manageMasterDistrictRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ManagePeopleViewResponse getDate(String mangeRegisterId) {
        logger.info("===== Start GetData ManagePeopleDetail=======");
        logger.info("manageTaxId : " + mangeRegisterId);
        List<ManagePeopleDetailResponse> managePeopleDetailResponseList = new ArrayList<>();
        ManagePeopleDetailResponse managePeopleDetailResponse = new ManagePeopleDetailResponse();
        ManagePeopleViewResponse managePeopleViewResponse = new ManagePeopleViewResponse();

        boolean status = true;
        String message = "";

        try {
            List<MangePeopleDetail> mangePeopleDetailList = this.managePeopleDetailRepository.searchByManagePeopleTaxIdLike(mangeRegisterId);
            if (mangePeopleDetailList.size() > 0) {
                for (MangePeopleDetail data : mangePeopleDetailList) {
                    managePeopleDetailResponse = this.setObjectDetailView(data);
                    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                    logger.info("data => " + gson.toJson(managePeopleDetailResponse));
                    managePeopleDetailResponseList.add(managePeopleDetailResponse);
                }
                message = "ดึงข้อมูลสำเร็จ";
            } else {
                message = "ไม่พบข้อมูล";
                status = false;
            }
            managePeopleViewResponse.setStatus(status);
            managePeopleViewResponse.setMessage(message);
            managePeopleViewResponse.setManagePeopleDetailResponse(managePeopleDetailResponseList);

        } catch (ResponseException | ParseException e) {
            logger.error("error : "+ e.getMessage());
            managePeopleViewResponse.setStatus(false);
            managePeopleViewResponse.setMessage(e.getMessage());
            managePeopleViewResponse.setManagePeopleDetailResponse(null);
        }
        logger.info("status message : " + managePeopleViewResponse.getMessage());
        logger.info("===== Done GetData ManagePeopleDetail=======");
        return managePeopleViewResponse;
    }

    @Override
    public Response deleteDate(int id) {

        try {
            this.managePeopleDetailRepository.deleteById(id);

        } catch (ResponseException e) {
            logger.error("error : "+ e.getMessage());
            return new Response(false, e.getMessage(), "500");
        }
        return new Response(true, "ลบข้อมูลสำเร็จ", "200");
    }

    @Override
    public Response createDataPeople(MangeRegisterRequest mangeRegisterRequest) {
        MangePeopleDetail mangePeopleDetail = new MangePeopleDetail();
        ManagePeopleVat managePeopleVat = this.managePeopleVatRepository.findByManagePeopleTaxId(mangeRegisterRequest.getManageTaxId());

        try {
            if (managePeopleVat != null) {
                boolean checkData = checkRegisterData(managePeopleVat.getManagePeopleTaxId());
                if(checkData){
                    mangePeopleDetail = this.setObjectDetail(managePeopleVat);
                    this.managePeopleDetailRepository.save(mangePeopleDetail);
                }else {
                    return new Response(false, Constant.ERROR_PEOPLE_CHECKDATA_DUPLICATE, Constant.STATUS_CODE_FAIL);
                }
            } else {
                return new Response(false, Constant.ERROR_PEOPLE_CHECKDATA_FOUND, Constant.STATUS_CODE_FOUND);
            }

        } catch (ResponseException | ParseException e) {
            logger.error("error : "+ e.getMessage());
            return new Response(false, e.getMessage(), Constant.STATUS_CODE_FAIL);
        }
        return new Response(true, Constant.SUCCESS_PEOPLE, Constant.STATUS_CODE_SUCCESS);
    }

    @Override
    public Response updateDataPeople(int id) {
        MangePeopleDetail mangePeopleDetail = new MangePeopleDetail();
        try {
            mangePeopleDetail = this.managePeopleDetailRepository.findById(id);

            if (mangePeopleDetail != null) {
                // mangePeopleDetail.setStatusId(1);
                mangePeopleDetail.setUpdateDateTime(new UtilityTools().getFormatsDateMilli());
            }

        } catch (ResponseException | ParseException e) {
            logger.error("error : "+ e.getMessage());
            return new Response(false, Constant.ERROR_UPDATE_PEOPLE, Constant.STATUS_CODE_FAIL);
        }
        this.managePeopleDetailRepository.save(mangePeopleDetail);

        return new Response(true, Constant.SUCCESS_UPDATE_PEOPLE, Constant.STATUS_CODE_SUCCESS);
    }

    @Override
    public List<ManageMasterDistrict> getTest() {
        return this.manageMasterDistrictRepository.findAll();
    }

    public MangePeopleDetail setObjectDetail(ManagePeopleVat managePeopleVat) throws ParseException {
        String refNo = "PE000" + new UtilityTools().randomNumber(2);

        MangePeopleDetail mangePeopleDetail = new MangePeopleDetail();
        mangePeopleDetail.setRefNo(refNo);
        mangePeopleDetail.setManagePeopleTaxId(managePeopleVat.getManagePeopleTaxId());
        mangePeopleDetail.setManagePeopleType(managePeopleVat.getManagePeopleType());
        mangePeopleDetail.setManagePeopleLastName(managePeopleVat.getManagePeopleLastName());
        mangePeopleDetail.setManagePeopleName(managePeopleVat.getManagePeopleName());
        mangePeopleDetail.setAddress(managePeopleVat.getAddress());
        mangePeopleDetail.setBuilding(managePeopleVat.getBuilding());
        mangePeopleDetail.setMoo(managePeopleVat.getMoo());
        mangePeopleDetail.setFloor(managePeopleVat.getFloor());
        mangePeopleDetail.setPostCode(managePeopleVat.getPostCode());
        mangePeopleDetail.setVillage(managePeopleVat.getVillage());
        mangePeopleDetail.setSoi(managePeopleVat.getSoi());
        mangePeopleDetail.setRoad(managePeopleVat.getRoad());
        mangePeopleDetail.setDistrictId(managePeopleVat.getDistrictId());
        mangePeopleDetail.setSubProvinceId(managePeopleVat.getSubProvinceId());
        mangePeopleDetail.setProviceId(managePeopleVat.getProviceId());
        mangePeopleDetail.setPostCode(managePeopleVat.getPostCode());
        mangePeopleDetail.setEmail(managePeopleVat.getEmail());
        mangePeopleDetail.setNewEmail(managePeopleVat.getNewEmail());
        mangePeopleDetail.setTel(managePeopleVat.getTel());
        mangePeopleDetail.setCreateDateTime(new UtilityTools().getFormatsDateMilli());
        return mangePeopleDetail;
    }

    public ManagePeopleDetailResponse setObjectDetailView(MangePeopleDetail mangePeopleDetail) throws ParseException {
        ManagePeopleDetailResponse managePeopleDetailResponse = new ManagePeopleDetailResponse();

        managePeopleDetailResponse.setManageTaxId(mangePeopleDetail.getManagePeopleTaxId());
        managePeopleDetailResponse.setFirstName(mangePeopleDetail.getManagePeopleName());
        managePeopleDetailResponse.setLastName(mangePeopleDetail.getManagePeopleLastName());
        managePeopleDetailResponse.setAddress(mangePeopleDetail.getAddress());
        managePeopleDetailResponse.setEmail(mangePeopleDetail.getEmail());
        managePeopleDetailResponse.setBuilding(mangePeopleDetail.getBuilding());
        managePeopleDetailResponse.setRefNo(mangePeopleDetail.getRefNo());
        managePeopleDetailResponse.setMoo(mangePeopleDetail.getMoo());
        managePeopleDetailResponse.setFloor(mangePeopleDetail.getFloor());
        managePeopleDetailResponse.setPostCode(mangePeopleDetail.getPostCode());
        managePeopleDetailResponse.setVillage(mangePeopleDetail.getVillage());
        managePeopleDetailResponse.setSoi(mangePeopleDetail.getSoi());
        managePeopleDetailResponse.setTel(mangePeopleDetail.getTel());
        return managePeopleDetailResponse;
    }

    public boolean checkRegisterData(String manageTaxId){
        boolean isRegis = true;
        List<MangePeopleDetail> mangePeopleDetail = new ArrayList<>();
        try {
            mangePeopleDetail = this.managePeopleDetailRepository.searchByManagePeopleTaxIdLike(manageTaxId);
            if(mangePeopleDetail.size() > 0){
                isRegis = false;
            }
        }catch (ResponseException ex){
            logger.error(ex.getMessage());
        }

        return isRegis;
    }

}
