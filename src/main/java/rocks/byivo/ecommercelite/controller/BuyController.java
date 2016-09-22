/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocks.byivo.ecommercelite.model.Buy;
import rocks.byivo.ecommercelite.services.interfaces.IBuyService;
import rocks.byivo.ecommercelite.util.IBaseActions;

/**
 *
 * @author byivo
 */
@RestController
@RequestMapping(value = "/buys")
public class BuyController extends DefaultController<Buy> {

    @Autowired
    @Qualifier("buyService")
    private IBaseActions genericBuyService;

    @Autowired
    private IBuyService buyService;

    @Override
    public IBaseActions<Buy> getService() {
        return this.genericBuyService;
    }

}
