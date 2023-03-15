/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import annotation.entity.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGameEntity  implements IEntity{
    @Column("Id")
    private int id;
    @Column("GameId")
    private int gameId;
    @Column("CategoryId")
    private int categoryId;
    
}
