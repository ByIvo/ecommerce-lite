/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.model;

/**
 *
 * @author byivo
 */
import static rocks.byivo.ecommercelite.model.ModelValidation.Item.*;

public class Item extends Entity {

    private Integer id;

    private String name;

    private String description;

    private String image;

    private Double boughtPrice;

    public Item() {
        boughtPrice = .0;
        image = "";
        description = "";
    }

    @Override
    protected boolean isThisEntityValid() {
        if(this.getName() != null) {
            if(this.getName().length() > 50) {
                this.errors.put(FIELD_NAME, NAME_TOO_LONG);
            }
        }else {
            this.errors.put(FIELD_NAME, INVALID_NAME);
        }
        
        if(this.getBoughtPrice() != null) {
            if(this.getBoughtPrice() < 0.0) {
                this.errors.put(FIELD_BOUGHT_PRICE, INVALID_BOUGHT_PRICE);
            }
        }else {
            this.errors.put(FIELD_BOUGHT_PRICE, INVALID_BOUGHT_PRICE);
        }
        
        return this.errors.isEmpty();
    }

    public double getFinalPrice(double avgExpenses, double profits) {
        double totalPrice = this.getBoughtPrice() + avgExpenses;
        double totalProfit = totalPrice  * (profits / 100);
        return totalPrice + totalProfit;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
