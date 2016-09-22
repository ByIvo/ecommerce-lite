/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.dao;

import org.springframework.stereotype.Repository;
import rocks.byivo.ecommercelite.dao.interfaces.IBuyDao;
import rocks.byivo.ecommercelite.model.Buy;

/**
 *
 * @author byivo
 */
@Repository
public class BuyDao extends GenericDao<Buy> implements IBuyDao {

}
