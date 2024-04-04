package com.kjy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@SpringBootTest
@AutoConfigureMockMvc
class SpringKjyApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Test
	public void testCheck() throws Exception {
		// userRepository.findById(id)를 호출할 때의 동작을 모의로 설정
		// 예: Mockito.when(userRepository.findById(1)).thenReturn(new UserEntity());

		mockMvc.perform(get("/multithread/{id}/check", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
		// .andExpect(jsonPath("$.id").value(1)); // 응답 내용 검증이 필요하다면 추가
	}
	@Test
	public void testCheckTicket() throws Exception {
		mockMvc.perform(get("/multithread/ticket"))
				.andExpect(status().isOk())
				.andExpect(content().string("0")); // 초기 count 값을 기반으로 검증
	}
	@Test
	public void testIssueTicket() throws Exception {
		System.out.println("멀티스레드 테스트 시작");
		int numberOfThreads = 20;
		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		for (int i = 0; i < numberOfThreads; i++) {
			service.submit(() -> {
				try {
					mockMvc.perform(post("/multithread/ticket"))
							.andExpect(status().isOk());
				}catch (Exception e){
					e.printStackTrace();
				}
			});
		}
		service.shutdown();
		service.awaitTermination(5, TimeUnit.MINUTES);

		mockMvc.perform(get("/multithread/2/ticket"))
				.andExpect(status().isOk())
				.andExpect(content().string("0")); // 초기 count 값을 기반으로 검증

	}
}
