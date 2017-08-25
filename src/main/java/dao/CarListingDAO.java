package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarListing;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CarListingDAO extends AbstractDAO<CarListing> {

    private SessionFactory sessionFactory;

    public CarListingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public CarListing createOrUpdate(CarListing carListing) {
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(carListing);
            //required when updating on a detached hibernate object
            session.flush();
            return carListing;
        } finally {
            session.close();
        }
    }

    public CarListing getById(String id) {
        return get(id);
    }
}
