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
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.web.util.HtmlWriter;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns="/vehicles")
public class VehicleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private WebApplicationContext ctx;
	
	private VehicleController controller;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manufacturerName = request.getParameter("manufacturer");

		response.setContentType("text/html");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());
		html.beginHtml().defaultHeader().beginMain();
		printVehicleList(manufacturerName, html);
		html.closeMain();
		html.beginPart("").buttonInfo("/vehicle-web/manufacturers", "Zurück");
		html.footer().closeHtml().out().flush();

	}

	private void printVehicleList(String manufacturerName, HtmlWriter html) {
		html.beginPart(manufacturerName);
		html.beginTable();
		for (VehicleDto vehicle : controller.findVehicleByManufacturer(manufacturerName)) {
			html.beginRow()
				.beginCell()
				.print(vehicle.getModelName())
				.closeCell()
				.closeRow();
		}
		html.closeTable();
		html.closePart();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (VehicleController) ctx.getBean("vehicleControllerBean");
	}

}
