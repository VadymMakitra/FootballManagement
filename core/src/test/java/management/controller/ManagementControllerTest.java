package management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import management.ModelUtils;
import management.conroller.FootballerController;
import management.conroller.ManagementController;
import management.dto.TransferDto;
import management.exception.CommandNotFoundException;
import management.exception.FootballerAlreadyInCommandException;
import management.exception.FootballerNotFoundException;
import management.exception.NotEnoughBudgetException;
import management.exception.handler.CustomExceptionHandler;
import management.service.FootballerService;
import management.service.ManagementService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManagementControllerTest {
    private static final String MANAGEMENT_LINK = "/footballer/management";
    private MockMvc mockMvc;
    private ErrorAttributes errorAttributes = new DefaultErrorAttributes();
    @Mock
    ManagementService managementService;
    @InjectMocks
    ManagementController managementController;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(managementController)
                .setControllerAdvice(new CustomExceptionHandler(errorAttributes))
                .build();
    }

    @Test
    void transferPlayerTest() throws Exception{
        TransferDto transferDto = ModelUtils.getTransferDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(transferDto);

        mockMvc.perform(post(MANAGEMENT_LINK + "/transfer")
                .content(requestedJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void transferPlayerThrowFootballerNotFoundException() throws Exception{
        TransferDto transferDto = ModelUtils.getTransferDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(transferDto);

        doThrow(FootballerNotFoundException.class).when(managementService).transferFootballer(transferDto);

        mockMvc.perform(post(MANAGEMENT_LINK + "/transfer")
                        .content(requestedJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void transferPlayerThrowCommandNotFoundException() throws Exception{
        TransferDto transferDto = ModelUtils.getTransferDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(transferDto);

        doThrow(CommandNotFoundException.class).when(managementService).transferFootballer(transferDto);

        mockMvc.perform(post(MANAGEMENT_LINK + "/transfer")
                        .content(requestedJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void transferPlayerThrowFootballerAlreadyInCommandException() throws Exception{
        TransferDto transferDto = ModelUtils.getTransferDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(transferDto);

        doThrow(FootballerAlreadyInCommandException.class).when(managementService).transferFootballer(transferDto);

        mockMvc.perform(post(MANAGEMENT_LINK + "/transfer")
                        .content(requestedJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void transferPlayerThrowNotEnoughBudgetEException() throws Exception{
        TransferDto transferDto = ModelUtils.getTransferDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestedJSON = objectMapper.writeValueAsString(transferDto);

        doThrow(NotEnoughBudgetException.class).when(managementService).transferFootballer(transferDto);

        mockMvc.perform(post(MANAGEMENT_LINK + "/transfer")
                        .content(requestedJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
