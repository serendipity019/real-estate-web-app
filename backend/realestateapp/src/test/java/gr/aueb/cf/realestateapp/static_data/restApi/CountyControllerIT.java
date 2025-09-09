package gr.aueb.cf.realestateapp.static_data.restApi;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.core.exceptions.AppServerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CountyControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Rollback
    void shouldReturnCountiesByRegionId() throws Exception {
        mockMvc.perform(get("/api/counties")
                .param("regionId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").value("Athens Central"));
    }

    @Test
    @Rollback
    void shouldReturnNotFoundException() throws Exception {
        mockMvc.perform(get("/api/counties")
                .param("regionId", "5"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("COUNTY_NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("No counties found for region with id 5"));
    }
}
