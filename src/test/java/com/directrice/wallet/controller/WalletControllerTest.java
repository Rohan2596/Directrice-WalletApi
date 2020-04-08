package com.directrice.wallet.controller;

import com.directrice.wallet.dto.EditWallet;
import com.directrice.wallet.response.Response;
import com.directrice.wallet.service.WalletServiceImp;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    WalletServiceImp walletServiceImp;

    private EditWallet editWallet;

    @BeforeEach
    void setUp() {
        this.editWallet=new EditWallet("5e8de423774d5e3bc0e29bc9",0,"SEND");
    }

    //Create Wallet

    @Test
    void givenValidUserId_whenAuthenticated_shouldReturnValidResponse() throws Exception{

        MvcResult result = this.mockMvc.perform(post("/directrice/wallet")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Wallet Created Successfully.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }

    //Update Wallet

    @Test
    void givenValidUserId_Edited_whenAuthenticated_shouldReturnValidResponse() throws Exception{
        this.editWallet=new EditWallet("5e8de423774d5e3bc0e29bc9",0,"SEND");
        String editDTO=new Gson().toJson(this.editWallet);
        MvcResult result = this.mockMvc.perform(put("/directrice/wallet")
                .header("token","token")
                .content(editDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Wallet Updated Successfully.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }

    @Test
    void givenValidUserId_Edited_InValidWalletId_whenAuthenticated_shouldReturnValidResponse() throws Exception{
        this.editWallet=new EditWallet("",0,"TOPUP");
        String editDTO=new Gson().toJson(this.editWallet);
        MvcResult result = this.mockMvc.perform(put("/directrice/wallet")
                .header("token","token")
                .content(editDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Wallet Id cannot be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }

    @Test
    void givenValidUserId_Edited_InValidWalletId_NULL_whenAuthenticated_shouldReturnValidResponse() throws Exception{
        this.editWallet=new EditWallet(null,0,"5e8de423774d5e3bc0e29bc9");
        String editDTO=new Gson().toJson(this.editWallet);
        MvcResult result = this.mockMvc.perform(put("/directrice/wallet")
                .header("token","token")
                .content(editDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Wallet Id cannot be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }

    @Test
    void givenValidUserId_Edited_InValidTransMessage_NULL_whenAuthenticated_shouldReturnValidResponse() throws Exception{
        this.editWallet=new EditWallet("5e8de423774d5e3bc0e29bc9",0,null);
        String editDTO=new Gson().toJson(this.editWallet);
        MvcResult result = this.mockMvc.perform(put("/directrice/wallet")
                .header("token","token")
                .content(editDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Transaction Status cannot be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }


    @Test
    void givenValidUserId_Edited_InValidTransMessage_EMPTY_whenAuthenticated_shouldReturnValidResponse() throws Exception{
        this.editWallet=new EditWallet("5e8de423774d5e3bc0e29bc9",0,"");
        String editDTO=new Gson().toJson(this.editWallet);
        MvcResult result = this.mockMvc.perform(put("/directrice/wallet")
                .header("token","token")
                .content(editDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Transaction Status cannot be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }

    //Get User Wallet

    @Test
    void givenValidUserIdANDWalletId_whenAuthenticated_shouldReturnValidResponse() throws Exception{
                MvcResult result = this.mockMvc.perform(get("/directrice/wallet/walletId")
                .header("token","token")
                 .param("walletId","walletId")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("User Wallet Details.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).getMessage());

    }


    @Test
    void givenValidUserIdAndInWalletId_whenAuthenticated_shouldReturnValidResponse() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/directrice/wallet/walletId")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());

    }

}
