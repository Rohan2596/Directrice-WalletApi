package com.directrice.wallet.controller;

import com.directrice.wallet.response.Response;
import com.directrice.wallet.service.TransactionServiceImp;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    TransactionServiceImp transactionServiceImp;

    //controller test for get all transaction

    @Test
    void givenValidUserIdAndWalletId_whenVerified_shouldReturnValidResponse() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/directrice/transaction/user")
                .header("token","userID")
                .param("walletId","walletId")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("All Wallet",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }


}
