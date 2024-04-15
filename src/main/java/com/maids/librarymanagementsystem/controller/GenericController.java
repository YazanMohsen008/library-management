package com.maids.librarymanagementsystem.controller;
import com.maids.librarymanagementsystem.dao.GenericDao;
import com.maids.librarymanagementsystem.domain.GenericDomain;
import com.maids.librarymanagementsystem.service.GenericService;
import com.maids.librarymanagementsystem.utils.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * The GenericController class is a generic controller that provides basic CRUD operations for a specific domain.
 * It handles HTTP requests and delegates the operations to the corresponding service class.
 *
 * @param <Service> the generic service type that provides business logic for the domain
 * @param <Dao> the generic DAO type that handles database operations for the domain
 * @param <Domain> the generic domain type
 * @param <Id> the generic identifier type for the domain
 */
public class GenericController<Service extends GenericService<Dao, Domain, Id>, Dao extends GenericDao<Domain, Id>, Domain extends GenericDomain<Id>, Id> {
    @Autowired
    protected Service service;
    protected Map<String, Object> error = new HashMap<>();

    /**
     * Inserts a new domain object into the system.
     *
     * @param domain the domain object to insert
     * @param response the HTTP response object
     * @return a ResponseObject containing the result of the operation
     */
    @PostMapping
    public ResponseObject<?> insert(@Valid @RequestBody Domain domain, HttpServletResponse response) {
        try {
            Domain domain1 = service.insert(domain);
            if (domain1 != null) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                return ResponseObject.ADDED_SUCCESS(domain1, null);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return   ResponseObject.ADDING_FAILED(null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            error.put("error", e.getMessage());
            return ResponseObject.ADDING_FAILED(null, error);
        }

    }
    /**
     * Retrieves all domain objects from the system.
     *
     * @param response the HTTP response object
     * @return a ResponseObject containing the list of domain objects
     */
    @GetMapping
    public ResponseObject<?> getAll(HttpServletResponse response) {
        try {
            List<Domain> list = service.getAll();
            Map<String, Object> map = new HashMap<>();
            if (list != null) {
                map.put("count", list.size());
                return ResponseObject.FETCHED_SUCCESS(list, map);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return ResponseObject.FETCHING_FAILED(null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            error.put("error", e.getMessage());
            return ResponseObject.FETCHING_FAILED(null, error);
        }
    }
    /**
     * Retrieves a domain object by its identifier.
     *
     * @param id the identifier of the domain object
     * @param response the HTTP response object
     * @return a ResponseObject containing the retrieved domain object
     */
    @GetMapping(value = "/{id}")
    public ResponseObject<?> getById(@PathVariable Id id, HttpServletResponse response) {
        try {
            Domain domain = service.getById(id);
            if (domain != null)
                return ResponseObject.FETCHED_SUCCESS(domain, null);
            else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return ResponseObject.FETCHING_FAILED(null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            error.put("error", e.getMessage());
            return ResponseObject.FETCHING_FAILED(null, error);
        }
    }

    /**
     * Updates a domain object.
     *
     * @param id the identifier of the domain object to update
     * @param domain the updated domain object
     * @param response the HTTP response object
     * @return a ResponseObject containing the updated domain object
     */
    @PutMapping(value = "/{id}")
    public ResponseObject<?> update(@PathVariable Id id, @RequestBody Domain domain, HttpServletResponse response) {
        try {
            domain.setId(id);
            Domain domain1 = service.update(domain);
            if (domain1 != null)
                return ResponseObject.UPDATED_SUCCESS(domain1, null);
            else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return ResponseObject.UPDATING_FAILED(null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            error.put("error", e.getMessage());
            return ResponseObject.UPDATING_FAILED(null, error);
        }
    }
    /**
     * Deletes a domain object by its identifier.
     *
     * @param id the identifier of the domain object to delete
     * @param response the HTTP response object
     * @return a ResponseObject indicating the success of the delete operation
     */
    @DeleteMapping(value = "/{id}")
    public ResponseObject<?> delete(@PathVariable Id id, HttpServletResponse response) {
        try {
            service.delete(id);
            return ResponseObject.DELETED_SUCCESS(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            error.put("error", e.getMessage());
            return ResponseObject.DELETING_FAILED(true, error);
        }
    }


}
