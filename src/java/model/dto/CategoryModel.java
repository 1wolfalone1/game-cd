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
public class CategoryModel implements Serializable, IModel {

    @Id
    @EqualsAndHashCode.Include
    @MapToEntity(mapId = "id")
    private int id;
    @MapToEntity(mapId = "name")
    private String name;

    @ManyToMany
    @MapToEntity(mapId = "id")
    private List<CategoryGameModel> listGameWithCategorys;
}
