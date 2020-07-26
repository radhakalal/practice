package com.example.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.test.controller.CommonEntiesController;
import com.example.test.model.Student;
import com.example.test.model.Teacher;
import com.example.test.service.CommonEntiesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value= CommonEntiesController.class)
public class CommonEntiesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommonEntiesService commonEntiesService;
	
	@Test
	public void testCreateTicket() throws Exception {
		
		Student std=new Student();
		std.setId(1);
		std.setHobby("hobby");
		
		Teacher teacher = new Teacher();
		teacher.setId(1);
		teacher.setFirstName("firstName");
		teacher.setEmail("email");
		teacher.setLastName("lastname");
		teacher.setStudentDetail(std);
		String inputInJson = this.mapToJson(teacher);
		
		String URI = "/common/teacheStudent";
		
		Mockito.when(commonEntiesService.saveRecords(Mockito.any(Teacher.class))).thenReturn(teacher);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetTicketById() throws Exception {
		Student std=new Student();
		std.setId(1);
		std.setHobby("hobby");
		
		
		Object teacher =  new Teacher();
		 ((Teacher) teacher).setId(1);
		((Teacher) teacher).setFirstName("firstName");
		((Teacher) teacher).setEmail("email");
		((Teacher) teacher).setLastName("lastname");
		((Teacher) teacher).setStudentDetail(std);
		
	
	
		
		Mockito.when(commonEntiesService.recordByID(1)).thenReturn((Optional<Teacher>) teacher);
		
		String URI = "/common/allrecord/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(teacher);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetAllBookedTickets() throws Exception {

		Student std=new Student();
		std.setId(1);
		std.setHobby("hobby");
		
		Teacher teacher = new Teacher();
		teacher.setId(1);
		teacher.setFirstName("firstName");
		teacher.setEmail("email");
		teacher.setLastName("lastname");
		teacher.setStudentDetail(std);
		
		Student std1=new Student();
		std1.setId(2);
		std1.setHobby("hobby2");
		
		Teacher teacher1 = new Teacher();
		teacher1.setId(2);
		teacher1.setFirstName("firstName2");
		teacher1.setEmail("email");
		teacher1.setLastName("lastname");
		teacher1.setStudentDetail(std1);
		
		List<Teacher> teacherList = new ArrayList<>();
		teacherList.add(teacher);
		teacherList.add(teacher1);
		
		Mockito.when(commonEntiesService.getRecort()).thenReturn(teacherList);
		
		String URI = "/common/allrecord";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(teacherList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void updateProduct() throws Exception {
	   String uri = "/common/update/2";
	   Student std=new Student();
		std.setId(2);
		std.setHobby("hobby2");
		
		Teacher teacher = new Teacher();
		teacher.setId(2);
		teacher.setFirstName("firstName2");
		teacher.setEmail("email");
		teacher.setLastName("lastname");
		teacher.setStudentDetail(std);
	   
	   String inputJson = this.mapToJson(teacher);
	   Mockito.when(commonEntiesService.saveRecords(Mockito.any(Teacher.class))).thenReturn(teacher);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   System.out.println("mvcresult : "+mvcResult);
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Teacher is updated successsfully");
	}
	
	@Test
	public void deleteProduct() throws Exception {
	   String uri = "/common/allrecord/2";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Teacher is deleted successsfully");
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
       