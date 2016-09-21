/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.byivo.ecommercelite.dao.ItemDao;
import rocks.byivo.ecommercelite.dao.interfaces.IItemDao;
import rocks.byivo.ecommercelite.model.Item;
import rocks.byivo.ecommercelite.services.interfaces.IItemService;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
@Service
@Transactional
public class ItemService extends GenericService<Item> implements IItemService{

    @Autowired
    @Qualifier("itemDao")
    private IBaseActions genericItemDao;
    
    @Autowired
    private IItemDao itemDao;

    @Override
    public IBaseActions<Item> getDao() {
        return this.genericItemDao;
    }

    public Object findSellableItems() {
        List<Item> sellableItems = itemDao.findSellableItems();

        if (sellableItems != null) {
            return new ResponseEntity<>(sellableItems, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
