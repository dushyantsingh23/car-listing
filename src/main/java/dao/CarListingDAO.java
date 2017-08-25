package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarBlock;
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

    public List<CarListing> getCarListingByTime(String dateTime) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(CarBlock.class);
            criteria.add(Restrictions.ge("startTime", dateTime));
            criteria.add(Restrictions.le("endTime", dateTime));
            return criteria.list();
        } finally {
            session.close();
        }
    }
}
