package res;


import io.dropwizard.hibernate.UnitOfWork;
import model.CarDetails;
import parser.CarDetailsParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("v1/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarDetailsRes {


    private CarDetailsParser carDetailsParser;

    public CarDetailsRes(CarDetailsParser carDetailsParser) {
        this.carDetailsParser = carDetailsParser;
    }

    @POST
    @UnitOfWork
    public CarDetails createCar(CarDetails carDetailsRequest) {

        return carDetailsParser.createCar(carDetailsRequest);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public CarDetails getCarById(@PathParam("id") String carId) {

        return carDetailsParser.getCarById(carId);
    }
}
