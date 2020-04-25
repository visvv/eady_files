package com.vv.easy.server.controller;

import com.vv.easy.server.model.FileInfo;
import com.vv.easy.server.services.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MetadataControllerTest {
    @Autowired
    private MetadataController metadataController;
    @MockBean
    private FileService fileService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Test
    void list() throws Exception {
        final String path = "/";
        final FileInfo fileInfo = new FileInfo("pic.jpg",
                "/pics", 123455);
        when(fileService.getFileInfo(anyString())).thenReturn(fileInfo);
        mockMvc.perform(get("/info").param("path", "pic.jpg").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.content.name", is("pic.jpg")));
//        assertNotNull(this.metadataController.info(path));
    }

    @Test
    void info() {
    }
}