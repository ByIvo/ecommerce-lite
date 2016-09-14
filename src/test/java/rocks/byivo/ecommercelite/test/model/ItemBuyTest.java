/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.test.model;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import rocks.byivo.ecommercelite.model.Buy;
import rocks.byivo.ecommercelite.model.Item;
import rocks.byivo.ecommercelite.model.ItemBuy;
import static rocks.byivo.ecommercelite.model.ModelValidation.ItemBuy.*;

/**
 *
 * @author byivo
 */
public class ItemBuyTest {
    
    @Test
    public void testMultiplesErrors() {
        ItemBuy itemBuy = new ItemBuy();
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BUY, errors.get(FIELD_BUY));
        assertEquals(INVALID_ITEM, errors.get(FIELD_ITEM));
        assertEquals(INVALID_ITEM_QNT, errors.get(FIELD_ITEM_QNT));
    }
    
    @Test
    public void testInvalidItem() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        
        itemBuy.setItemQnt(1);
        itemBuy.setBuy(buy);
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_ITEM, errors.get(FIELD_ITEM));
    }
    
    @Test
    public void testInvalidItemName() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        
        Item item = new Item();
        item.setName(null);
        
        itemBuy.setItemQnt(1);
        itemBuy.setBuy(buy);
        itemBuy.setItem(item);
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();
         
         boolean itemInvalid = item.isValid();
         Map<String, Object> itemErrors = item.getErrors();

        assertFalse(isValid);
        assertFalse(itemInvalid);
        assertEquals(itemErrors, errors.get(FIELD_ITEM));
    }
    
    @Test
    public void testInvalidItemPrice() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        
        itemBuy.setItemQnt(1);
        itemBuy.setBuy(buy);
        itemBuy.setItem(item);
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_ITEM_BOUGHT_PRICE, errors.get(FIELD_ITEM));
    }
    
    @Test
    public void testInvalidBuy() {
        ItemBuy itemBuy = new ItemBuy();
    
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        
        itemBuy.setItemQnt(1);
        itemBuy.setItem(item);
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BUY, errors.get(FIELD_BUY));
    }
    
    @Test
    public void testInvalidItemQuantity() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        
        itemBuy.setItemQnt(0);
        itemBuy.setBuy(buy);
        itemBuy.setItem(item);
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_ITEM_QNT, errors.get(FIELD_ITEM_QNT));
    }
    
    @Test
    public void testValidItemBuy() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        item.setBoughtPrice(200.0);
        
        itemBuy.setItemQnt(1);
        itemBuy.setBuy(buy);
        itemBuy.setItem(item);
        
        boolean isValid = itemBuy.isValid();
         Map<String, Object> errors = itemBuy.getErrors();

        assertTrue(isValid);
        assertTrue(errors.isEmpty());
    }
    
    @Test
    public void testItemBuyTotalCostDefaultValues() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        item.setBoughtPrice(200.0);
        
        itemBuy.setItemQnt(2);
        itemBuy.setBuy(buy);
        itemBuy.setItem(item);
        
        buy.getBoughtItems().add(itemBuy);
        
        boolean isValid = itemBuy.isValid();
        
        Double totalCost = itemBuy.getTotalCost();

        assertTrue(isValid);
        assertEquals((Double)800.0, totalCost);
    }
    
     @Test
    public void testItemBuyTotalCost() {
        ItemBuy itemBuy = new ItemBuy();
        
        Buy buy = new Buy();
        buy.setProfitRate(20.0);
        buy.setTotalExpenses(900.0);
        
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        item.setBoughtPrice(100.0);
        
        itemBuy.setItemQnt(3);
        itemBuy.setBuy(buy);
        itemBuy.setItem(item);
        
        buy.getBoughtItems().add(itemBuy);
        
        boolean isValid = itemBuy.isValid();
        
        Double totalCost = itemBuy.getTotalCost();

        assertTrue(isValid);
        assertEquals((Double)1440.0, totalCost);
    }
    
}
