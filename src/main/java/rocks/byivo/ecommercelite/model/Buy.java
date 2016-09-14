/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static rocks.byivo.ecommercelite.model.ModelValidation.Buy.*;
/**
 *
 * @author byivo
 */
public final class Buy extends Entity {

    private Integer id;

    private Date buyDate;

    private double profitRate;

    private double totalExpenses;

    private List<ItemBuy> boughtItems;

    public Buy() {
        totalExpenses = 400.0;
        profitRate = .0;
        buyDate = new Date();
        
        boughtItems = new ArrayList<>();
    }

    @Override
    protected boolean isThisEntityValid() {
             
        if(this.getBoughtItems().isEmpty()) {
            this.errors.put(FIELD_BOUGHT_ITEM, INVALID_BOUGHT_ITEM);
        }else {
            for(ItemBuy itemBuy : boughtItems) {
                boolean itemBuyValidation = itemBuy.isValid();
                
                if(!itemBuyValidation) {
                    this.errors.put(FIELD_BOUGHT_ITEM, itemBuy.getErrors());
                    break;
                }
            }
        }
        
        if(this.getProfitRate() < 0 || this.getProfitRate() > 100) {
             this.errors.put(FIELD_PROFIT_RATE, INVALID_PROFIT_RATE);
        }
        
        if(this.getTotalExpenses() <= 0) {
            this.errors.put(FIELD_TOTAL_EXPENSES, INVALID_TOTAL_EXPENSES);
        }
        
        if(this.getBuyDate() == null) {
            this.errors.put(FIELD_BUY_DATE, INVALID_BUY_DATE);
        }
        
        return this.errors.isEmpty();
    }

    public double getAvgExpenses() {
        return this.getTotalExpenses() / this.getTotalItemQnt();
    }

    public double getTotalCost() {
        double totalCost = 0.0;

        for (ItemBuy itemBuy : boughtItems) {
            totalCost += itemBuy.getTotalCost();
        }

        return totalCost;
    }

    public int getTotalItemQnt() {
        int totalItems = 0;

        for (ItemBuy itemBuy : boughtItems) {
            totalItems += itemBuy.getItemQnt();
        }

        return totalItems;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    @JsonIgnore
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public List<ItemBuy> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<ItemBuy> boughtItems) {
        this.boughtItems = boughtItems;
    }
}
