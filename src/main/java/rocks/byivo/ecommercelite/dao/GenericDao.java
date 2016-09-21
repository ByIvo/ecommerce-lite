/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.dao;

import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import rocks.byivo.ecommercelite.model.Entity;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
public class GenericDao<T extends Entity> implements IBaseActions<T> {

    protected final Class<?> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public Object create(T obj) throws DataAccessException {
        this.entityManager.persist(obj);
        return obj;
    }

    @Override
    @Transactional
    public Object update(Integer id, T obj) throws DataAccessException {
        this.entityManager.merge(obj);
        return obj;
    }

    @Override
    public Object delete(Integer id) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Object deleteEntity(T old) throws DataAccessException {
        old = (T) this.entityManager.getReference(this.clazz, old.getId());
        
        this.entityManager.remove(old);
        return old;
    }

    @Override
    public Object findById(Integer id) throws DataAccessException {
        return this.entityManager.find(this.clazz, id);
    }

    @Override
    @ResponseBody
    @Transactional
    public Object list() throws DataAccessException {
        Session session = (Session) this.entityManager.getDelegate();
        Criteria cr = session.createCriteria(this.clazz);
        return cr.list();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
