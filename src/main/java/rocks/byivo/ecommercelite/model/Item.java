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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static rocks.byivo.ecommercelite.model.ModelValidation.Item.*;

@javax.persistence.Entity
@Table(name = "items")
public class Item extends Entity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "image", length = 500, nullable = true)
    private String image;

    @Column(name = "bought_price", nullable = false)
    private Double boughtPrice;

    public Item() {
        boughtPrice = .0;
        image = "";
        description = "";
    }

    @Override
    protected boolean isThisEntityValid() {
        if (this.getName() != null) {
            if (this.getName().length() > 50) {
                this.errors.put(FIELD_NAME, NAME_TOO_LONG);
            }
        } else {
            this.errors.put(FIELD_NAME, INVALID_NAME);
        }

        if (this.getDescription() == null) {
            this.errors.put(FIELD_DESCRIPTION, INVALID_DESCRIPTION);
        } else if (this.getDescription().length() > 200) {
            this.errors.put(FIELD_DESCRIPTION, DESCRIPTION_TOO_LONG);
        }

        if (this.getBoughtPrice() != null) {
            if (this.getBoughtPrice() < 0.0) {
                this.errors.put(FIELD_BOUGHT_PRICE, INVALID_BOUGHT_PRICE);
            }
        } else {
            this.errors.put(FIELD_BOUGHT_PRICE, INVALID_BOUGHT_PRICE);
        }

        return this.errors.isEmpty();
    }

    @Override
    public <T extends Entity> void safeUpdateItself(T obj) {
        if (obj instanceof Item) {
            Item newest = (Item) obj;
            
            this.setName(newest.getName());
            this.setImage(newest.getImage());
            this.setDescription(newest.getDescription());
            this.setBoughtPrice(newest.getBoughtPrice());
        }
    }

    public double getFinalPrice(double avgExpenses, double profits) {
        double totalPrice = this.getBoughtPrice() + avgExpenses;
        double totalProfit = totalPrice * profits;
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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", boughtPrice=" + boughtPrice + '}';
    }

}
