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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import static rocks.byivo.ecommercelite.model.ModelValidation.Buy.*;
/**
 *
 * @author byivo
 */
@javax.persistence.Entity
@Table(name = "buys")
public final class Buy extends Entity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "buy_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date buyDate;

    @Column(name = "profit_rate", nullable = false)
    private double profitRate;

    @Column(name = "total_expenses", nullable = false)
    private double totalExpenses;

    @OneToMany(targetEntity=ItemBuy.class, mappedBy="buy", fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch (FetchMode.SELECT)
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
        
        if(this.getProfitRate() < 0) {
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

    @Override
    public String toString() {
        return "Buy{" + "id=" + id + ", buyDate=" + buyDate + ", profitRate=" + profitRate + ", totalExpenses=" + totalExpenses + ", boughtItems=" + boughtItems + '}';
    }
    
    
    
}
