package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.controller.CompetencyController;
import org.kainos.ea.controller.TrainingCourseController;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.service.BandService;
import org.kainos.ea.service.CompetencyService;
import org.kainos.ea.service.TrainingCourseService;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.validator.BandCompetencyValidator;
import org.kainos.ea.validator.BandValidator;

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
        environment.jersey().register(new BandController(new BandService(new BandDao(), new BandValidator())));
        environment.jersey().register(new CompetencyController(new CompetencyService(new CompetencyDao(), new BandCompetencyValidator())));
        environment.jersey().register(new TrainingCourseController(new TrainingCourseService(new TrainingCourseDao())));
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao())));
    }
}
