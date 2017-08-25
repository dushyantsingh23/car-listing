package parser;

import dao.CarBlockDAO;
import dao.CarListingDAO;
import exceptions.ExceptionType;
import exceptions.ListingException;
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

    private void validateCarBlockRequest(CarBlock carBlock) {
       if (!(DateUtils.isDateInFormat(carBlock.getStartTime()) &&
               DateUtils.isDateInFormat(carBlock.getEndTime()))) {
            throw new ListingException(ExceptionType.BAD_REQUEST, "Invalid date format");
       }
    }

    public CarBlock createCarBlock(CarBlock carBlock) {
        validateCarBlockRequest(carBlock);

        return carBlockDAO.createOrUpdate(carBlock);
    }

    private void validateCarListingRequest(CarListing carListing) {
        if (!(DateUtils.isDateInFormat(carListing.getStartTime()) &&
                DateUtils.isDateInFormat(carListing.getEndTime()))) {
            throw new ListingException(ExceptionType.BAD_REQUEST, "Invalid date format");
        }
    }

    public CarListing createCarListing(CarListing carListing) {
        validateCarListingRequest(carListing);

        return carListingDAO.createOrUpdate(carListing);
    }

    public List<CarAvailability> getLiveListings(Long searchTime) {
         if (searchTime == null) {
             searchTime = System.currentTimeMillis() / 1000;
         }
        String date = DateUtils.getISTTimeFromEpoch(searchTime);


        List<CarListing> carListings = carListingDAO.getCarListingByTime(date);

        List<CarBlock> carBlocks = carBlockDAO.getCarBlocksBytime(date);

        return getCarAvailabilityList(carListings, carBlocks);
    }

    public List<CarAvailability> getCarAvailabilityList(List<CarListing> carListings, List<CarBlock> carBlocks) {
        return Collections.EMPTY_LIST;
    }


}
