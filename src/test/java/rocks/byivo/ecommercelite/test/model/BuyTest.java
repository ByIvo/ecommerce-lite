/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.test.model;

import java.util.Map;
import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;
import rocks.byivo.ecommercelite.model.Buy;
import rocks.byivo.ecommercelite.model.Item;
import rocks.byivo.ecommercelite.model.ItemBuy;
import static rocks.byivo.ecommercelite.model.ModelValidation.Buy.*;

/**
 *
 * @author byivo
 */
public class BuyTest {

    public BuyTest() {
    }

    @Test
    public void testValidBuy() {
        Buy buy = new Buy();
        buy.setTotalExpenses(600.0);
        buy.setProfitRate(10.0);

        Item item1 = new Item();
        item1.setBoughtPrice(100.0);
        item1.setName("A VALID NAME");

        Item item2 = new Item();
        item2.setBoughtPrice(200.0);
        item2.setName("A VALID NAME");

        Item item3 = new Item();
        item3.setBoughtPrice(300.0);
        item3.setName("A VALID NAME");

        ItemBuy itemBuy1 = new ItemBuy(buy, item1);
        itemBuy1.setItemQnt(1);

        ItemBuy itemBuy2 = new ItemBuy(buy, item2);
        itemBuy2.setItemQnt(1);

        ItemBuy itemBuy3 = new ItemBuy(buy, item3);
        itemBuy3.setItemQnt(1);

        buy.getBoughtItems().add(itemBuy1);
        buy.getBoughtItems().add(itemBuy2);
        buy.getBoughtItems().add(itemBuy3);

        boolean isValid = buy.isValid();

        assertTrue(isValid);

        assertEquals((Double) 330.0, (Double) itemBuy1.getTotalCost());
        assertEquals((Double) 440.0, (Double) itemBuy2.getTotalCost());
        assertEquals((Double) 550.0, (Double) itemBuy3.getTotalCost());
        assertEquals((Double) 1320.0, (Double) buy.getTotalCost());
    }

    @Test
    public void testInvalidItemOnBuy() {
        Buy buy = new Buy();
        buy.setProfitRate(20.0);
        buy.setTotalExpenses(500.0);

        Item item1 = new Item();
        item1.setBoughtPrice(100.0);
        item1.setName("A VALID NAME");

        Item item2 = new Item();
        item2.setBoughtPrice(0.0);
        item2.setName("A VALID NAME2");

        ItemBuy itemBuy1 = new ItemBuy(buy, item1);
        itemBuy1.setItemQnt(2);

        ItemBuy itemBuy2 = new ItemBuy(buy, item2);
        itemBuy2.setItemQnt(3);

        buy.getBoughtItems().add(itemBuy1);
        buy.getBoughtItems().add(itemBuy2);

        boolean isItemBuyValid = itemBuy2.isValid();

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertFalse(isItemBuyValid);
        assertEquals(itemBuy2.getErrors(), errors.get(FIELD_BOUGHT_ITEM));
    }

