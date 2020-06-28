package controller;

import com.d1.PuzzleLauncher;
import com.d1.controller.UserController;
import com.d1.factory.UserFactory;
import com.d1.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PuzzleLauncher.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFactory userFactory;

    @Before
    public void setUp() {
        userRepository.save(userFactory.create("Thor", "fifteenPuzzle"));
    }

    @Test
    public void whenGetRequestToUsers_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Thor"));
    }

    @Test
    public void whenGetRequestToCreateUser_thenCreateUserWithCorrectResponse() throws Exception {
        mockMvc.perform(post("/user/create")
                .param("userName", "Hulk")
                .param("gameName", "hulkPuzzle")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        assertEquals("Hulk", userRepository.findAll().get(1).getName());
    }

}
