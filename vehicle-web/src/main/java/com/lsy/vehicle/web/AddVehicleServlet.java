package com.lsy.vehicle.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.dto.EngineDto;
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.web.util.HtmlWriter;

/**
 * Servlet implementation class ManufacturerServlet
 */
@WebServlet(urlPatterns = "/addvehicle")
public class AddVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private WebApplicationContext context;

	private VehicleController controller;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HtmlWriter html = new HtmlWriter(response.getWriter());
		
		html.defaultHeader().beginHtml().beginMain();
		
		String manufacturer = request.getParameter("manufacturer");
		printForm(html, manufacturer);
		printActionBar(html);
		
		html.closeMain();
		html.footer().closeHtml();
	}

	private void printActionBar(HtmlWriter html) {
		html.beginFluid();
		html.beginPart("").buttonInfo("/vehicle-web/manufacturers", "Zurück");
		html.closeFluid();
	}

	private void printForm(HtmlWriter html, String manufacturer) {
		html.beginFluid().beginPart("Add Vehicle");
		html.println("<form action=\"addvehicle\" method=\"POST\">");
			html.print("<fieldset>");
			html.println("<input type=\"hidden\" name=\"manufacturer\" value=\""+ manufacturer +"\">");
			html.println("<label>Modelname:</label>");
			html.println("<input type=\"text\" size=\"40\" name=\"modelName\">");
			html.println("<br/>");
			html.println("<label>Herstellungsdatum:</label>");
			html.println("<input type=\"text\" size=\"40\" name=\"construction\">");
			html.println("<br/>");
			html.println("<button type=\"submit\" class=\"btn\">Anlegen</button>");
			html.print("</fieldset>");
		html.println("</form>");
		html.closePart().closeFluid();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlWriter html = new HtmlWriter(response.getWriter());
		html.defaultHeader().beginHtml().beginMain().beginFluid().beginPart("Add Manufacturer");

		try {
			VehicleDto vehicle = new VehicleDto();
			String modelName = request.getParameter("modelName");
			String manufacturer = request.getParameter("manufacturer");

			vehicle.setModelName(modelName);
			vehicle.setManufacturerName(manufacturer);
			EngineDto engineDto = new EngineDto();
			engineDto.setEngineType(EngineType.DIESEL);
			vehicle.setEngine(engineDto);
			
			String paramConstructionDate = request.getParameter("construction");
			Date constructionDate = new SimpleDateFormat("dd.MM.yyyy").parse(paramConstructionDate);
			vehicle.setConstructionDate(constructionDate);
			
			controller.saveOrUpdateVehicle(vehicle);
			html.print("<p class=\"text-success\">Fahrzeug "+modelName+" dem Hersteller "+manufacturer+" hinzugefügt.</p>");
		} catch (ParseException e) {
			html.print("<p class=\"text-error\"> "+e.getMessage()+"</p>");
		}
		
		html.closePart().closeFluid();
		html.beginFluid();
		html.beginPart("").buttonInfo("/vehicle-web/manufacturers", "Zurück");
		html.closeFluid();
		html.closeMain();
		
		html.footer().closeHtml();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		controller = (VehicleController) context.getBean("vehicleControllerBean");
	}

}
