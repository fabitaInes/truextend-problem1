package com.truextend.problem1.controller;

import com.truextend.problem1.entity.Clazz;
import com.truextend.problem1.entity.ClazzStudent;
import com.truextend.problem1.entity.Student;
import com.truextend.problem1.exception.*;
import com.truextend.problem1.service.ClazzService;
import com.truextend.problem1.service.ClazzStudentService;
import com.truextend.problem1.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The type Clazz controller.
 */
@RestController
@Api(value = "Classes", description = "Classes CRUD, browsing and queries")
public class ClazzController {

    /**
     * List collection.
     *
     * @param tittle      the tittle
     * @param description the description
     * @return the collection
     */
    @GetMapping("/classes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of registered classes", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Clazz> list(@RequestParam(value = "tittle", required = false) String tittle, @RequestParam(value = "description", required = false) String description) {
        if (tittle == null && description == null) {
            return ClazzService.getInstance().selectAll();
        } else {
            return ClazzService.getInstance().selectByCristeria(tittle, description);
        }
    }

    /**
     * Gets by code.
     *
     * @param code the code
     * @return the by code
     * @throws ClazzNotFoundException the clazz not found exception
     */
    @GetMapping("/classes/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a class by code", response = Clazz.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved class"),
            @ApiResponse(code = 404, message = "Class not found")
    })
    public Clazz getByCode(@PathVariable String code) throws ClazzNotFoundException {
        return ClazzService.getInstance().selectByCode(code);
    }

    /**
     * Create clazz.
     *
     * @param clazz the clazz
     * @return the clazz
     * @throws ClazzAlreadyExistsException the clazz already exists exception
     * @throws ClazzFieldsException        the clazz fields exception
     */
    @PostMapping("/classes")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a class", response = Clazz.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created class"),
            @ApiResponse(code = 409, message = "Class already exists"),
            @ApiResponse(code = 400, message = "Invalid class")
    })
    public Clazz create(@RequestBody Clazz clazz) throws ClazzAlreadyExistsException, ClazzFieldsException {
        return ClazzService.getInstance().add(clazz);
    }

    /**
     * Update clazz.
     *
     * @param code  the code
     * @param clazz the clazz
     * @return the clazz
     * @throws ClazzNotFoundException the clazz not found exception
     */
    @PatchMapping("/classes/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a class", response = Clazz.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated class"),
            @ApiResponse(code = 404, message = "Class not found")
    })
    public Clazz update(@PathVariable String code, @RequestBody Clazz clazz) throws ClazzNotFoundException {
        return ClazzService.getInstance().update(code, clazz);
    }

    /**
     * Delete.
     *
     * @param code the code
     * @throws ClazzNotFoundException the clazz not found exception
     */
    @DeleteMapping("/classes/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a class", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted class"),
            @ApiResponse(code = 404, message = "Class not found")
    })
    public void delete(@PathVariable String code) throws ClazzNotFoundException {
        ClazzService.getInstance().delete(code);
    }

    /**
     * Assign clazz student.
     *
     * @param code the code
     * @param id   the id
     * @return the clazz student
     * @throws ClazzNotFoundException             the clazz not found exception
     * @throws StudentNotFoundException           the student not found exception
     * @throws ClazzStudentAlreadyExistsException the clazz student already exists exception
     */
    @PostMapping("/classes/{code}/students/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Assign class to student", response = ClazzStudent.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully assign class to student"),
            @ApiResponse(code = 404, message = "Class or student not found"),
            @ApiResponse(code = 409, message = "Class or student already exists")
    })
    public ClazzStudent assign(@PathVariable String code, @PathVariable Long id) throws ClazzNotFoundException, StudentNotFoundException, ClazzStudentAlreadyExistsException {
        return ClazzStudentService.getInstance().add(code, id);
    }

    /**
     * Unassign.
     *
     * @param code the code
     * @param id   the id
     * @throws ClazzStudentNotFoundException the clazz student not found exception
     */
    @DeleteMapping("/classes/{code}/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Unassign class to student", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully unassign class to student"),
            @ApiResponse(code = 404, message = "Class or student not found")
    })
    public void unassign(@PathVariable String code, @PathVariable Long id) throws ClazzStudentNotFoundException {
        ClazzStudentService.getInstance().delete(code, id);
    }

    /**
     * Gets students by code.
     *
     * @param code the code
     * @return the students by code
     */
    @GetMapping("/classes/{code}/students")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of students by code", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Student> getStudentsByCode(@PathVariable String code) {
        return StudentService.getInstance().getByClazzCode(code);
    }

}