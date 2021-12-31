package management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import management.ModelUtils;
import management.conroller.CommandController;
import management.conroller.FootballerController;
import management.dto.EditInfoAboutFootballerDto;
import management.dto.FootballerDto;
import management.entity.Footballer;
import management.exception.handler.CustomExceptionHandler;
import management.service.CommandService;
import management.service.FootballerService;
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
@SpringBootTest
public class FootballerControllerTest {
    private static final String FOOTBALLER_LINK = "/footballer";
    private MockMvc mockMvc;
    private final ErrorAttributes errorAttributes = new DefaultErrorAttributes();
    @Mock
    FootballerService footballerService;
    @InjectMocks
    FootballerController footballerController;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(footballerController)
                .setControllerAdvice(new CustomExceptionHandler(errorAttributes))
                .build();
    }

    @Test
    void addNewFootballerTest() throws Exception{
        FootballerDto footballerDto = ModelUtils.getFootballerDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(footballerDto);

        mockMvc.perform(post(FOOTBALLER_LINK + "/add")
                .content(requestedJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getInfoTest() throws Exception {
        mockMvc.perform(get(FOOTBALLER_LINK + "/allInfo"))
                .andExpect(status().isOk());
    }

    @Test
    void editInfo() throws Exception{
        EditInfoAboutFootballerDto editInfoAboutFootballerDto = ModelUtils.getEditedInfoAboutFootballerDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(editInfoAboutFootballerDto);

        mockMvc.perform(patch(FOOTBALLER_LINK + "/{id}",1)
                        .content(requestedJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFootballer() throws Exception{
        mockMvc.perform(delete(FOOTBALLER_LINK + "/{id}",1))
                .andExpect(status().isOk());
    }
}
