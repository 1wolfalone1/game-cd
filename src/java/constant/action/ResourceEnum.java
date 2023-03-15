/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant.action;

import model.dto.UserModel;

/**
 *
 * @author ASUS
 */
public enum ResourceEnum {
    //JSP
    WELCOME_PAGE("views/web/welcome.jsp", RoleEnum.GUEST),
    LOGIN_PAGE("views/web/login.jsp", RoleEnum.GUEST),
    SIGN_UP_PAGE("views/web/registration.jsp", RoleEnum.GUEST),
    PAGE_NOT_FOUND("views/web/page-not-found.jsp", RoleEnum.GUEST),
    SOMETHING_WRONG("views/web/something_wrong.jsp", RoleEnum.GUEST),
    MARKET_PAGE("views/web/market.jsp", RoleEnum.GUEST),
    VIEW_DETAILS_GAME("views/web/details.jsp", RoleEnum.GUEST),
    VIEW_CART("views/web/view-cart.jsp", RoleEnum.GUEST),
    USER_PROFILE_PAGE("views/web/user_profile.jsp", RoleEnum.USER),
    ORDER_PAGE("views/web/order_game.jsp", RoleEnum.GUEST),
    LIST_ORDERS_PAGE("views/web/list-order.jsp", RoleEnum.USER),
    USER_ORDER_DETAILS_PAGE("views/web/user_order_details.jsp", RoleEnum.USER),
    /////amin
    ADMIN_GAME_LIST_PAGE("/views/admin/admin_game_list.jsp", RoleEnum.ADMIN),
    ADMIN_VIEW_GAME_PAGE("/views/admin/admin_view_game.jsp", RoleEnum.ADMIN),
    ADMIN_NEW_GAME_PAGE("/views/admin/admin_new_game.jsp", RoleEnum.ADMIN),
    ADMIN_NEW_CATEGORY_PAGE("/views/admin/admin_category_management.jsp", RoleEnum.ADMIN),
    ADMIN_ORDERS_LIST_PAGE("/views/admin/admin_order_list.jsp", RoleEnum.ADMIN),
    ADMIN_ORDER_DETAILS_PAGE("/views/admin/admin_order_details.jsp", RoleEnum.ADMIN),
    ADMIN_USER_LIST_PAGE("/views/admin/admin_user_list.jsp", RoleEnum.ADMIN),
    ADMIN_ACOUNT_LIST_PAGE("/views/admin/admin_user_account_list.jsp", RoleEnum.ADMIN),
    //SERVLET
    LOGIN("LoginController", RoleEnum.GUEST),
    SIGN_UP("RegistrationController", RoleEnum.GUEST),
    LOGOUT("LogoutController", RoleEnum.GUEST),
    SEARCH_GAME("SearchGameController", RoleEnum.GUEST),
    CART_GAME("CartGameController", RoleEnum.GUEST),
    ORDER("OrderGameController", RoleEnum.GUEST),
    USER_CHANGE("UserChangeController", RoleEnum.USER),
    ORDER_DETAILS_USER("OrderDetailsOfUserController", RoleEnum.USER),
    ORDER_DETAILS_FILTER("OrderDetailsFilterController", RoleEnum.USER),
    ////ADMIN
    ADMIN_GAME("/admin/game/AdminGameController", RoleEnum.ADMIN),
    ADMIN_GAME_CONTROL("/admin/game/AdminGameControlController", RoleEnum.ADMIN),
    ADMIN_GAME_PAGING("/admin/game/AdminGamePagingController", RoleEnum.ADMIN),
    ADMIN_GAME_CHANGE("/admin/game/AdminGameChangeController", RoleEnum.ADMIN),
    ADMIN_GAME_CHANGE_INFO("/admin/game/AdminGameChangeInfoController", RoleEnum.ADMIN),
    ADMIN_GAME_CHANGE_IMAGE("/admin/game/AdminGameChangeImageController", RoleEnum.ADMIN),
    ADMIN_GAME_CREATE("/admin/game/AdminCreateGameController", RoleEnum.ADMIN),
    ADMIN_GAME_CATEGORY("/admin/game/AdminCategoryController", RoleEnum.ADMIN),
    ADMIN_ORDER_PAGING("/admin/order/AdminOrderPagingController", RoleEnum.ADMIN),
    ADMIN_ORDER_ACTION("/admin/order/AdminOrderActionController", RoleEnum.ADMIN), //folder
    ADMIN_USER_PAGING("/admin/user/AdminUserPaging", RoleEnum.ADMIN), //folder
    ADMIN_USER_ACCOUNT_PAGING("/admin/user/AdminUserAccountPaging", RoleEnum.ADMIN),
    ADMIN_USER_ACTION("/admin/user/AdminUserActionController", RoleEnum.ADMIN), 
    ;

    private ResourceEnum(String resource, RoleEnum role) {
        this.resource = resource;
        this.role = role;
    }

    private ResourceEnum(String resource, RoleEnum role, UserModel model) {
        this.model = model;
        this.resource = resource;
        this.role = role;
    }
    private String resource;
    private RoleEnum role;

    public static ResourceEnum getLOGIN_PAGE() {
        return LOGIN_PAGE;
    }

    public int getRole() {
        return role.getRole();
    }

    public String getResource() {
        return resource;
    }

    private UserModel model;
}
