package parser;

import dao.CarBlockDAO;
import dao.CarListingDAO;
import model.CarBlock;
import model.CarListing;

import java.util.Collections;
import java.util.List;

public class CarListingParser {

    private CarBlockDAO carBlockDAO;
    private CarListingDAO carListingDAO;


    public CarListingParser(CarBlockDAO carBlockDAO, CarListingDAO carListingDAO) {

        this.carBlockDAO = carBlockDAO;
        this.carListingDAO = carListingDAO;
    }


    public CarBlock createCarBlock(CarBlock carBlock) {
         return carBlockDAO.createOrUpdate(carBlock);
    }

    public CarListing createCarListing(CarListing carListing) {
         return carListingDAO.createOrUpdate(carListing);
    }

    public List<CarListing> getLiveListings() {
        return Collections.emptyList();
    }


}
