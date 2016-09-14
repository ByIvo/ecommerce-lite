/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.byivo.ecommercelite.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author byivo
 */
public abstract class Entity implements Serializable{
    
    public abstract Integer getId();
    public abstract void setId(Integer id);
    protected abstract boolean isThisEntityValid();
    
    protected Map<String, Object> errors;

    public Entity() {
        errors = new HashMap<>();
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
    
    public boolean isValid() {
        this.errors.clear();
        return this.isThisEntityValid();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        
        return Objects.equals(this.getId(), other.getId());
    }
    
    
}
