/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.model;

import static rocks.byivo.ecommercelite.model.ModelValidation.ItemBuy.*;
/**
 *
 * @author byivo
 */
public final class ItemBuy extends Entity{
    
    private Integer id;
    
    private Double boughtPrice;
    
    private Item item;
    
    private Buy buy;
    
    private int itemQnt;

    public ItemBuy() {
    }
    
    public ItemBuy(Buy buy, Item item) {
        this.setItem(item);
        this.buy = buy;
    }
    
    public double getTotalCost() {
        double avgExpenses = this.getBuy().getAvgExpenses();
        double profits = this.getBuy().getProfitRate();
        
        return itemQnt * this.getItem().getFinalPrice(avgExpenses, profits);
    }

    
    @Override
    protected boolean isThisEntityValid() {
        if(this.getItem() == null) {
            this.errors.put(FIELD_ITEM, INVALID_ITEM);
        }else if(!this.getItem().isValid()) {
            this.errors.put(FIELD_ITEM, this.getItem().getErrors());
        }else if(this.getItem().getBoughtPrice() <= 0) {
            this.errors.put(FIELD_ITEM, INVALID_ITEM_BOUGHT_PRICE);
        }
        
         if(this.getBuy() == null) {
            this.errors.put(FIELD_BUY, INVALID_BUY);
        }
         
         if(this.getItemQnt() <= 0) {
            this.errors.put(FIELD_ITEM_QNT, INVALID_ITEM_QNT);
        }
        
        return this.errors.isEmpty();
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    private void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        if(item != null) {
            this.setBoughtPrice(item.getBoughtPrice());
        }
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public int getItemQnt() {
        return itemQnt;
    }

    public void setItemQnt(int itemQnt) {
        this.itemQnt = itemQnt;
    }
    
    
    
    
}
