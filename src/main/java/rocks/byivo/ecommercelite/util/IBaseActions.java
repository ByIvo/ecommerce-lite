/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.util;

import java.io.Serializable;
import org.springframework.dao.DataAccessException;
import rocks.byivo.ecommercelite.model.Entity;

/**
 *
 * @author byivo
 */
public interface IBaseActions<T extends Entity> extends Serializable {

    public Object create(T obj) throws DataAccessException;

    public Object update(Integer id, T obj) throws DataAccessException;

    public Object delete(Integer id) throws DataAccessException;
    
    public Object deleteEntity(T old) throws DataAccessException;

    public Object findById(Integer id) throws DataAccessException;

    public Object list() throws DataAccessException;

}
