package management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import management.ModelUtils;
import management.conroller.CommandController;
import management.dto.CommandDto;
import management.exception.handler.CustomExceptionHandler;
import management.service.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CommandControllerTest {
    private static final String commandLink = "/command";
    private MockMvc mockMvc;
    private final ErrorAttributes errorAttributes = new DefaultErrorAttributes();
    @Mock
    CommandService commandService;
    @InjectMocks
    CommandController commandController;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(commandController)
                .setControllerAdvice(new CustomExceptionHandler(errorAttributes))
                .build();
    }

    @Test
    void addNewCommandTest() throws Exception {
        CommandDto commandDto = ModelUtils.getCommandDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJson = objectMapper.writeValueAsString(commandDto);

        mockMvc.perform(post(commandLink + "/add")
                        .content(requestedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getInfoAboutCommandsTest() throws Exception {
        mockMvc.perform(get(commandLink + "/infoAboutCommands"))
                .andExpect(status().isOk());
    }

    @Test
    void editCommandsTest() throws Exception {
        CommandDto commandDto = ModelUtils.getCommandDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJson = objectMapper.writeValueAsString(commandDto);

        mockMvc.perform(patch(commandLink + "/{id}", 1)
                        .content(requestedJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommandsTest() throws Exception {
        mockMvc.perform(delete(commandLink + "/{id}", 1))
                .andExpect(status().isOk());
    }
}
