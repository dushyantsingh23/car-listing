package res;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.CarListingParser;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1/listing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarListingRes {
    private CarListingParser carListingParser;

    private static final Logger LOG = LoggerFactory.getLogger(CarListingRes.class);
    public CarListingRes(CarListingParser carListingParser) {
    this.carListingParser = carListingParser;
    }
}
