package dao;

import io.dropwizard.hibernate.AbstractDAO;
import model.CarDetails;
import org.hibernate.SessionFactory;

public class CarDetailsDAO extends AbstractDAO<CarDetails> {

    private SessionFactory sessionFactory;
    public CarDetailsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}
