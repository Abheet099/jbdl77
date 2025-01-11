package com.example.minorProject.controller;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.minorProject.config.SecurityConfig;
import com.example.minorProject.config.TestSecurityConfig;
import com.example.minorProject.enums.TransactionType;
import com.example.minorProject.service.TransactionService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


  // Loads the full application context
@WebMvcTest(TransactionController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;  // Use @MockBean to mock the service

    @Autowired
    private MockMvc mockMvc;  // Automatically injects MockMvc

    @Test
    public void transaction_test() throws Exception {
        int bookId = 1;
        int studentId = 1;

        // Mock the service call
        given(transactionService.transact(TransactionType.ISSUE.name(), studentId, bookId))
                .willReturn(UUID.randomUUID().toString());

        // Prepare the request parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("studentId", String.valueOf(studentId));
        params.add("bookId", String.valueOf(bookId));

        // Perform the POST request
        ResultActions response = mockMvc.perform(post("/transaction/" + TransactionType.ISSUE.name())
                .contentType(MediaType.APPLICATION_JSON)
                .params(params));

        // Verify the response
        response.andDo(print())
                .andExpect(status().isOk());
    }

}
