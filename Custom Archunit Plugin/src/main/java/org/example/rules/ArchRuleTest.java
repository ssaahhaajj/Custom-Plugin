package org.example.rules;

import org.example.service.ScopePathProvider;

import java.util.Collection;

/**
 * Created by agarg020917 on 11/10/2017.
 */
@FunctionalInterface
public interface ArchRuleTest {

    void execute(String path, ScopePathProvider scopePathProvider, Collection<String> excludedPaths);

}

