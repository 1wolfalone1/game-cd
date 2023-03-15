/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant.action;

/**
 *
 * @author ASUS
 */
public enum RoleEnum {

    USER(0), ADMIN(1), GUEST(-1);

    private RoleEnum(int role) {
        this.role = role;
    }
    private final int role;

    public int getRole() {
        return role;
    }

}
