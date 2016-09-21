/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rocks.byivo.ecommercelite.model.Item;
import rocks.byivo.ecommercelite.services.interfaces.IItemService;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
@RestController
@RequestMapping(value = "/items")
public class ItemController extends DefaultController<Item> {

    @Autowired
    @Qualifier("itemService")
    private IBaseActions genericItemService;

    @Autowired
    private IItemService itemService;

    @Override
    public IBaseActions<Item> getService() {
        return this.genericItemService;
    }

    @RequestMapping(value = "/sellable", method = RequestMethod.GET)
    public Object findSellableItems() {
        return itemService.findSellableItems();
    }

}
