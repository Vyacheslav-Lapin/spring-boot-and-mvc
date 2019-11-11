package ru.vlapin.experiments.springbootandmvc;

import static com.jayway.jsonpath.JsonPath.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.vlapin.experiments.springbootandmvc.dao.CatRepository;
import ru.vlapin.experiments.springbootandmvc.model.Cat;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@org.springframework.security.test.context.support.WithMockUser(authorities = "ADMIN")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class SpringBootAndMvcApplicationTests {

//  @Autowired
  MockMvc mockMvc;

  CatRepository catRepository;

	@Test
	void contextLoads() {
	}

  @BeforeEach
  void setUp() {
  }

  @Test
  @SneakyThrows
  @DisplayName("cats end-point works correctly")
  void cats() {
    mockMvc.perform(get("/cats"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(HAL_JSON_VALUE))
        .andExpect(mvcResult -> assertEquals(3,
            parse(mvcResult.getResponse().getContentAsString())
                .<Integer>read("$.page.totalElements").intValue()));
  }
}
