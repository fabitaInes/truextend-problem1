package com.truextend.problem1.service;

import com.truextend.problem1.dao.ClazzDao;
import com.truextend.problem1.entity.Clazz;
import com.truextend.problem1.entity.ClazzStudent;
import com.truextend.problem1.exception.ClazzAlreadyExistsException;
import com.truextend.problem1.exception.ClazzFieldsException;
import com.truextend.problem1.exception.ClazzNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Clazz service.
 */
public class ClazzService {
    private static ClazzService INSTANCE;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ClazzService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClazzService();
        }
        return INSTANCE;
    }

    /**
     * Select all collection.
     *
     * @return the collection
     */
    public Collection<Clazz> selectAll() {
        return ClazzDao.getDao().fetchAll();
    }

    /**
     * Select by code clazz.
     *
     * @param code the code
     * @return the clazz
     * @throws ClazzNotFoundException the clazz not found exception
     */
    public Clazz selectByCode(String code) throws ClazzNotFoundException {
        return ClazzDao.getDao().fetchById(code);
    }

    /**
     * Add clazz.
     *
     * @param clazz the clazz
     * @return the clazz
     * @throws ClazzAlreadyExistsException the clazz already exists exception
     * @throws ClazzFieldsException        the clazz fields exception
     */
    public Clazz add(Clazz clazz) throws ClazzAlreadyExistsException, ClazzFieldsException {
        validateClazz(clazz);
        return ClazzDao.getDao().insert(clazz);
    }

    private void validateClazz(Clazz clazz) throws ClazzFieldsException {
        if (clazz.getCode() == null) {
            throw new ClazzFieldsException("Clazz Code cannot be null");
        }
        if (clazz.getTittle() == null) {
            throw new ClazzFieldsException("Clazz Tittle cannot be null");
        }
        if (clazz.getDescription() == null) {
            throw new ClazzFieldsException("Clazz Description cannot be null");
        }
    }

    /**
     * Delete.
     *
     * @param Code the code
     * @throws ClazzNotFoundException the clazz not found exception
     */
    public void delete(String Code) throws ClazzNotFoundException {
        ClazzDao.getDao().delete(Code);
    }

    /**
     * Update clazz.
     *
     * @param code  the code
     * @param clazz the clazz
     * @return the clazz
     * @throws ClazzNotFoundException the clazz not found exception
     */
    public Clazz update(String code, Clazz clazz) throws ClazzNotFoundException {
        clazz.setCode(code);
        return ClazzDao.getDao().update(clazz);
    }

    /**
     * Gets by student id.
     *
     * @param id the id
     * @return the by student id
     */
    public Collection<Clazz> getByStudentId(Long id) {
        List<String> clazzCodes = ClazzStudentService.getInstance().getByStudentId(id).stream().map(ClazzStudent::getClazzCode).collect(Collectors.toList());
        return ClazzDao.getDao().fetchAll().stream().filter(clazz -> clazzCodes.contains(clazz.getCode())).collect(Collectors.toList());
    }

    /**
     * Select by cristeria collection.
     *
     * @param tittle      the tittle
     * @param description the description
     * @return the collection
     */
    public Collection<Clazz> selectByCristeria(String tittle, String description) {
        return ClazzDao.getDao().fetchByCriteria(tittle, description);
    }
}
