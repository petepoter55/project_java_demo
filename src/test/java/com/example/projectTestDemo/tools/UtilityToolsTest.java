package com.example.projectTestDemo.tools;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
public class UtilityToolsTest {

    @Test
    public void isNumberic_case_returnTrue() throws Exception {
        ///////////////
        // ARRANGE = ค่าเริ่มต้น
        ///////////////
        final String DATA = "123";
        ///////////////
        // ACT = ส่งข้อมูลไปที่ Function or Method
        ///////////////
        boolean test = UtilityTools.isNumberic(DATA);
        ///////////////
        // ASSERT = ผลลัพธ์ที่คาดหวัง
        //////////////
        assertEquals(true,test);
    }

}