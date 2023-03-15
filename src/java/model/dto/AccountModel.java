/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.ManyToMany;
import annotation.model.MapToEntity;
import annotation.model.OneToOne;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import service.iml.UserService;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    private int id;
    
    @MapToEntity(mapId = "email")
    private String email;
    
    @MapToEntity(mapId = "password")
    private String password;
    
    @MapToEntity(mapId = "avatarPath")
    private String avatarPath;
    
    @MapToEntity(mapId = "userId")
    @OneToOne(getAction = UserService.class)
    private UserModel user;
    
    @MapToEntity(mapId = "token")
    private String token;
    
    @MapToEntity(mapId = "status")
    private int status;
    
    @MapToEntity(mapId = "role")
    private int role;
    
  
}
