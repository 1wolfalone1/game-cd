/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
public class CartDTO extends HashMap<GameModel, Integer> {

    public CartDTO() {
    }

    public void addTooCart(GameModel item) {
        if (this.get(item) == null) {
            this.put(item, 1);
        }
//        else {
//            int index = this.get(item);
//            this.put(item, index + 1);
//        }
    }

    public void removeItems(GameModel item) {
        if (this.get(item) == null) {

        } else {
            this.remove(item);
        }
    }

    public void increaseIndexItem(GameModel item) {
        if (this.get(item) == null) {
            this.addTooCart(item);
        } else {
            int index = this.get(item);
            this.put(item, index + 1);
        }
    }

    public void changeQuantity(GameModel item, int quantity) {

            this.put(item, quantity);

    }

    public void decreaseIndexItem(GameModel item) {
        if (this.get(item) == null) {

        } else if (this.get(item) == 1) {
            removeItems(item);
        } else {
            int current = this.get(item);
            this.put(item, current - 1);
        }
    }
}
