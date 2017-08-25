package res;

import io.dropwizard.hibernate.UnitOfWork;
import model.CarBlock;
import model.CarDetails;
import model.CarListing;
import parser.CarListingParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarListingRes {
    private CarListingParser carListingParser;

    public CarListingRes(CarListingParser carListingParser) {
        this.carListingParser = carListingParser;
    }

    @POST
    @Path("listing")
    @UnitOfWork
    public CarListing createCarListing(CarListing carListing) {
        return carListingParser.createCarListing(carListing);
    }


    @POST
    @Path("block")
    @UnitOfWork
    public CarBlock createCarBlock(CarBlock carBlock) {
        return carListingParser.createCarBlock(carBlock);
    }

    @GET
    @Path("search")
    @UnitOfWork
    public List<CarDetails> getCarsListing(@QueryParam("time") Long searchTime) {
        return carListingParser.getLiveListings(searchTime);
    }

}
