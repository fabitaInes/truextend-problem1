package com.truextend.problem1.service;

import com.truextend.problem1.dao.ClazzDao;
import com.truextend.problem1.dao.ClazzStudentDao;
import com.truextend.problem1.dao.StudentDao;
import com.truextend.problem1.entity.ClazzStudent;
import com.truextend.problem1.exception.ClazzNotFoundException;
import com.truextend.problem1.exception.ClazzStudentAlreadyExistsException;
import com.truextend.problem1.exception.ClazzStudentNotFoundException;
import com.truextend.problem1.exception.StudentNotFoundException;
import com.truextend.problem1.utils.Utils;

import java.util.Collection;

/**
 * The type Clazz student service.
 */
public class ClazzStudentService {
    private static ClazzStudentService INSTANCE;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ClazzStudentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClazzStudentService();
        }
        return INSTANCE;
    }

    /**
     * Add clazz student.
     *
     * @param code the code
     * @param id   the id
     * @return the clazz student
     * @throws ClazzNotFoundException             the clazz not found exception
     * @throws StudentNotFoundException           the student not found exception
     * @throws ClazzStudentAlreadyExistsException the clazz student already exists exception
     */
    public ClazzStudent add(String code, Long id) throws ClazzNotFoundException, StudentNotFoundException, ClazzStudentAlreadyExistsException {
        ClazzStudent clazzStudent = new ClazzStudent(Utils.getClazzStudentId(code, id), code, id);
        validateClazzStudent(clazzStudent);
        return ClazzStudentDao.getDao().insert(clazzStudent);
    }

    private void validateClazzStudent(ClazzStudent clazzStudent) throws ClazzNotFoundException, StudentNotFoundException {
        if (ClazzDao.getDao().fetchById(clazzStudent.getClazzCode()) == null) {
            throw new ClazzNotFoundException(clazzStudent.getClazzCode());
        }
        if (StudentDao.getDao().fetchById(clazzStudent.getStudentId()) == null) {
            throw new StudentNotFoundException(clazzStudent.getStudentId());
        }
    }

    /**
     * Delete.
     *
     * @param code the code
     * @param id   the id
     * @throws ClazzStudentNotFoundException the clazz student not found exception
     */
    public void delete(String code, Long id) throws ClazzStudentNotFoundException {
        ClazzStudentDao.getDao().delete(code, id);
    }

    /**
     * Gets by clazz code.
     *
     * @param code the code
     * @return the by clazz code
     */
    public Collection<ClazzStudent> getByClazzCode(String code) {
        return ClazzStudentDao.getDao().fetchByClazzCode(code);
    }

    /**
     * Gets by student id.
     *
     * @param id the id
     * @return the by student id
     */
    public Collection<ClazzStudent> getByStudentId(Long id) {
        return ClazzStudentDao.getDao().fetchByStudentId(id);
    }
}
