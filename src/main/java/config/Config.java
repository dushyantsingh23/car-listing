package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.omg.CORBA.Environment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Config extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    private Environment environment;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }


    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
