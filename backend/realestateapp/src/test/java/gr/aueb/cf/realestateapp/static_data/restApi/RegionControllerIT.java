package gr.aueb.cf.realestateapp.static_data.restApi;

import gr.aueb.cf.realestateapp.RealestateappApplication;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = RealestateappApplication.class)
@AutoConfigureMockMvc
public class RegionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRegions() throws Exception {
        mockMvc.perform(get("/api/regions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].name").exists());
    }
}
