package config;

import dao.CarBlockDAO;
import dao.CarDetailsDAO;
import dao.CarListingDAO;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import model.CarBlock;
import model.CarDetails;
import model.CarListing;
import parser.CarDetailsParser;
import parser.CarListingParser;
import res.CarDetailsRes;
import res.CarListingRes;

public class MainApplication extends Application<Config> {


    private final HibernateBundle<Config> hibernateBundle =
            new HibernateBundle<Config>(Object.class, CarDetails.class, CarListing.class, CarBlock.class) {

                public DataSourceFactory getDataSourceFactory(Config configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<Config>() {

            public DataSourceFactory getDataSourceFactory(Config configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(new TemplateConfigBundle());
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(Config config, Environment environment) throws Exception {

        final CarDetailsDAO carDetailsDAO = new CarDetailsDAO(hibernateBundle.getSessionFactory());
        final CarDetailsParser carDetailsParser = new CarDetailsParser(carDetailsDAO);

        final CarListingDAO carListingDAO = new CarListingDAO(hibernateBundle.getSessionFactory());
        final CarBlockDAO carBlockDAO = new CarBlockDAO(hibernateBundle.getSessionFactory());

        final CarListingParser carListingParser = new CarListingParser(carBlockDAO, carListingDAO);


        environment.jersey().register(new CarListingRes(carListingParser));
        environment.jersey().register(new CarDetailsRes(carDetailsParser));
    }
}
