package com.abc.event_registration_system;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventTest {

    @Autowired
    private MockMvc mvc;

    private static String token = "";
    @Test
    @Order(1)
    public void t1createventwithauth() throws Exception {

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
                                "  \"password\": \"12433\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        int statuscode = r2.andReturn().getResponse().getStatus();
        String cont=    r2.andReturn().getResponse().getContentAsString();
        JsonNode node = JsonLoader.fromString(cont);
        String  token  = String.valueOf(node.get("token").get("accessToken"));
        EventTest.token = token;
        ResultActions r3=  mvc.perform(MockMvcRequestBuilders
                        .post("/events/createevent")
                        .param("username","john.doe")
                        .header("Authorization","Bearer "+token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"title\": \"Team Meeting\",\n" +
                                "  \"description\": \"Monthly team meeting to discuss project progress and plans.\",\n" +
                                "  \"startTime\": \"2024-06-21T09:00:00Z\",\n" +
                                "  \"endTime\": \"2024-06-21T10:00:00Z\",\n" +
                                "  \"location\": \"Conference Room A\",\n" +
                                "  \"organizer\": \"John Doe\",\n" +
                                "  \"maxParticipants\": 50,\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"createdAt\": \"2024-06-01T08:00:00Z\",\n" +
                                "  \"updatedAt\": \"2024-06-15T10:00:00Z\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        statuscode = r3.andReturn().getResponse().getStatus();
        Assert.assertEquals(200,statuscode);


    }
    @Test
    @Order(2)
    public void t2createeventwithoutauth() throws Exception {
        ResultActions r3=  mvc.perform(MockMvcRequestBuilders
                        .post("/events/createevent")
                        .param("username","john.doe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"title\": \"Team Meeting\",\n" +
                                "  \"description\": \"Monthly team meeting to discuss project progress and plans.\",\n" +
                                "  \"startTime\": \"2024-06-21T09:00:00Z\",\n" +
                                "  \"endTime\": \"2024-06-21T10:00:00Z\",\n" +
                                "  \"location\": \"Conference Room A\",\n" +
                                "  \"organizer\": \"John Doe\",\n" +
                                "  \"maxParticipants\": 50,\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"createdAt\": \"2024-06-01T08:00:00Z\",\n" +
                                "  \"updatedAt\": \"2024-06-15T10:00:00Z\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
        int    statuscode = r3.andReturn().getResponse().getStatus();
        Assert.assertEquals(401,statuscode);


    }

    @Test
    @Order(3)
    public void t3updateevent() throws Exception {



        ResultActions r3=  mvc.perform(MockMvcRequestBuilders
                        .post("/events/createevent")
                        .param("username","john.doe")
                        .header("Authorization","Bearer "+EventTest.token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"title\": \"Team Meeting\",\n" +
                                "  \"description\": \"Monthly team meeting to discuss project progress and plans.\",\n" +
                                "  \"startTime\": \"2024-06-21T09:00:00Z\",\n" +
                                "  \"endTime\": \"2024-06-21T10:00:00Z\",\n" +
                                "  \"location\": \"Conference Room A\",\n" +
                                "  \"organizer\": \"John Doe\",\n" +
                                "  \"maxParticipants\": 50,\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"createdAt\": \"2024-06-01T08:00:00Z\",\n" +
                                "  \"updatedAt\": \"2024-06-15T10:00:00Z\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        int statuscode = r3.andReturn().getResponse().getStatus();
        ResultActions r4=  mvc.perform(MockMvcRequestBuilders
                        .put("/events/updateevent?eventid=1")
                        .param("username","john.doe")
                        .header("Authorization","Bearer "+EventTest.token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"title\": \"Tam Meeting\",\n" +
                                "  \"description\": \"Monthly team meeting to discuss project progress and plans.\",\n" +
                                "  \"startTime\": \"2024-06-21T09:00:00Z\",\n" +
                                "  \"endTime\": \"2024-06-21T10:00:00Z\",\n" +
                                "  \"location\": \"Conference Room A\",\n" +
                                "  \"organizer\": \"John Doe\",\n" +
                                "  \"maxParticipants\": 50,\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"createdAt\": \"2024-06-01T08:00:00Z\",\n" +
                                "  \"updatedAt\": \"2024-06-15T10:00:00Z\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        ResultActions r5=  mvc.perform(MockMvcRequestBuilders
                        .get("/events/getevent?eventid=1")
                        .param("username","john.doe")
                        .header("Authorization","Bearer "+EventTest.token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        String cont=    r5.andReturn().getResponse().getContentAsString();
        JsonNode node = JsonLoader.fromString(cont);
        String title = String.valueOf(node.get("title")).replace("\"","");
        Assert.assertEquals(title,"Tam Meeting");


    }
    @Test
    @Order(4)
    public void t4onlytheuserwhocreatedcanupdate() throws Exception {

        ResultActions r4=  mvc.perform(MockMvcRequestBuilders
                        .put("/events/updateevent?eventid=1")
                        .param("username","john.boe")
                        .header("Authorization","Bearer "+EventTest.token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"title\": \"Tam Meeting\",\n" +
                                "  \"description\": \"Monthly team meeting to discuss project progress and plans.\",\n" +
                                "  \"startTime\": \"2024-06-21T09:00:00Z\",\n" +
                                "  \"endTime\": \"2024-06-21T10:00:00Z\",\n" +
                                "  \"location\": \"Conference Room A\",\n" +
                                "  \"organizer\": \"John Doe\",\n" +
                                "  \"maxParticipants\": 50,\n" +
                                "  \"username\": \"john.doe\",\n" +
                                "  \"createdAt\": \"2024-06-01T08:00:00Z\",\n" +
                                "  \"updatedAt\": \"2024-06-15T10:00:00Z\"\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError());

        int statuscode = r4.andReturn().getResponse().getStatus();
        Assert.assertEquals(500,statuscode);

    }
    @Test
    @Order(5)
    public void t5nootherusercandelteotherthancreatedone() throws Exception {




        ResultActions r4=  mvc.perform(MockMvcRequestBuilders
                        .delete("/events/deleteevent?eventid=1")
                        .param("username","john.boe")
                        .header("Authorization","Bearer "+EventTest.token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError());

        int  statuscode = r4.andReturn().getResponse().getStatus();
        Assert.assertEquals(500,statuscode);

    }
    @Test
    @Order(6)
    public void t6deleteevent() throws Exception {

        ResultActions r4=  mvc.perform(MockMvcRequestBuilders
                        .delete("/events/deleteevent?eventid=1")
                        .param("username","john.doe")
                        .header("Authorization","Bearer "+EventTest.token.replace("\"",""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        int  statuscode = r4.andReturn().getResponse().getStatus();
        Assert.assertEquals(200,statuscode);

    }

}