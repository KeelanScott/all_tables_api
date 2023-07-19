package org.kainos.ea;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class all_tables_apiConfiguration extends Configuration {
    @Valid
    @NotNull
    private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger() {
        swagger.setResourcePackage("org.kainos.ea.controller");
        String[] schemes = { "http", "https" };
        swagger.setSchemes(schemes);
        return swagger;
    }
}
