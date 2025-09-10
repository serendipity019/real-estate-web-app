package gr.aueb.cf.realestateapp.static_data.restApi;

import gr.aueb.cf.realestateapp.RealestateappApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RealestateappApplication.class)
@AutoConfigureMockMvc
public class CountyControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCountiesByRegionId() throws Exception {
        mockMvc.perform(get("/api/counties/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].name").exists());
    }

    @Test
    void shouldReturnNotFoundException() throws Exception {
        mockMvc.perform(get("/api/counties/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("NotFound"));
    }

    @Test
    void shouldReturnBadRequestWhenRegionIdIsInvalid() throws Exception {
        mockMvc.perform(get("/api/counties/abc")  // invalid number
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
