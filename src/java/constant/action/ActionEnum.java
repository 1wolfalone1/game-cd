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
public enum ActionEnum {
    //jsp
    WELCOME_PAGE("", ResourceEnum.WELCOME_PAGE),
    LOGIN_PAGE("login-page", ResourceEnum.LOGIN_PAGE),
    SIGN_UP_PAGE("sign-up-page", ResourceEnum.SIGN_UP_PAGE),
    PAGE_NOT_FOUND("page-not-found", ResourceEnum.PAGE_NOT_FOUND),
    SOMETHING_WRONG("something-wrong", ResourceEnum.SOMETHING_WRONG),
    MARKET_PAGE("market-page", ResourceEnum.MARKET_PAGE),
    VIEW_DETAILS("details-game", ResourceEnum.VIEW_DETAILS_GAME),
    VIEW_CART("view-cart", ResourceEnum.VIEW_CART),
    USER_PROFILE_PAGE("profile", ResourceEnum.USER_PROFILE_PAGE),
    LIST_ORDER("order-list", ResourceEnum.LIST_ORDERS_PAGE),
    ////admin
    FIRST_PAGE("admin", ResourceEnum.ADMIN_GAME_LIST_PAGE),
    ADMIN_FIRST_ORDER_PAGE("orders", ResourceEnum.ADMIN_ORDERS_LIST_PAGE),
    ADMIN_FIRST_USER_PAGE("user-management", ResourceEnum.ADMIN_USER_LIST_PAGE),
    //servlet
    SIGN_UP("sign-up", ResourceEnum.SIGN_UP),
    LOGIN("login", ResourceEnum.LOGIN),
    LOGOUT("logout", ResourceEnum.LOGOUT),
    SEARCH_GAME("search-game", ResourceEnum.SEARCH_GAME),
    CART_GAME("cart-game", ResourceEnum.CART_GAME),
    ORDER("order", ResourceEnum.ORDER),
    USER_CHANGE("change-profile", ResourceEnum.USER_CHANGE),
    USER_DETAIL_ORDER("order-details", ResourceEnum.ORDER_DETAILS_USER),
    USER_DETAIL_FILTER("order-details-filter", ResourceEnum.ORDER_DETAILS_FILTER),
    ////admin
    ADMIN_GAME("game-management", ResourceEnum.ADMIN_GAME),
    ADMIN_GAME_CONTROL("admin-game-control", ResourceEnum.ADMIN_GAME_CONTROL),
    ADMIN_GAME_PAGING("admin-game-paging", ResourceEnum.ADMIN_GAME_PAGING),
    ADMIN_GAME_CHANGE_INFO("admin-game-change-info", ResourceEnum.ADMIN_GAME_CHANGE_INFO),
    ADMIN_GAME_CHANGE_IMG("admin-game-change-img", ResourceEnum.ADMIN_GAME_CHANGE_IMAGE),
    ADMIN_GAME_CREATE("admin-game-create-game", ResourceEnum.ADMIN_GAME_CREATE),
    ADMIN_GAME_CATEGORY("admin-category", ResourceEnum.ADMIN_GAME_CATEGORY),
    ADMIN_ORDER_PAGING("admin-order-paging", ResourceEnum.ADMIN_ORDER_PAGING),
    ADMIN_ORDER_ACTION("admin-order-action", ResourceEnum.ADMIN_ORDER_ACTION),
    ADMIN_USER_PAGING("admin-user-search", ResourceEnum.ADMIN_USER_PAGING),
    ADMIN_USER_ACCOUNT_PAGING("admin-account-search", ResourceEnum.ADMIN_USER_ACCOUNT_PAGING),
    ADMIN_USER_ACTION("admin-user-action", ResourceEnum.ADMIN_USER_ACTION),
    //folder
    ;

    private ActionEnum(String key, ResourceEnum resource) {
        this.key = key;
        this.resource = resource;
    }

    private final String key;
    private final ResourceEnum resource;

    public String getKey() {
        return key;
    }

    public ResourceEnum getResource() {
        return resource;
    }

}
