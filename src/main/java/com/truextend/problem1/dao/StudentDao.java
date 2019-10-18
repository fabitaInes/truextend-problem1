package com.truextend.problem1.dao;

import com.truextend.problem1.entity.Student;
import com.truextend.problem1.exception.StudentAlreadyExistsException;
import com.truextend.problem1.exception.StudentNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * The type Student dao.
 */
public class StudentDao {
    private static final Logger logger = LogManager.getLogger(StudentDao.class);


    private static StudentDao DAO;
    private static ConcurrentMap<Long, Student> students = new ConcurrentHashMap<>();

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public static StudentDao getDao() {
        if (DAO == null) {
            DAO = new StudentDao();
        }
        return DAO;
    }

    /**
     * Fetch all collection.
     *
     * @return the collection
     */
    public Collection<Student> fetchAll() {
        logger.info("Fetching all Students");
        return students.values();
    }

    /**
     * Fetch by id student.
     *
     * @param id the id
     * @return the student
     * @throws StudentNotFoundException the student not found exception
     */
    public Student fetchById(Long id) throws StudentNotFoundException {
        logger.info("Fetching Student by id {}", id);
        Student student = students.getOrDefault(id, null);
        if (student == null) {
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    /**
     * Insert student.
     *
     * @param student the student
     * @return the student
     * @throws StudentAlreadyExistsException the student already exists exception
     */
    public Student insert(Student student) throws StudentAlreadyExistsException {
        logger.info("Inserting Student {}", student);
        if (students.containsKey(student.getId())) {
            throw new StudentAlreadyExistsException(student.getId());
        }
        return students.computeIfAbsent(student.getId(), id -> student);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws StudentNotFoundException the student not found exception
     */
    public void delete(Long id) throws StudentNotFoundException {
        logger.info("Deleting Student by id {}", id);
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        students.remove(id);
    }

    /**
     * Update student.
     *
     * @param student the student
     * @return the student
     * @throws StudentNotFoundException the student not found exception
     */
    public Student update(Student student) throws StudentNotFoundException {
        logger.info("Updating Student {}", student);

        if (!students.containsKey(student.getId())) {
            throw new StudentNotFoundException(student.getId());
        }
        return students.merge(student.getId(), student, (oldStudent, newStudent) -> {
            if (newStudent.getFirstName() == null) {
                newStudent.setFirstName(oldStudent.getFirstName());
            }
            if (newStudent.getLastName() == null) {
                newStudent.setLastName(oldStudent.getLastName());
            }
            return newStudent;
        });
    }

    /**
     * Fetch by criteria collection.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the collection
     */
    public Collection<Student> fetchByCriteria(String firstName, String lastName) {
        return students.values().stream().filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }
}
