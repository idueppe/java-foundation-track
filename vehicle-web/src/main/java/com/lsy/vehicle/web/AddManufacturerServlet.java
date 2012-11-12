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
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;
import com.lsy.vehicle.web.util.HtmlWriter;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns = "/AddManufacturer")
public class AddManufacturerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private WebApplicationContext ctx;

	private ManufacturerController controller;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());
		html.defaultHeader().beginHtml().beginMain().beginPart("Add Manufacturer");
		
		html.println("<form action=\"AddManufacturer\" method=\"POST\">");
			html.println("<label>Name:</label>");
			html.println("<input type=\"text\" size=\"40\" name=\"manufacturerName\">");
			html.println("<button type=\"submit\" class=\"btn\">Anlegen</button>");
		html.println("</form>");
		
		html.closePart().closeMain();
		html.beginPart("").buttonInfo("/vehicle-web/manufacturers", "Zurück");
		html.footer().closeHtml();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manufacturerName = request.getParameter("manufacturerName");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());
		html.defaultHeader().beginHtml().beginMain().beginPart("Add Manufacturer");

		try {
			controller.addManufacturer(manufacturerName);
			html.print("<p class=\"text-success\">Manufacturer "+manufacturerName+" erfolgreich angelegt.</p>");
		} catch (ManufacturerAlreadyExistsException e) {
			html.print("<p class=\"text-error\">Beim anlegen des Manufacturer "+manufacturerName+" trat ein fehler auf.</p>");
			html.print("<p class=\"text-error\">"+e.getMessage()+"</p>");
		}
		
		html.closePart().closeMain();
		html.beginPart("").buttonInfo("/vehicle-web/manufacturers", "Zurück");
		html.footer().closeHtml();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (ManufacturerController) ctx.getBean("manufacturerControllerBean");
	}

}