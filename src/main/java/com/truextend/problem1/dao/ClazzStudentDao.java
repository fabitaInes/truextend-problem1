package com.truextend.problem1.dao;

import com.truextend.problem1.entity.ClazzStudent;
import com.truextend.problem1.exception.ClazzStudentAlreadyExistsException;
import com.truextend.problem1.exception.ClazzStudentNotFoundException;
import com.truextend.problem1.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * The type Clazz student dao.
 */
public class ClazzStudentDao {
    private static final Logger logger = LogManager.getLogger(StudentDao.class);


    private static ClazzStudentDao DAO;
    private static ConcurrentMap<String, ClazzStudent> clazzStudents = new ConcurrentHashMap<>();


    /**
     * Gets dao.
     *
     * @return the dao
     */
    public static ClazzStudentDao getDao() {
        if (DAO == null) {
            DAO = new ClazzStudentDao();
        }
        return DAO;
    }

    /**
     * Insert clazz student.
     *
     * @param clazzStudent the clazz student
     * @return the clazz student
     * @throws ClazzStudentAlreadyExistsException the clazz student already exists exception
     */
    public ClazzStudent insert(ClazzStudent clazzStudent) throws ClazzStudentAlreadyExistsException {
        logger.info("Inserting ClazzStudent {}", clazzStudent);
        if (clazzStudents.containsKey(clazzStudent.getId())) {
            throw new ClazzStudentAlreadyExistsException(clazzStudent.getClazzCode(), clazzStudent.getStudentId());
        }
        return clazzStudents.computeIfAbsent(clazzStudent.getId(), code -> clazzStudent);
    }

    /**
     * Delete.
     *
     * @param code the code
     * @param id   the id
     * @throws ClazzStudentNotFoundException the clazz student not found exception
     */
    public void delete(String code, Long id) throws ClazzStudentNotFoundException {
        logger.info("Deleting Student by codes {} and  id {}", code, id);
        String clazzStudentId = Utils.getClazzStudentId(code, id);
        if (!clazzStudents.containsKey(clazzStudentId)) {
            throw new ClazzStudentNotFoundException(code, id);
        }
        clazzStudents.remove(clazzStudentId);
    }

    /**
     * Fetch by clazz code collection.
     *
     * @param code the code
     * @return the collection
     */
    public Collection<ClazzStudent> fetchByClazzCode(String code) {
        logger.info("Fetching ClazzStudent by code {}", code);

        return clazzStudents.values().stream().filter(clazzStudent -> clazzStudent.getClazzCode().equals(code)).collect(Collectors.toList());
    }

    /**
     * Fetch by student id collection.
     *
     * @param id the id
     * @return the collection
     */
    public Collection<ClazzStudent> fetchByStudentId(Long id) {
        logger.info("Fetching ClazzStudent by id {}", id);

        return clazzStudents.values().stream().filter(clazzStudent -> clazzStudent.getStudentId().equals(id)).collect(Collectors.toList());
    }
}
