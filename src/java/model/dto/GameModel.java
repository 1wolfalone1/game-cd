/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.ManyToMany;
import annotation.model.MapToEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GameModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    @EqualsAndHashCode.Include
    private int id;
    @MapToEntity(mapId = "name")
    private String name;
    @MapToEntity(mapId = "price")
    private int price;
    @MapToEntity(mapId = "imgPath")
    private String imgPath;
    @MapToEntity(mapId = "description")
    private String description;
    @MapToEntity(mapId = "quantity")
    private int quantity;
    @MapToEntity(mapId = "status")
    private int status;

    @MapToEntity(mapId = "gameId")
    @ManyToMany
    private List<CategoryModel> listCategory = new ArrayList<>();

    @MapToEntity(mapId = "gameId")
    @ManyToMany
    private List<OrderModel> listOrderWithGame = new ArrayList<>();

    private List<String> imgPaths = new ArrayList<>();

    public void removeImg(String img) {
        this.imgPath = this.imgPath.replace(img, "");
        this.imgPath = this.imgPath.replace("::", ":");
        if (this.imgPath.charAt(0) == ':') {
            this.imgPath = this.imgPath.substring(1, this.imgPath.length());
        } else if (this.imgPath.charAt(this.imgPath.length() - 1) == ':') {
            this.imgPath = this.imgPath.substring(0, this.imgPath.length() - 1);
        }
    }

    public void addImg(String img) {
        if (this.imgPath == null || this.imgPath.trim().isEmpty()) {
            this.imgPath = img;
        } else {
            img = ":" + img;
            this.imgPath = this.imgPath + img;
        }
    }

    public void setListImg() {
        if (imgPath != null) {
            String[] imgPathArray = this.imgPath.split(":");
            for (String string : imgPathArray) {
                this.imgPaths.add(string);
            }
        }
    }

    public static void main(String[] args) {
        GameModel model1 = new GameModel();
        model1.addImg("Epxi-I.png");
        System.out.println(model1);
        model1.addImg("Epxi-I.png");
        System.out.println(model1);

    }
}
