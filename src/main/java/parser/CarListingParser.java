package parser;

import dao.CarBlockDAO;
import dao.CarDetailsDAO;
import dao.CarListingDAO;
import model.CarBlock;
import model.CarDetails;
import model.CarListing;
import utils.DateUtils;

import java.time.Instant;
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

    public CarBlock createCarBlock(CarBlock carBlock) {
        //validateCarTimingsRequest(carBlock);

        return carBlockDAO.createOrUpdate(carBlock);
    }

//    private void validateCarTimingsRequest(CarTimings carTimings) {
//        if (!(DateUtils.isDateInFormat(carTimings.getStartTime()) &&
//                DateUtils.isDateInFormat(carTimings.getEndTime()))) {
//            throw new ListingException(ExceptionType.BAD_REQUEST, "Invalid date format");
//        }
//
//        CarDetails carDetails = carDetailsDAO.getById(carTimings.getCarId());
//        if (carDetails == null) {
//            throw new ListingException(ExceptionType.NOT_FOUND, "No car found with given id");
//        }
//    }

    public CarListing createCarListing(CarListing carListing) {
        //validateCarTimingsRequest(carListing);

        return carListingDAO.createOrUpdate(carListing);
    }

    public List<CarDetails> getLiveListings(Long searchTime) {
        if (searchTime == null) {
            searchTime = Instant.now().getEpochSecond();
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
