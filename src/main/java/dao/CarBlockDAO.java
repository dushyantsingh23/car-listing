package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarBlock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
