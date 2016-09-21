/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import rocks.byivo.ecommercelite.dao.interfaces.IItemDao;
import rocks.byivo.ecommercelite.model.Item;

/**
 *
 * @author byivo
 */
@Repository
public class ItemDao extends GenericDao<Item> implements IItemDao {

    @Override
    public List<Item> findSellableItems() {
        Session session = (Session) this.entityManager.getDelegate();
        Criteria cr = session.createCriteria(this.clazz);

        cr.add(Restrictions.gt("boughtPrice", 0.0));

        return cr.list();
    }
}
