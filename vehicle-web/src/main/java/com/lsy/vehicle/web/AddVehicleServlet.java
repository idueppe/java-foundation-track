package com.lsy.vehicle.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lsy.vehicle.controller.VehicleController;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns = "/addvehicle")
public class AddVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private WebApplicationContext ctx;

	private VehicleController controller;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Bitte erstellen Sie ein Formular um ein neues Fahrzeug anlegen zu können.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Bitte die Parameter entgegen nehmen und ein neues Fahrzeug anlegen.
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
	}

}
