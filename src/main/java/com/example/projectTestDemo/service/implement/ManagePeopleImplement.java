package com.example.projectTestDemo.service.implement;


import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleDetailResponse;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageMasterDistrict;
import com.example.projectTestDemo.entity.ManagePeopleVat;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.ManageMasterDistrictRepository;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.repository.ManagePeopleVatRepository;
import com.example.projectTestDemo.service.ManagePeopleService;
import com.example.projectTestDemo.tools.UtilityTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



@Service
public class ManagePeopleImplement implements ManagePeopleService {

      private final ManagePeopleDetailRepository managePeopleDetailRepository;
      private final ManagePeopleVatRepository managePeopleVatRepository;
      private final ManageMasterDistrictRepository manageMasterDistrictRepository;

      @Autowired
      public ManagePeopleImplement(ManagePeopleDetailRepository managePeopleDetailRepository,ManagePeopleVatRepository managePeopleVatRepository,
                                   ManageMasterDistrictRepository manageMasterDistrictRepository){
          this.managePeopleDetailRepository = managePeopleDetailRepository;
          this.managePeopleVatRepository = managePeopleVatRepository;
          this.manageMasterDistrictRepository = manageMasterDistrictRepository;
      }

    @Override
    public ManagePeopleViewResponse getDate(MangeRegisterRequest mangeRegisterRequest) {
        List<ManagePeopleDetailResponse> managePeopleDetailResponseList = new ArrayList<>();
        ManagePeopleDetailResponse managePeopleDetailResponse = new ManagePeopleDetailResponse();
        ManagePeopleViewResponse managePeopleViewResponse = new ManagePeopleViewResponse();
        boolean status = true;
        String message = "";

        try {
            List<MangePeopleDetail> mangePeopleDetailList = this.managePeopleDetailRepository.searchByManagePeopleTaxIdLike(mangeRegisterRequest.getManageTaxId());
            if(mangePeopleDetailList.size() > 0){
                for(MangePeopleDetail data : mangePeopleDetailList){
                    managePeopleDetailResponse = this.setObjectDetailView(data);
                    managePeopleDetailResponseList.add(managePeopleDetailResponse);
                }
                message = "ดึงข้อมูลสำเร็จ";
            }else {
                message = "ไม่พบข้อมูล";
                status = false;
            }
            managePeopleViewResponse.setStatus(status);
            managePeopleViewResponse.setMessage(message);
            managePeopleViewResponse.setManagePeopleDetailResponse(managePeopleDetailResponseList);

        }catch (ResponseException e){
            e.printStackTrace();
            managePeopleViewResponse.setStatus(false);
            managePeopleViewResponse.setMessage(e.getMessage());
            managePeopleViewResponse.setManagePeopleDetailResponse(null);
        }
        return managePeopleViewResponse;
    }

    @Override
    public Response deleteDate(int id) {

        try {
            this.managePeopleDetailRepository.deleteById(id);

        }catch (ResponseException e){
            e.printStackTrace();
            return new Response(false,e.getMessage(),"500");
        }
        return new Response(true,"ลบข้อมูลสำเร็จ","200");
    }

    @Override
    public Response createDataPeople(MangeRegisterRequest mangeRegisterRequest) {
        MangePeopleDetail mangePeopleDetail = new MangePeopleDetail();
        ManagePeopleVat managePeopleVat = this.managePeopleVatRepository.findByManagePeopleTaxId(mangeRegisterRequest.getManageTaxId());

        try {
            if(managePeopleVat != null){
                mangePeopleDetail = this.setObjectDetail(managePeopleVat);
                this.managePeopleDetailRepository.save(mangePeopleDetail);
            }else {
                return new Response(false,"ไม่พบข้อมูลที่ทำการลงทะเบียน","500");
            }

        }catch (ResponseException | ParseException e){
            e.printStackTrace();
            return new Response(false,e.getMessage(),"500");
        }
        return new Response(true,"ลงทะเบียนเรียบร้อบ","200");
    }

    @Override
    public Response updateDataPeople(int id) {
        MangePeopleDetail mangePeopleDetail = new MangePeopleDetail();
        try {
            mangePeopleDetail = this.managePeopleDetailRepository.findById(id);

            if(mangePeopleDetail != null){
//                mangePeopleDetail.setStatusId(1);
                mangePeopleDetail.setUpdateDateTime(new UtilityTools().getFormatsDateMilli());
            }

        }catch (ResponseException | ParseException e){
           e.printStackTrace();
        }

        this.managePeopleDetailRepository.save(mangePeopleDetail);

        return new Response(true,"อัปเดตข้อมูลเสร็จเรียบร้อย","200");
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
        mangePeopleDetail.setBuilding(managePeopleVat.getBuilding());;
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

    public ManagePeopleDetailResponse setObjectDetailView(MangePeopleDetail mangePeopleDetail){
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
        managePeopleDetailResponse.setCreateDateTime(mangePeopleDetail.getCreateDateTime());
        return managePeopleDetailResponse;
    }

}
