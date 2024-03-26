package com.kjy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;



@SpringBootTest
@AutoConfigureMockMvc
class SpringKjyApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Test
	void testApiController() throws Exception{
		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

		info.add("id", "1");
		mockMvc.perform(
				get("/multithread/{info}/like", info.getFirst("id")))
				.andExpect(status().isOk())
				.andExpect(content().string("좋아용"))
				.andDo(print()).andReturn();
	}

}
