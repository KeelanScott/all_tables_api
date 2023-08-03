package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controller.AuthController;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.controller.CapabilityController;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.service.BandService;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.validator.JobRoleValidator;
import org.kainos.ea.validator.AuthValidator;
import org.kainos.ea.controller.*;
import org.kainos.ea.dao.*;
import org.kainos.ea.service.*;
import org.kainos.ea.validator.*;

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
        CompetencyDao competencyDao = new CompetencyDao();
        TrainingCourseDao trainingCourseDao = new TrainingCourseDao();
        CapabilityDao capabilityDao = new CapabilityDao();
        BandDao bandDao = new BandDao();
        JobRoleDao jobRoleDao = new JobRoleDao();
        CompetencyService competencyService = new CompetencyService(competencyDao);
        AuthDao authDao = new AuthDao();
        environment.jersey().register(new BandController(new BandService(bandDao, competencyDao, trainingCourseDao, new BandValidator(), new BandCompetencyValidator(competencyService))));
        environment.jersey().register(new CompetencyController(competencyService));
        environment.jersey().register(new TrainingCourseController(new TrainingCourseService(trainingCourseDao)));
        environment.jersey().register(new JobRoleController(new JobRoleService(jobRoleDao, new JobRoleValidator(jobRoleDao, bandDao, capabilityDao))));
        environment.jersey().register(new CapabilityController(new CapabilityService(capabilityDao)));
        environment.jersey().register(new AuthController(new AuthService(authDao, new AuthValidator(authDao))));
    }
}