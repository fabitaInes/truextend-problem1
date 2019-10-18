package com.truextend.problem1.dao;

import com.truextend.problem1.entity.Clazz;
import com.truextend.problem1.exception.ClazzAlreadyExistsException;
import com.truextend.problem1.exception.ClazzNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * The type Clazz dao.
 */
public class ClazzDao {
    private static final Logger logger = LogManager.getLogger(ClazzDao.class);

    private static ClazzDao DAO;
    private static ConcurrentMap<String, Clazz> clazzes = new ConcurrentHashMap<>();

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public static ClazzDao getDao() {
        if (DAO == null) {
            DAO = new ClazzDao();
        }
        return DAO;
    }

    /**
     * Fetch all collection.
     *
     * @return the collection
     */
    public Collection<Clazz> fetchAll() {
        logger.info("Fetching all Classes");
        return clazzes.values();
    }

    /**
     * Fetch by id clazz.
     *
     * @param code the code
     * @return the clazz
     * @throws ClazzNotFoundException the clazz not found exception
     */
    public Clazz fetchById(String code) throws ClazzNotFoundException {
        logger.info("Fetching Class by code {}", code);
        Clazz clazz = clazzes.getOrDefault(code, null);
        if (clazz == null) {
            throw new ClazzNotFoundException(code);
        }
        return clazz;
    }

    /**
     * Insert clazz.
     *
     * @param clazz the clazz
     * @return the clazz
     * @throws ClazzAlreadyExistsException the clazz already exists exception
     */
    public Clazz insert(Clazz clazz) throws ClazzAlreadyExistsException {
        logger.info("Inserting Class {}", clazz);

        if (clazzes.containsKey(clazz.getCode())) {
            throw new ClazzAlreadyExistsException(clazz.getCode());
        }
        return clazzes.computeIfAbsent(clazz.getCode(), code -> clazz);
    }

    /**
     * Delete.
     *
     * @param code the code
     * @throws ClazzNotFoundException the clazz not found exception
     */
    public void delete(String code) throws ClazzNotFoundException {
        logger.info("Deleting Class by code {}", code);

        if (!clazzes.containsKey(code)) {
            throw new ClazzNotFoundException(code);
        }
        clazzes.remove(code);
    }

    /**
     * Update clazz.
     *
     * @param clazz the clazz
     * @return the clazz
     * @throws ClazzNotFoundException the clazz not found exception
     */
    public Clazz update(Clazz clazz) throws ClazzNotFoundException {
        logger.info("Updating Class {}", clazz);
        if (!clazzes.containsKey(clazz.getCode())) {
            throw new ClazzNotFoundException(clazz.getCode());
        }
        return clazzes.merge(clazz.getCode(), clazz, (oldClazz, newClazz) -> {
            if (newClazz.getDescription() == null) {
                newClazz.setDescription(oldClazz.getDescription());
            }
            if (newClazz.getTittle() == null) {
                newClazz.setTittle(oldClazz.getTittle());
            }
            return newClazz;
        });
    }

    /**
     * Fetch by criteria collection.
     *
     * @param tittle      the tittle
     * @param description the description
     * @return the collection
     */
    public Collection<Clazz> fetchByCriteria(String tittle, String description) {
        return clazzes.values().stream().filter(clazz -> clazz.getDescription().equals(description) && clazz.getTittle().equals(tittle))
                .collect(Collectors.toList());
    }
}
