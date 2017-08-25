package config;

import dao.CarDetailsDAO;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import model.CarDetails;
import parser.CarListingParser;
import res.CarListingRes;

/**
 * Created by Hades on 01/02/17.
 */
public class MainApplication extends Application<Config>  {


    private final HibernateBundle<Config> hibernateBundle =
            new HibernateBundle<Config>(Object.class, CarDetails.class) { //TODO add all models

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

        CarDetailsDAO carDetailsDAO = new CarDetailsDAO(hibernateBundle.getSessionFactory());;
        CarListingParser carListingParser = new CarListingParser();
        environment.jersey().register(new CarListingRes(carListingParser));
    }
}
