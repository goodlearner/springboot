package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests
{

    private MockMvc mock;

    @Before
    public void setup()
    {
        mock = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void testHello() throws Exception
    {
        mock.perform(post("/hello")//.accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello SpringBoot!")));
    }

    @Test
    public void testUserApi() throws Exception
    {
        //Get User List
        mock.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //POST add user
        mock.perform(post("/users/")
                .param("id", "1")
                .param("name", "User")
                .param("age", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
        mock.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"User\",\"age\":1}]")));

        //Get user
        mock.perform(get("/users/1")).andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"User\",\"age\":1}")));


        //PUT update user
        mock.perform(put("/users/1")
                .param("name", "user-new")
                .param("age", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
        mock.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"user-new\",\"age\":2}]")));
        //DELETE delete user
        mock.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
        mock.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
}
