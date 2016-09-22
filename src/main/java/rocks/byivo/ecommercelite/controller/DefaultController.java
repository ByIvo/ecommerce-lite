/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rocks.byivo.ecommercelite.model.Entity;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
public abstract class DefaultController<T extends Entity> implements IBaseActions<T> {

    public abstract IBaseActions<T> getService();

    @Override
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public Object create(@RequestBody T obj) throws DataAccessException {
        System.out.println(obj.toString());
        return this.getService().create(obj);
    }

    @Override
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    public Object update(@PathVariable(value = "id") Integer id, @RequestBody T obj) throws DataAccessException {
        return this.getService().update(id, obj);
    }

    @Override
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public Object delete(@PathVariable(value = "id") Integer id) throws DataAccessException {
        return this.getService().delete(id);
    }

    @Override
    public Object deleteEntity(T old) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findById(@PathVariable(value = "id") Integer id) throws DataAccessException {
        return this.getService().findById(id);
    }

    @Override
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Object list() throws DataAccessException {
        return this.getService().list();
    }

}
