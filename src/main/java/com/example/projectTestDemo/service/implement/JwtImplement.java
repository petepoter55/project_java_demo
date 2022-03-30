package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

@Service
public class JwtImplement implements JwtService {
    private static final Logger logger = Logger.getLogger(JwtImplement.class);

    @Value("${jwt.secretkey}")
    private String jwtSecretkey;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JwtResponse getDataToken(LoginRequest loginRequest) {
        ManageUser manageUser = this.userRepository.findByUsername(loginRequest.getUsername());
        JwtResponse jwtResponse = new JwtResponse();
        try {
            boolean checkUser = ObjectUtils.isEmpty(manageUser);
            if (!checkUser) {
                jwtResponse = this.getDataJwt(manageUser.getToken_user());
            }
        } catch (ResponseException e) {
            e.printStackTrace();
        }
        return jwtResponse;
    }

    @Override
    public Response generateToken(String username, String managePeopleTaxId, String beforeToken) {
        ManageUser manageUser = this.userRepository.findByUsername(username);
        try {
            if(manageUser != null || !manageUser.getToken_user().equals(beforeToken)){
                String afterToken = this.generate(manageUser.getUsername(),manageUser.getEmail(),managePeopleTaxId);
                manageUser.setToken_user(afterToken);
                this.userRepository.save(manageUser);
            }else {
                return new Response(false, "สร้าง token ไม่สำเร็จ", "500");
            }
        }catch (ResponseException e){
            e.printStackTrace();
        }
        return new Response(true, "สร้าง token สำเร็จ", "200");
    }

    public String generate(String username, String email, String managePeopleTaxId) {
        Calendar currentDate = Calendar.getInstance();
        Date date = currentDate.getTime();

        SecretKey key = Keys.hmacShaKeyFor(this.jwtSecretkey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("username", username)
                .claim("email", email)
                .claim("managePeopleTaxId", managePeopleTaxId)
                .setIssuedAt(date)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public JwtResponse getDataJwt(String jwtToken) {
        JwtResponse jwtResponse = new JwtResponse();
        try {
            SecretKey key = Keys.hmacShaKeyFor(this.jwtSecretkey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            jwtResponse.setUsername(jws.getBody().get("username").toString());
            jwtResponse.setEmail(jws.getBody().get("email").toString());
            jwtResponse.setManagePeopleTaxId(jws.getBody().get("managePeopleTaxId").toString());
            jwtResponse.setIssueDate(jws.getBody().getIssuedAt());
        } catch (ResponseException e) {
            e.printStackTrace();
        }
        return jwtResponse;
    }
}
