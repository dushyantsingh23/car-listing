package parser;

import dao.CarDetailsDAO;
import model.CarDetails;

public class CarDetailsParser {


    private CarDetailsDAO carDetailsDAO;
    public CarDetailsParser(CarDetailsDAO carDetailsDAO) {
         this.carDetailsDAO = carDetailsDAO;
    }


    public CarDetails createCar(CarDetails carDetails) {
        return carDetailsDAO.createOrUpdate(carDetails);
    }

    public CarDetails getCarById(String carId) {
        return carDetailsDAO.getById(carId);
    }
}
