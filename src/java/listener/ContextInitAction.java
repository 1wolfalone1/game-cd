/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ASUS
 */
public class ContextInitAction implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HashMap<String, ResourceEnum> map = new HashMap();
        ServletContext context = sce.getServletContext();
        Set<ActionEnum> set = EnumSet.allOf(ActionEnum.class);
        for (ActionEnum enumEntity : set) {
            map.put(enumEntity.getKey(), enumEntity.getResource());
        }
        context.setAttribute("map", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
