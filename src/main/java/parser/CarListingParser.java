package parser;

import dao.CarBlockDAO;
import dao.CarDetailsDAO;
import dao.CarListingDAO;
import exceptions.ExceptionType;
import exceptions.ListingException;
import model.CarBlock;
import model.CarDetails;
import model.CarListing;
import utils.DateUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarListingParser {

    private CarBlockDAO carBlockDAO;
    private CarListingDAO carListingDAO;
    private CarDetailsDAO carDetailsDAO;


    public CarListingParser(CarBlockDAO carBlockDAO, CarListingDAO carListingDAO, CarDetailsDAO carDetailsDAO) {

        this.carBlockDAO = carBlockDAO;
        this.carListingDAO = carListingDAO;
        this.carDetailsDAO = carDetailsDAO;
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

    public List<CarDetails> getLiveListings(Long searchTime) {
        if (searchTime == null) {
            searchTime = System.currentTimeMillis() / 1000;
        }
        String date = DateUtils.getISTTimeFromEpoch(searchTime);
        List<CarBlock> carBlocks = carBlockDAO.getCarBlocksByTime(date);

        Set<String> blockedCarIds = new HashSet<>();
        for (CarBlock cb : carBlocks) blockedCarIds.add(cb.getCarId());
        List<CarListing> carListings = carListingDAO.getCarListingByTimeAndIds(date,
                blockedCarIds.toArray(new String[blockedCarIds.size()]));

        return getCarDetails(carListings);
    }

    private List<CarDetails> getCarDetails(List<CarListing> carListings) {
        Set<String> carDetailsSet = new HashSet<>();

        for (CarListing cl : carListings) {
            carDetailsSet.add(cl.getCarId());
        }

        if (carDetailsSet.size() > 0) {
            return carDetailsDAO.getBulkDetailsByIds(carDetailsSet.toArray(new String[carDetailsSet.size()]));
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
