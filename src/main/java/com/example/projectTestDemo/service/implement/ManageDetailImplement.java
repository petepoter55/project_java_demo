package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.service.ManageDetailService;
import com.example.projectTestDemo.tools.UtilityTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@Service
public class ManageDetailImplement implements ManageDetailService {
    @Autowired
    ManagePeopleDetailRepository managePeopleDetailRepository;
    @Autowired
    UserRepository userRepository;

    @Value("${jwt.secretkey}")
    private String jwtSecretkey;

    @Override
    public String generateXml() throws JsonProcessingException {
        MangePeopleDetail mangePeopleDetail = this.managePeopleDetailRepository.findByRefNo("PE68");
//        Solution 1
//        XmlMapper xmlMapper = new XmlMapper();
//        String personXml = xmlMapper.writeValueAsString(mangePeopleDetail);
        XStream xstream = new XStream(new StaxDriver());
//        Solution2
        String dataXml = xstream.toXML(mangePeopleDetail);
        return dataXml;
    }

    @Override
    public Response createAccount(CreateAccountRequest createAccountRequest) {
        MangePeopleDetail mangePeopleDetail = this.managePeopleDetailRepository.findById(Integer.parseInt(createAccountRequest.getPeopleDetailId()));
        try {
            Boolean checkObject = ObjectUtils.isEmpty(createAccountRequest);
            Boolean checkObject2 = ObjectUtils.isEmpty(mangePeopleDetail);

            if(!checkObject && !checkObject2){
                if(createAccountRequest.getPassword().equals(createAccountRequest.getConfirmPassword())){
                    ManageUser user = new ManageUser();
                    String password = new UtilityTools().hashSha256(createAccountRequest.getPassword());
                    String username = "US" + createAccountRequest.getUsername();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setUcode(new UtilityTools().randomString(10));
                    user.setCreateDate(new UtilityTools().getFormatsDateMilli());
                    user.setEmail(mangePeopleDetail.getEmail());
                    user.setApprovedDate(new UtilityTools().getFormatsDateMilli());
                    user.setFirstName(mangePeopleDetail.getManagePeopleName());
                    user.setLastName(mangePeopleDetail.getManagePeopleLastName());
                    user.setApproved("Y");
                    user.setStatusLogin("Y");
                    user.setPostCode(mangePeopleDetail.getPostCode());
                    user.setCreateBy(BigInteger.valueOf(0));
                    user.setForceChangePassword(1);
                    user.setToken_user(generate(username,mangePeopleDetail.getEmail(),mangePeopleDetail.getManagePeopleTaxId()));
                    this.userRepository.save(user);
                }else {
                    return new Response(false,"รหัสผ่านระบุไม่ถูกต้อง เนื่องจาก ระบุรหัสผ่านไม่ตรงกับยืนยันรหัสผ่าน","500");
                }
            }else {
                return new Response(false,"สร้างบัญชีผู้ใช้งานไม่สำเร็จ","500");
            }
        }catch (ResponseException | NoSuchAlgorithmException | UnsupportedEncodingException | ParseException e){
            e.printStackTrace();
            return new Response(false,"สร้างบัญชีผู้ใช้งานไม่สำเร็จ","500");
        }
        return new Response(true,"สร้างบัญชีผู้ใช้งานเสร็จเรียบร้อย","500");
    }

    @Override
    public String gen() {
        return generate("test","2","001");
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        ManageUser manageUser = this.userRepository.findByUsername(loginRequest.getUsername());
        try {
            Boolean checkObject = ObjectUtils.isEmpty(manageUser);
            if(!checkObject){
                Boolean checkpass = new UtilityTools().checkPassphrases(manageUser.getPassword(),loginRequest.getPassword());
                if(!checkpass){
                    return new Response(false,"บัญชีผู้ใช้งานหรือ รหัสผ่านไม่ถูกต้องโปรดตรวจสอบ","500");
                }
            }else {
                return new Response(false,"บัญชีผู้ใช้งานไม่มีในระบบ","500");
            }

        }catch (ResponseException | NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
            return new Response(false,"เข้าสู่ระบบไม่สำเร็จ","500");
        }
        return new Response(true,"เข้าสู่ระบบสำเร็จ","200");
    }

    @Override
    public JwtResponse getDataToken(LoginRequest loginRequest) {
        ManageUser manageUser = this.userRepository.findByUsername(loginRequest.getUsername());
        JwtResponse jwtResponse = new JwtResponse();
        try {
          boolean checkUser = ObjectUtils.isEmpty(manageUser);
          if(!checkUser){
             jwtResponse = this.getDataJwt(manageUser.getToken_user());
          }
        }catch (ResponseException e){
            e.printStackTrace();
        }
        return jwtResponse;
    }


    public String generate(String username, String email, String managePeopleTaxId) {
        Calendar currentDate = Calendar.getInstance();
        Date date = currentDate.getTime();

//        currentDate.add(Calendar.MINUTE,expire);

        SecretKey key = Keys.hmacShaKeyFor(this.jwtSecretkey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("username", username)
                .claim("email", email)
                .claim("managePeopleTaxId", managePeopleTaxId)
                .setIssuedAt(date)
//                .setExpiration(currentDate.getTime())
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public JwtResponse getDataJwt(String jwtToken){
        JwtResponse jwtResponse = new JwtResponse();
        try {
            SecretKey key = Keys.hmacShaKeyFor(this.jwtSecretkey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            jwtResponse.setUsername(jws.getBody().get("username").toString());
            jwtResponse.setEmail(jws.getBody().get("email").toString());
            jwtResponse.setManagePeopleTaxId(jws.getBody().get("managePeopleTaxId").toString());
            jwtResponse.setIssueDate(jws.getBody().getIssuedAt());
        }catch (ResponseException e){
            e.printStackTrace();
        }
        return jwtResponse;
    }
}


