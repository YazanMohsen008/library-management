package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.log.Log;
import com.maids.librarymanagementsystem.dao.GenericDao;
import com.maids.librarymanagementsystem.domain.GenericDomain;
import com.maids.librarymanagementsystem.utils.model.ActionType;
import com.maids.librarymanagementsystem.utils.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.*;

public abstract class GenericService<Dao extends GenericDao, Domain extends GenericDomain, IdClass> {
    @Autowired
    protected Dao dao;
//    @Autowired
//    protected PaginationService paginationService;

    @Log(actionType = ActionType.ADD)
    public Domain insert(Domain domain) throws Exception {
        return ((JpaRepository<Domain, IdClass>) dao).save(domain);
    }


    public Domain getById(IdClass id) {
        Optional<Domain> result = dao.findById(id);
        return result != null && result.isPresent() ? result.get() : null;
    }


    public List<Domain> getAll() {
        return dao.findAll();
    }

    @Log(actionType = ActionType.UPDATE)
    public Domain update(Domain newDomain) {
        Domain oldDomain = this.getById((IdClass) newDomain.getId());
        mergeObjects(oldDomain, newDomain);
        return ((JpaRepository<Domain, IdClass>) dao).save(oldDomain);
    }

    private void mergeObjects(Domain old, Domain newOne) {
        List<Field> allFields = getAllFields(newOne.getClass());
        allFields.forEach(f -> {
            f.setAccessible(true);
            try {
                if (f.get(newOne) != null) {
                    f.set(old, f.get(newOne));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Field> getAllFields(Class clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }
        List<Field> result = new ArrayList<>(getAllFields(clazz.getSuperclass()));
        List<Field> filteredFields = Arrays.asList(clazz.getDeclaredFields());
        result.addAll(filteredFields);
        return result;
    }

    @Log(actionType = ActionType.DELETE)
    public void delete(IdClass id) {
        dao.deleteById(id);
    }



}
