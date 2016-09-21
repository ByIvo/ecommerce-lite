/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.services;

import java.util.List;
import java.util.Map;
import rocks.byivo.ecommercelite.model.Entity;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
public abstract class GenericService<T extends Entity> implements IBaseActions<T> {

    public abstract IBaseActions<T> getDao();

    @Override
    public Object create(T obj) throws DataAccessException {
        boolean isValid = obj.isValid();

        if (isValid) {
            obj.setId(null);
            T created = (T) this.getDao().create(obj);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        Map<String, Object> invalidMessages = obj.getErrors();
        return new ResponseEntity<>(invalidMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public Object update(Integer id, T obj) throws DataAccessException {
        T old = (T) this.getDao().findById(id);

        if (old == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        old.safeUpdateItself(obj);

        if (old.isValid()) {
            T newObject = (T) this.getDao().update(-1, old);
            return new ResponseEntity<>(newObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(old.getErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Object delete(Integer id) throws DataAccessException {
        T old = (T) this.getDao().findById(id);

        if (old == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.getDao().deleteEntity(old);
        return new ResponseEntity<>(old, HttpStatus.OK);
    }

    @Override
    public Object deleteEntity(T old) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(Integer id) throws DataAccessException {
        T obj = (T) this.getDao().findById(id);

        if (obj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @Override
    public Object list() throws DataAccessException {
        List<T> results = (List<T>) this.getDao().list();

        if (results != null) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
