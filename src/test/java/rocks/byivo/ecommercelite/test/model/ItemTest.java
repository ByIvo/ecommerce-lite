/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.test.model;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import rocks.byivo.ecommercelite.model.Item;
import static rocks.byivo.ecommercelite.model.ModelValidation.Item.*;

/**
 *
 * @author byivo
 */
public class ItemTest {

    public ItemTest() {
    }

    @Test
    public void testValidItem() {
        Item item = new Item();
        item.setName("Random Valid Name");
        item.setBoughtPrice(200.0);

        boolean isValid = item.isValid();

        assertTrue(isValid);
        assertTrue(item.getErrors().isEmpty());
    }

    @Test
    public void testInvalidName() {
        Item item = new Item();
        item.setName(null);
        item.setBoughtPrice(200.0);

        boolean isValid = item.isValid();
        Map<String, Object> errors = item.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_NAME, errors.get(FIELD_NAME));
    }

    @Test
    public void testNameTooLong() {
        Item item = new Item();
        item.setName("A LONG NAME... TOO LONGER TO BE ACCEPTED BY THIS SYSTEM, SO PLEASE, CHANGE IT");
        item.setBoughtPrice(200.0);

        boolean isValid = item.isValid();
        Map<String, Object> errors = item.getErrors();

        assertFalse(isValid);
        assertEquals(NAME_TOO_LONG, errors.get(FIELD_NAME));
    }
    
    @Test
    public void testDescriptionTooLong() {
        Item item = new Item();
        item.setName("VALID NAME");
        item.setBoughtPrice(200.0);
        item.setDescription("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        boolean isValid = item.isValid();
        Map<String, Object> errors = item.getErrors();

        assertFalse(isValid);
        assertEquals(DESCRIPTION_TOO_LONG, errors.get(FIELD_DESCRIPTION));
    }

    @Test
    public void testInvalidNegativeBoughtPrice() {
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        item.setBoughtPrice(-100.0);

        boolean isValid = item.isValid();
        Map<String, Object> errors = item.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BOUGHT_PRICE, errors.get(FIELD_BOUGHT_PRICE));
    }

    @Test
    public void testMultipleErrors() {
        Item item = new Item();
        item.setName("A LONG NAME... TOO LONGER TO BE ACCEPTED BY THIS SYSTEM, SO PLEASE, CHANGE IT");
        item.setBoughtPrice(-100.0);

        boolean isValid = item.isValid();
        Map<String, Object> errors = item.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BOUGHT_PRICE, errors.get(FIELD_BOUGHT_PRICE));
        assertEquals(NAME_TOO_LONG, errors.get(FIELD_NAME));
    }

    @Test
    public void testMultipleErrors2() {
        Item item = new Item();
        item.setName(null);
        item.setBoughtPrice(-0.1);

        boolean isValid = item.isValid();
        Map<String, Object> errors = item.getErrors();

        assertFalse(isValid);
        assertEquals(INVALID_BOUGHT_PRICE, errors.get(FIELD_BOUGHT_PRICE));
        assertEquals(INVALID_NAME, errors.get(FIELD_NAME));
    }

    @Test
    public void testFinalPriceNoProfits() {
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        item.setBoughtPrice(200.0);

        boolean isValid = item.isValid();
        double profits = .0;
        double avgExpenses = 400.0;

        Double sellPrice = item.getFinalPrice(avgExpenses, profits);

        assertTrue(isValid);
        assertEquals((Double) 600.0, sellPrice);
    }

    @Test
    public void testFinalPrice() {
        Item item = new Item();
        item.setName("A RANDOM VALID NAME");
        item.setBoughtPrice(200.0);

        boolean isValid = item.isValid();
        double profits = 0.1;
        double avgExpenses = 400.0;

        Double sellPrice = item.getFinalPrice(avgExpenses, profits);

        assertTrue(isValid);
        assertEquals((Double) 660.0, sellPrice);
    }
}
