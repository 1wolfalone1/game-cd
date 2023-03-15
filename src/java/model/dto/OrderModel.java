/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.ManyToMany;
import annotation.model.ManyToOne;
import annotation.model.MapToEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import service.iml.UserService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    private int id;

    @MapToEntity(mapId = "ordDate")
    private Date ordDate;

    @MapToEntity(mapId = "shipDate")
    private Date shipDate;

    @MapToEntity(mapId = "status")
    private int status;

    @ManyToOne(getAction = UserService.class)
    @MapToEntity(mapId = "userId")
    private UserModel user;

    @ManyToMany
    @MapToEntity(mapId = "orderId")
    private List<GameModel> listGameOfOrder;

    public String getStringStatus() {
        String status = "";
        if (this.status == 1) {
            status = "processing";
        } else if (this.status == 2) {
            status = "completed";
        } else if (this.status == 3) {
            status = "canceled";
        }
        return status;
    }
    private int totalPrice;

    public String getStringDate(Date date) {
        String a = MyUtils.convertDateToString(date, "MM/dd/yyyy");
        return a;
    }
    public String getStringBrowserDate(Date date) {
        String a = MyUtils.convertDateToString(date, "yyyy-MM-dd");
        return a;
    }
}
