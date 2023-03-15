/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import annotation.entity.Column;
import java.sql.Date;
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
public class OrderEntity  implements IEntity{
    @Column("Id")
    private int id;
    @Column("OrdDate")
    private Date ordDate;
    @Column("shipdate")
    private Date shipDate;
    @Column("status")
    private int status;
    @Column("UserId")
    private int userId;
}
