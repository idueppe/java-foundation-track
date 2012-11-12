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

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.web.util.HtmlWriter;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns="/manufacturers")
public class ManufacturerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private WebApplicationContext ctx;
	
	private ManufacturerController controller;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HtmlWriter html = new HtmlWriter(response.getWriter());

		html.beginHtml().defaultHeader().beginMain();

		html.beginPart("Manufacturer").beginTable();
		for (ManufacturerDto manufacturer : controller.allManufactures()) {
			html.beginRow().beginCell();
			html.button("vehicles?manufacturer="+manufacturer.getName(), manufacturer.getName());
			html.closeCell().closeRow();
		}
		html.closeTable().closePart();

		html.closeMain();
		html.beginPart("")
			.buttonInfo("/vehicle-web/AddManufacturer", "Add Manufacturer")
			.print("&nbsp;")
			.buttonInfo("/vehicle-web", "Zurück");
		html.footer().closeHtml().out().flush();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (ManufacturerController) ctx.getBean("manufacturerControllerBean");
	}

}
