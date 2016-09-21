/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.dao.interfaces;

import java.util.List;
import rocks.byivo.ecommercelite.model.Item;

/**
 *
 * @author byivo
 */
public interface IItemDao {

    public List<Item> findSellableItems();
}
