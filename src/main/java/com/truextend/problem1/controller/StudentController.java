package com.truextend.problem1.controller;

import com.truextend.problem1.entity.Clazz;
import com.truextend.problem1.entity.Student;
import com.truextend.problem1.exception.StudentAlreadyExistsException;
import com.truextend.problem1.exception.StudentFieldsException;
import com.truextend.problem1.exception.StudentNotFoundException;
import com.truextend.problem1.service.ClazzService;
import com.truextend.problem1.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The type Student controller.
 */
@RestController
public class StudentController {

    /**
     * List collection.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the collection
     */
    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of registered students", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Student> list(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null && lastName == null) {
            return StudentService.getInstance().selectAll();
        } else {
            return StudentService.getInstance().selectByCriteria(firstName, lastName);
        }
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws StudentNotFoundException the student not found exception
     */
    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get student by ID", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved student"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public Student getById(@PathVariable Long id) throws StudentNotFoundException {
        return StudentService.getInstance().selectById(id);
    }

    /**
     * Create student.
     *
     * @param student the student
     * @return the student
     * @throws StudentAlreadyExistsException the student already exists exception
     * @throws StudentFieldsException        the student fields exception
     */
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created student"),
            @ApiResponse(code = 409, message = "Student already exists"),
            @ApiResponse(code = 400, message = "Invalid student")
    })
    public Student create(@RequestBody Student student) throws StudentAlreadyExistsException, StudentFieldsException {
        return StudentService.getInstance().add(student);
    }

    /**
     * Update student.
     *
     * @param id      the id
     * @param student the student
     * @return the student
     * @throws StudentNotFoundException the student not found exception
     */
    @PatchMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated student"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public Student update(@PathVariable Long id, @RequestBody Student student) throws StudentNotFoundException {
        return StudentService.getInstance().update(id, student);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws StudentNotFoundException the student not found exception
     */
    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a student", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted student"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public void delete(@PathVariable Long id) throws StudentNotFoundException {
        StudentService.getInstance().delete(id);
    }

    /**
     * Gets classes by id.
     *
     * @param id the id
     * @return the classes by id
     */
    @GetMapping("/students/{id}/classes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of classes by ID", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Clazz> getClassesById(@PathVariable Long id) {
        return ClazzService.getInstance().getByStudentId(id);
    }
}