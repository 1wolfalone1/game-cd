/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

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
public class CartMessageDTO {
    private String title;
    private String message;
    private String type;
    private int status;
        
    public void setMegSuccessFul(String mesString){
        this.title = "Successful";
        this.message = mesString;
        this.type = "success";
        this.status = 1;
    }
    
    public void setReject(String mesString){
        this.title = "Rejected";
        this.message = mesString;
        this.type = "reject";
        this.status = 0;
    }
    
    public void setWrong(String mesString){
        this.title = "Error";
        this.message = mesString;
        this.type = "wrong";
        this.status = -1;
    }
}
