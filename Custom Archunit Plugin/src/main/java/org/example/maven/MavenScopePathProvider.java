package org.example.maven;

import org.example.service.ScopePathProvider;

public class MavenScopePathProvider implements ScopePathProvider {

    @Override
    public String getMainClassesPath() {
        return "/classes";
    }

    @Override
    public String getTestClassesPath() {
        return "/test-classes";
    }
}