package model;

import javax.persistence.*;

@Entity
@Table(name = "car_details")
public class CarDetails {

    @Id
    private String id;

    private String brand;

    private String model;

    private String licenseNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                '}';
    }
}
