package controller;

import com.d1.PuzzleLauncher;
import com.d1.controller.GameController;
import com.d1.domain.Game;
import com.d1.domain.User;
import com.d1.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PuzzleLauncher.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameController gameController;

    @Autowired
    GameRepository gameRepository;
    public List<User> expectedUserList = new ArrayList<>();

    @Before
    public void setUp() {
        int[][] puzzle = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        expectedUserList.add(new User(0
                , "Sam"
                , new Game("puzzle"
                , "UP"
                , puzzle
                , 3
                , 3
                , false)));
    }

    @Test
    public void givenUser_thenGetUser_thenStatus200() throws Exception {
        gameRepository.createUser("Sam");
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", is("Sam")));
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws Exception{
        mockMvc.perform(post("/create-user")
                .param("userName","Thor")
                .contentType(MediaType.APPLICATION_JSON));
        assertThat(gameRepository.userList).extracting(User::getName).containsOnly("Thor");
    }




}