    @Test
    public void testInvalidBuy() {
        Buy buy = new Buy();
        buy.setBuyDate(null);
        buy.setProfitRate(101.0);
        buy.setTotalExpenses(-100.0);

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BOUGHT_ITEM, errors.get(FIELD_BOUGHT_ITEM));
        assertEquals(INVALID_BUY_DATE, errors.get(FIELD_BUY_DATE));
        assertEquals(INVALID_PROFIT_RATE, errors.get(FIELD_PROFIT_RATE));
        assertEquals(INVALID_TOTAL_EXPENSES, errors.get(FIELD_TOTAL_EXPENSES));
    }

    @Test
    public void testEmptyBuy() {
        Buy buy = new Buy();

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BOUGHT_ITEM, errors.get(FIELD_BOUGHT_ITEM));
    }

    @Test
    public void test10SelectedProductsBuy() {
        Buy buy = new Buy();
        buy.setProfitRate(20.0);
        buy.setTotalExpenses(500.0);

        Item item1 = new Item();
        item1.setBoughtPrice(100.0);
        item1.setName("A VALID NAME");

        Item item2 = new Item();
        item2.setBoughtPrice(200.0);
        item2.setName("A VALID NAME2");

        Item item3 = new Item();
        item3.setBoughtPrice(300.0);
        item3.setName("A VALID NAME3");

        ItemBuy itemBuy1 = new ItemBuy(buy, item1);
        itemBuy1.setItemQnt(2);

        ItemBuy itemBuy2 = new ItemBuy(buy, item2);
        itemBuy2.setItemQnt(3);

        ItemBuy itemBuy3 = new ItemBuy(buy, item3);
        itemBuy3.setItemQnt(5);

        buy.getBoughtItems().add(itemBuy1);
        buy.getBoughtItems().add(itemBuy2);
        buy.getBoughtItems().add(itemBuy3);

        boolean isValid = buy.isValid();

        Double totalCosts = buy.getTotalCost();

        assertTrue(isValid);
        assertEquals((Double) 3360.0, totalCosts);
    }

    @Test
    public void testInvalidProfitRateBuy() {
        Buy buy = new Buy();
        buy.setProfitRate(-10.0);

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_PROFIT_RATE, errors.get(FIELD_PROFIT_RATE));
    }

    @Test
    public void testInvalidOver100ProfitRateBuy() {
        Buy buy = new Buy();
        buy.setProfitRate(100.1);

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_PROFIT_RATE, errors.get(FIELD_PROFIT_RATE));
    }

    @Test
    public void testInvalidTotalExpensesBuy() {

        Buy buy = new Buy();

        Item item1 = new Item();
        item1.setBoughtPrice(300.0);
        item1.setName("A VALID NAME");

        Item item2 = new Item();
        item2.setBoughtPrice(100.0);
        item2.setName("A VALID NAME");

        ItemBuy itemBuy1 = new ItemBuy(buy, item1);
        itemBuy1.setItemQnt(1);

        ItemBuy itemBuy2 = new ItemBuy(buy, item2);
        itemBuy2.setItemQnt(3);

        buy.getBoughtItems().add(itemBuy1);
        buy.getBoughtItems().add(itemBuy2);

        boolean isValid = buy.isValid();

        Double totalCosts = buy.getTotalCost();

        assertTrue(isValid);
        assertNotSame(1000.0, totalCosts);
    }

    @Test
    public void testInvalidTotalExpenses() {
        Buy buy = new Buy();
        buy.setTotalExpenses(-0.1);

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_TOTAL_EXPENSES, errors.get(FIELD_TOTAL_EXPENSES));
    }

    @Test
    public void testInvalidBuyDate() {
        Buy buy = new Buy();
        buy.setBuyDate(null);

        boolean isValid = buy.isValid();
        Map<String, Object> errors = buy.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BUY_DATE, errors.get(FIELD_BUY_DATE));
    }

    @Test
    public void testAverageExpenses() {
        Buy buy = new Buy();
        buy.setTotalExpenses(1000.0);

        Item item1 = new Item();
        item1.setBoughtPrice(300.0);
        item1.setName("A VALID NAME");

        Item item2 = new Item();
        item2.setBoughtPrice(100.0);
        item2.setName("A VALID NAME");

        ItemBuy itemBuy1 = new ItemBuy(buy, item1);
        itemBuy1.setItemQnt(1);

        ItemBuy itemBuy2 = new ItemBuy(buy, item2);
        itemBuy2.setItemQnt(3);

        buy.getBoughtItems().add(itemBuy1);
        buy.getBoughtItems().add(itemBuy2);

        boolean isValid = buy.isValid();

        Double avgExpenses = buy.getAvgExpenses();

        assertTrue(isValid);
        assertEquals((Double) 250.0, avgExpenses);
    }

    @Test
    public void testTotalItemCount() {
        Buy buy = new Buy();

        Item item1 = new Item();
        item1.setBoughtPrice(300.0);
        item1.setName("A VALID NAME");

        Item item2 = new Item();
        item2.setBoughtPrice(100.0);
        item2.setName("A VALID NAME");

        ItemBuy itemBuy1 = new ItemBuy(buy, item1);
        itemBuy1.setItemQnt(5);

        ItemBuy itemBuy2 = new ItemBuy(buy, item2);
        itemBuy2.setItemQnt(8);

        buy.getBoughtItems().add(itemBuy1);
        buy.getBoughtItems().add(itemBuy2);

        boolean isValid = buy.isValid();

        Integer totalItemCount = buy.getTotalItemQnt();

        assertTrue(isValid);
        assertEquals((Integer) 13, totalItemCount);
    }
}
