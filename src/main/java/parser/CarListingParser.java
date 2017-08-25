package parser;

import dao.CarBlockDAO;
import dao.CarListingDAO;
import model.CarAvailability;
import model.CarBlock;
import model.CarListing;
import utils.DateUtils;

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

    public List<CarAvailability> getLiveListings() {

        long currentTime = System.currentTimeMillis() / 1000;
        String date = DateUtils.getISTTimeFromEpoch(currentTime);


        List<CarListing> carListings = carListingDAO.getCarListingByTime(date);

        List<CarBlock> carBlocks = carBlockDAO.getCarBlocksBytime(date);

        return getCarAvailabilityList(carListings, carBlocks);
    }

    public List<CarAvailability> getCarAvailabilityList(List<CarListing> carListings, List<CarBlock> carBlocks) {
        return Collections.EMPTY_LIST;
    }


}
