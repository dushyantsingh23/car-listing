package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDetailsDAO extends AbstractDAO<CarDetails> {

    private SessionFactory sessionFactory;

    public CarDetailsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public CarDetails createOrUpdate(CarDetails carDetails) {

        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(carDetails);
            //required when updating on a detached hibernate object
            session.flush();
            return carDetails;
        } finally {
            session.close();
        }
    }

    public CarDetails getById(String id) {
        return get(id);
    }

    public List<CarDetails> getBulkDetailsByIds(String[] carIds) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(CarDetails.class);
            criteria.add(Restrictions.in("id", carIds));
            return criteria.list();
        } finally {
            session.close();
        }
    }
}
