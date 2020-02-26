package com.billercrud.billerapi.resource;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.*;
import com.billercrud.billerapi.model.*;

import com.billercrud.billerapi.dao.*;

@Path("biller")
public class BillerResource {

	private BillerDao billerDao = new BillerDao();

	@GET()
	@Path("billers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillers() {
		List<Biller> list = billerDao.getAllBillers();
		if (list == null) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
		} else
			return Response.status(Response.Status.OK).entity(list).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("deactivate/{billerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactivateBiller(@PathParam("billerId") String billerId) {
		try {
			Integer.parseInt(billerId);
		} catch (Exception exception) {
			exception.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
		if (billerDao.deleteBillerById(Integer.parseInt(billerId))) {
			return Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET()
	@Path("Biller/{billerName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBiller(@PathParam("billerName") String billerName) {
		Biller biller = billerDao.getBillerByName(billerName);
		if (biller == null) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
		} else
			return Response.status(Response.Status.OK).entity(biller).type(MediaType.APPLICATION_JSON).build();
	}

	@POST()
	@Path("addBiller")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBiller(Biller biller) {
		if (billerDao.addBiller(biller)) {
			Biller biller2 = billerDao.getBillerByName(biller.getBillerName());
			return Response.status(Response.Status.OK).entity(biller2).type(MediaType.APPLICATION_JSON).build();
		}
		else
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT()
	@Path("updateAddress")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAddress(JsonObject jsonObject) {
//		System.out.println(jsonObject);
		if (billerDao.updateAddress(jsonObject.getInt("billerId"), jsonObject.getString("address"))) {
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
		} else
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT()
	@Path("updateEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmail(JsonObject jsonObject) {
		if (billerDao.updateEmailId(jsonObject.getInt("billerId"), jsonObject.getString("oldEmail"),
				jsonObject.getString("newEmail")))
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT()
	@Path("updatePhone")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePhone(JsonObject jsonObject) {
		if (billerDao.updatePhoneNumber(jsonObject.getInt("billerId"), jsonObject.getString("oldPhone"),
				jsonObject.getString("newPhone")))
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();

	}

	@PUT()
	@Path("updateState")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateState(JsonObject jsonObject) {

		if (billerDao.updateState(jsonObject.getInt("billerId"), jsonObject.getString("state")))
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT()
	@Path("updateCity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCity(JsonObject jsonObject) {
		if (billerDao.updateCity(jsonObject.getInt("billerId"), jsonObject.getString("city")))
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();

	}

	@GET()
	@Path("serviceableCity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceableCities() {
		List<City> list = billerDao.getAllServiceableCity();
		if (list == null) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.OK).entity(list).type(MediaType.APPLICATION_JSON).build();
	}

	@GET()
	@Path("serviceableCity/{billerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillerServiceableCities(@PathParam("billerId") String billerId) {
		int id;
		try {
			id = Integer.parseInt(billerId);
		}catch(NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		ServiceableCity serviceableCity = billerDao.getBillerServiceableCityId(id);
		return Response.status(Response.Status.OK).entity(serviceableCity).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("billerbyid/{billerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillerById(@PathParam("billerId") String billerId) {
		int bId;
		try {
			bId = Integer.parseInt(billerId);
		}catch(NumberFormatException exception) {
			exception.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Biller biller = billerDao.getBillerById(bId);
		if(biller != null) {
			return Response.status(Response.Status.OK).entity(biller).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}

}
