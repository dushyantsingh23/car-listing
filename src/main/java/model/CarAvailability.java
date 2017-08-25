package model;

import java.util.List;

public class CarAvailability {

    private CarDetails carDetails;

    private List<Availability> timings;

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public List<Availability> getTimings() {
        return timings;
    }

    public void setTimings(List<Availability> timings) {
        this.timings = timings;
    }
}
