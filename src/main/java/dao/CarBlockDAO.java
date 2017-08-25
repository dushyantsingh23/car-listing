package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarBlock;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarBlockDAO extends AbstractDAO<CarBlock> {

    private SessionFactory sessionFactory;

    public CarBlockDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public CarBlock createOrUpdate(CarBlock carBlock) {
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(carBlock);
            //required when updating on a detached hibernate object
            session.flush();
            return carBlock;
        } finally {
            session.close();
        }
    }

    public CarBlock getById(String id) {
        return get(id);
    }

    public List<CarBlock> getCarBlocksBytime(String dateTime) {
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
