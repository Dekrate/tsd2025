package org.example.tsddemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserEndpoint.class)
class TaskNo3Test {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateNewUser() throws Exception {
        //given
        String newUser = """
                {
                    "name": "Andrzej",
                    "password": "123456",
                    "email": "andrzej@gmail.com",
                    "phoneNumber": "+48567123456"
                }
                """;
        //when & then
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(newUser)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}
