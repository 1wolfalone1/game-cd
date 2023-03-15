/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.MapToEntity;
import annotation.model.OneToMany;
import java.io.Serializable;
import java.util.List;
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
public class UserModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    private int id;
    @MapToEntity(mapId = "fullName")
    private String fullName;
    @MapToEntity(mapId = "phone")
    private String phone;
    @MapToEntity(mapId = "address")
    private String address;

    @OneToMany
    @MapToEntity(mapId = "userId")
    private List<OrderModel> listOrder;
}
