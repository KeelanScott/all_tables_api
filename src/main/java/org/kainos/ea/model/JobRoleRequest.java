package org.kainos.ea.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    private String name;
    private int bandId;
    private int capabilityId;
    private String specification;

    @JsonCreator
    public JobRoleRequest(@JsonProperty("name") String name,
                          @JsonProperty("bandId") int bandId,
                          @JsonProperty("capabilityId") int capabilityId,
                          @JsonProperty("specification") String specification) {
        setName(name);
        setBandId(bandId);
        setCapabilityId(capabilityId);
        setSpecification(specification);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }
}
