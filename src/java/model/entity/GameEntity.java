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
public class GameEntity  implements IEntity{
    @Column("Id")
    private int id;
    @Column("Name")
    private String name;
    @Column("price")
    private int price;
    @Column("imgPath")
    private String imgPath;
    @Column("description")
    private String description;
    @Column("quantity")
    private int quantity;
    @Column("status")
    private int status;
}
