/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.ManyToOne;
import annotation.model.MapToEntity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import service.iml.CategoryGameService;
import service.iml.CategoryService;
import service.iml.GameService;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryGameModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    private int id;
    
    @ManyToOne(getAction = GameService.class)
    @MapToEntity(mapId = "gameId")
    private GameModel game;
    
    @ManyToOne(getAction = CategoryService.class)
    @MapToEntity(mapId = "categoryId")
    private CategoryModel category;
}
