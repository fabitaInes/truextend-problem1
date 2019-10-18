package com.truextend.problem1.controller;

import com.truextend.problem1.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

/**
 * The type Student controller test.
 */
public class StudentControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp(){
        super.setUp();
    }

    /**
     * Create student.
     *
     * @throws Exception the exception
     */
    @Test
    public void createStudent() throws Exception {
        String uri = "/students";
        Student student = new Student(4893649L, "Flores", "Fabiana");

        String inputJson = super.mapToJson(student);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        Student content = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Student.class);
        assertEquals("Fabiana", content.getFirstName());
        assertEquals("Flores", content.getLastName());

        MvcResult mvcResultGet = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusGet = mvcResultGet.getResponse().getStatus();
        assertEquals(200, statusGet);
        String contentGet = mvcResultGet.getResponse().getContentAsString();
        Student[] list = super.mapFromJson(contentGet, Student[].class);
        assertTrue(list.length > 0);

        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete(uri + "/4893649")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusDelete = mvcResultDelete.getResponse().getStatus();
        assertEquals(204, statusDelete);


    }

    /**
     * Update student.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateStudent() throws Exception {
        String uri = "/students";
        Student student = new Student(4893649L, "Flores", "Fabiana");

        String inputJson = super.mapToJson(student);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        Student content = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Student.class);
        assertEquals("Fabiana", content.getFirstName());
        assertEquals("Flores", content.getLastName());

        Student studentNew = new Student();
        studentNew.setFirstName("Ines");

        String inputJsonNew = super.mapToJson(studentNew);

        MvcResult mvcResultPatch = mvc.perform(MockMvcRequestBuilders.patch(uri + "/4893649")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJsonNew)).andReturn();

        int statusPatch = mvcResultPatch.getResponse().getStatus();
        assertEquals(200, statusPatch);
        Student contentPatch = super.mapFromJson(mvcResultPatch.getResponse().getContentAsString(), Student.class);
        assertEquals("Ines", contentPatch.getFirstName());
        assertEquals("Flores", contentPatch.getLastName());


        MvcResult mvcResultGet = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusGet = mvcResultGet.getResponse().getStatus();
        assertEquals(200, statusGet);
        String contentGet = mvcResultGet.getResponse().getContentAsString();
        Student[] list = super.mapFromJson(contentGet, Student[].class);
        assertTrue(list.length > 0);

        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete(uri + "/4893649")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int statusDelete = mvcResultDelete.getResponse().getStatus();
        assertEquals(204, statusDelete);


    }

}