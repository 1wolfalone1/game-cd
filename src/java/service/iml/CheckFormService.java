/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@Getter
@ToString
@Setter
public class CheckFormService {

    String errorName;
    String errorEmail;
    String errorPhone;
    String errorPassword;
    String errorConfirm;

    public boolean check(String name, String mail, String phone, String password, String confirmPass) {
        boolean result = true;
        System.out.println(name + mail + phone + " " +  password +" " +  confirmPass + "                     ;djkshnfds");
        if (name.trim().isEmpty()) {
            errorName = "Name is required!";
            result = false;
        }
        if (!mail.trim().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            errorEmail = "Not a valid email!";
            result = false;
        }
        if (mail.trim().isEmpty()) {
            errorEmail = "Email is required!";
            result = false;

        }

        if (!phone.trim().matches("(84|0[3|5|7|8|9])+([0-9]{7,11})\\b")) {
            errorPhone = "Not a valid phone number!";
            result = false;
        }
        if (phone.trim().isEmpty()) {
            errorPhone = "Phone is required!";
            result = false;
        }

        int tmp = 0;
        if (!password.matches("(?!.*[\\s]).{5,20}")) {
            errorPassword = "Password's length must be between 5 and 20 inclusive!";
            tmp++;
            result = false;
        }
        if (password.trim().isEmpty()) {
            errorPassword = "Password is required!";
            tmp++;
            result = false;
        }
        if (tmp == 0) {
            if (!confirmPass.equals(password)) {
                errorConfirm = "Not match with password!!!";
                result = false;
            }
        }
        if (confirmPass.trim().isEmpty()) {
            errorConfirm = "Need to be confirmed!";
            result = false;
        }

        return result;
    }

    public static void main(String[] args) {
        CheckFormService a = new CheckFormService();
        System.out.println(a.check("1234", "thaaie@gamil.com", "082341234", "12345", "12345"));
        System.out.println(a);

    }
}
