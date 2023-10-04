package com.pragma.archetypespringboot.user.infrastructure.endpoints.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class UserControllerTest {

    private final static String SAVE_URL = "/api/v1/save-user";
    private final static String GET_URL = "/api/v1/get/{idUser}";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testSaveUserEndpoint() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SAVE_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"Carlos\",\"birthDate\":\"2005-02-18\",\"document\":\"123495\"}"))
                .andReturn();
        assertEquals(201,result.getResponse().getStatus());
        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Carlos").contains("2005-02-18").contains("123495");
    }
    @Test
    void testGetUserByIdEndpoint() throws Exception {

        testSaveUserEndpoint();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(GET_URL, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("Carlos").contains("2005-02-18").contains("123495");
    }

}