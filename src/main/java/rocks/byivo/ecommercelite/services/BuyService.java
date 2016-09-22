/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.byivo.ecommercelite.dao.interfaces.IBuyDao;
import rocks.byivo.ecommercelite.dao.interfaces.IItemDao;
import rocks.byivo.ecommercelite.model.Buy;
import rocks.byivo.ecommercelite.model.Item;
import rocks.byivo.ecommercelite.model.ItemBuy;
import rocks.byivo.ecommercelite.services.interfaces.IBuyService;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
@Service
@Transactional
public class BuyService extends GenericService<Buy> implements IBuyService{

    @Autowired
    @Qualifier("buyDao")
    private IBaseActions genericBuyDao;
    
    @Autowired
    @Qualifier("itemDao")
    private IBaseActions genericItemDao;

    @Override
    public IBaseActions<Buy> getDao() {
        return this.genericBuyDao;
    }

    @Override
    public Object create(Buy obj) throws DataAccessException {
        for(ItemBuy itemBuy : obj.getBoughtItems()) {
            Item item = (Item) genericItemDao.findById(itemBuy.getItem().getId());
            itemBuy.setItem(item);
            itemBuy.setBuy(obj);
        }
        
        return super.create(obj);
    }

    @Override
    public Object update(Integer id, Buy obj) throws DataAccessException {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
    
}
