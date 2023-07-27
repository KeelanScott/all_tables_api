package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controller.AuthController;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.service.JobRoleService;

public class all_tables_apiApplication extends Application<all_tables_apiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new all_tables_apiApplication().run(args);
    }

    @Override
    public String getName() {
        return "all_tables_api";
    }

    @Override
    public void initialize(final Bootstrap<all_tables_apiConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<all_tables_apiConfiguration>(){
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(all_tables_apiConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final all_tables_apiConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao())));
        environment.jersey().register(new AuthController(new AuthService(new AuthDao())));
    }

}