package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.test.controller.CommonEntiesController;
import com.example.test.model.Student;
import com.example.test.model.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonEntiesController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommonEntitesControllerTest1 {
     @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Test
     public void contextLoads() {

     }

     @Test
     public void testGetAllEmployees() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allrecord",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        Teacher tec = restTemplate.getForObject(getRootUrl() + "/allrecord/1", Teacher.class);
        System.out.println(tec.getFirstName());
        assertNotNull(tec);
    }

    @Test
    public void testCreateEmployee() {
    	Student std=new Student();
		std.setId(1);
		std.setHobby("hobby");
		
		Teacher teacher = new Teacher();
		teacher.setId(1);
		teacher.setFirstName("firstName");
		teacher.setEmail("email");
		teacher.setLastName("lastname");
		teacher.setStudentDetail(std);
        ResponseEntity<Teacher> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", teacher, Teacher.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Teacher tec = restTemplate.getForObject(getRootUrl() + "/allrecord/" + id, Teacher.class);
        tec.setFirstName("admin1");
        tec.setLastName("admin2");
        restTemplate.put(getRootUrl() + "/allrecord/" + id, tec);
        Teacher updatedTeacher = restTemplate.getForObject(getRootUrl() + "/allrecord/" + id, Teacher.class);
        assertNotNull(updatedTeacher);
    }

    @Test
    public void testDeleteEmployee() {
         int id = 2;
         Teacher teacher = restTemplate.getForObject(getRootUrl() + "/allrecord/" + id, Teacher.class);
         assertNotNull(teacher);
         restTemplate.delete(getRootUrl() + "/allrecord/" + id);
         try {
              teacher = restTemplate.getForObject(getRootUrl() + "/allrecord/" + id, Teacher.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
}