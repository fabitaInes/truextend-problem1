package com.truextend.problem1.service;

import com.truextend.problem1.dao.StudentDao;
import com.truextend.problem1.entity.ClazzStudent;
import com.truextend.problem1.entity.Student;
import com.truextend.problem1.exception.StudentAlreadyExistsException;
import com.truextend.problem1.exception.StudentFieldsException;
import com.truextend.problem1.exception.StudentNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Student service.
 */
public class StudentService {
//    private static final Logger logger = LogManager.getLogger(StudentService.class);

    private static StudentService INSTANCE;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static StudentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentService();
        }
        return INSTANCE;
    }

    /**
     * Select all collection.
     *
     * @return the collection
     */
    public Collection<Student> selectAll() {
        return StudentDao.getDao().fetchAll();
    }

    /**
     * Select by id student.
     *
     * @param id the id
     * @return the student
     * @throws StudentNotFoundException the student not found exception
     */
    public Student selectById(Long id) throws StudentNotFoundException {
        return StudentDao.getDao().fetchById(id);
    }

    /**
     * Add student.
     *
     * @param student the student
     * @return the student
     * @throws StudentAlreadyExistsException the student already exists exception
     * @throws StudentFieldsException        the student fields exception
     */
    public Student add(Student student) throws StudentAlreadyExistsException, StudentFieldsException {
        validateStudent(student);
        return StudentDao.getDao().insert(student);
    }

    private void validateStudent(Student student) throws StudentFieldsException {
        if (student.getId() == null) {
            throw new StudentFieldsException("Student Id cannot be null");
        }
        if (student.getLastName() == null) {
            throw new StudentFieldsException("Student Last Name cannot be null");
        }
        if (student.getFirstName() == null) {
            throw new StudentFieldsException("Student First Name cannot be null");
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws StudentNotFoundException the student not found exception
     */
    public void delete(Long id) throws StudentNotFoundException {
        StudentDao.getDao().delete(id);
    }

    /**
     * Update student.
     *
     * @param id      the id
     * @param student the student
     * @return the student
     * @throws StudentNotFoundException the student not found exception
     */
    public Student update(Long id, Student student) throws StudentNotFoundException {
        student.setId(id);
        return StudentDao.getDao().update(student);
    }

    /**
     * Gets by clazz code.
     *
     * @param code the code
     * @return the by clazz code
     */
    public Collection<Student> getByClazzCode(String code) {
        List<Long> studentIds = ClazzStudentService.getInstance().getByClazzCode(code).stream().map(ClazzStudent::getStudentId).collect(Collectors.toList());
        return StudentDao.getDao().fetchAll().stream().filter(student -> studentIds.contains(student.getId())).collect(Collectors.toList());
    }

    /**
     * Select by criteria collection.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the collection
     */
    public Collection<Student> selectByCriteria(String firstName, String lastName) {
        return StudentDao.getDao().fetchByCriteria(firstName, lastName);
    }
}
