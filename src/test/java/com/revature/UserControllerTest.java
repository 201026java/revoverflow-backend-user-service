package com.revature;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.models.User;
import com.revature.services.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UserServiceApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	static int id = 12;
	static User u1;

	@Autowired
	private WebApplicationContext context;

	@Mock
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		u1 = new User(12, 26, 0, true, null, "admin@rss.com", "Admin", "Admin", null);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
	public void will_return_user_with_a_status_of_200() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/12").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(200, result.getResponse().getStatus());
		// System.out.println(content);
//    			assertTrue("This return object conains the string", content.contains("admin@rss.com"));
	}

	
	 @Test
	 @WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER") 
	 public void will__not_return_user_with_a_status_of_200() throws Exception { MvcResult
		 result = mockMvc.perform(MockMvcRequestBuilders.get("/15")
		 .accept(MediaType.APPLICATION_JSON) ).andReturn(); String content =
		 result.getResponse().getContentAsString(); assertEquals(200,
		 result.getResponse().getStatus()); assertEquals("", content); 
		 }
	 
	@Test
	@WithMockUser(username = "user@rss.com", password = "Password123!", authorities = "user")
	public void will_return_nothing_with_a_status_of_400() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/alatreon").accept(MediaType.APPLICATION_JSON)).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(400, result.getResponse().getStatus());
		assertEquals("", content);
	}

	@Test
	@WithMockUser(username = "user@rss.com", password = "Password123!", authorities = "user")
	public void will_return_nothing_with_a_status_of_404() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/20/alatreon").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(404, result.getResponse().getStatus());
		assertEquals("", content);
	}
}
