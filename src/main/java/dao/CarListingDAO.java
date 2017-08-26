package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarListing;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarListingDAO extends AbstractDAO<CarListing> {

    private SessionFactory sessionFactory;

    public CarListingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public CarListing createOrUpdate(CarListing carListing) {
        persist(carListing);
        return carListing;
    }

    public List<CarListing> getCarListingByTimeAndIds(String dateTime, String[] blockedCars) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(CarListing.class);
        criteria.add(Restrictions.le("startTime", dateTime));
        criteria.add(Restrictions.ge("endTime", dateTime));
        if (blockedCars.length > 0) {
            criteria.add(Restrictions.not(Restrictions.in("carId", blockedCars)));
        }

        return criteria.list();
    }
}
