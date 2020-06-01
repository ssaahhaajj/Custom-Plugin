package org.example.maven.model;

import org.apache.maven.plugins.annotations.Parameter;
import org.example.model.ApplyOn;
import org.example.model.ConfigurableRule;

import java.util.ArrayList;
import java.util.List;

public class MavenConfigurableRule {

    @Parameter(property ="rule")
    private String rule;

    @Parameter(property ="applyOn")
    private MavenApplyOn applyOn;

    @Parameter(property ="checks")
    private List<String> checks = new ArrayList<>();

    @Parameter(defaultValue = "false", required = false)
    private boolean skip;

    public ConfigurableRule toCoreConfigurableRule(){
        return new ConfigurableRule(rule, applyOn ==null ? new ApplyOn() : applyOn
                .toCoreApplyOn(),checks,skip);
    }

    public List<String> getChecks() {
        return checks;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }

    public MavenApplyOn getApplyOn() {
        return applyOn;
    }

    public void setApplyOn(MavenApplyOn applyOn) {
        this.applyOn = applyOn;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}