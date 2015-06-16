package com.freetmp.investigate.vaadin;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by LiuPin on 2015/6/16.
 */
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = HelloWorld.class)
public class MainServlet extends VaadinServlet {

}
