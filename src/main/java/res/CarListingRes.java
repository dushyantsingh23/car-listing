package res;

import model.CarAvailability;
import model.CarBlock;
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
    public CarListing createCarListing(CarListing carListing) {
        return carListingParser.createCarListing(carListing);
    }


   @POST
   @Path("block")
    public CarBlock createCarBlock(CarBlock carBlock) {
        return  carListingParser.createCarBlock(carBlock);
    }

    @GET
    @Path("listing")
    public List<CarAvailability> getCarsListing() {
        return carListingParser.getLiveListings();
    }

}
