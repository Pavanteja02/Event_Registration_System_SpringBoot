package com.abc.event_registration_system;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void loginuser() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        ResultActions r=  mvc.perform(MockMvcRequestBuilders
                        .post("/auth/createuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"username\":\"user1\",\n" +
                                "    \"id\":1,\n" +
                                "    \"password\":\"pass1\",\n" +
                                "    \"firstName\":\"fn1\",\n" +
                                "    \"lastName\":\"ln1\",\n" +
                                "    \"email\":\"email1@sample.com\",\n" +
                                "    \"enabled\":true\n" +
                                "    \n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        ResultActions r2=  mvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"username\":\"user1\",\n" +
                                "    \"password\":\"pass1\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        int statuscode = r2.andReturn().getResponse().getStatus();
        Assert.assertEquals(200,statuscode);


    }

    @Test
    public void invalidcreds() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        ResultActions r=  mvc.perform(MockMvcRequestBuilders
                        .post("/auth/createuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"password\": \"12433\",\n" +
                                "  \"enabled\":true,\n" +
                                "  \"id\":1,\n" +
                                "  \"email\":\"ss.com\",\n" +
                                "  \"firstName\":\"ss\",\n" +
                                "  \"lastName\":\"efefe\"\n" +
                                "\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        ResultActions r2=  mvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\n" +
                                "{\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"password\": \"1433\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
        int statuscode = r2.andReturn().getResponse().getStatus();
        Assert.assertEquals(400,statuscode);


    }



}
