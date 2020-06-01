package org.example.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.example.service.ScopePathProvider;
import org.example.utils.ArchUtils;

import java.util.Collection;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Junit's asserts are pretty poor. We favor the use of AssertJ which is much richer and enables developers to write easily fluent assertions. Therefore we don't want to see people use basic Junit assertions.
 *
 * @see : http://joel-costigliola.github.io/assertj/assertj-core.html
 */
public class NoJunitAssertRuleTest implements ArchRuleTest {

    protected static final String NO_JUNIT_ASSERT_DESCRIPTION = "not use Junit assertions";

    private static final String JUNIT4_ASSERT_PACKAGE_NAME = "org.junit.Assert";

    private static final String JUNIT5_ASSERT_PACKAGE_NAME = "org.junit.jupiter.api.Assertions";

    private static final String PACKAGE_SEPARATOR = ".";

    @Override
    public void execute(String path, ScopePathProvider scopePathProvider, Collection<String> excludedPaths) {
        classes().should(notUseJunitAssertRule()).check(ArchUtils.importAllClassesInPackage(path, scopePathProvider.getTestClassesPath(), excludedPaths));
    }

    protected static ArchCondition<JavaClass> notUseJunitAssertRule() {

        return new ArchCondition<JavaClass>(NO_JUNIT_ASSERT_DESCRIPTION) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {

                item.getMethodCallsFromSelf().stream().filter(methodCall -> isJunitAssert(methodCall.getTarget().getOwner()))
                        .forEach(junitAssertCall -> events.add(SimpleConditionEvent.violated(junitAssertCall,
                                "Favor AssertJ assertions over Junit's - " + junitAssertCall.getDescription())));
            }
        };
    }

    static boolean isJunitAssert(JavaClass javaClass) {

        String packageNameToCheck = new StringBuilder().append(javaClass.getPackageName()).append(PACKAGE_SEPARATOR).append(javaClass.getSimpleName())
                .toString();

        return JUNIT4_ASSERT_PACKAGE_NAME.equals(packageNameToCheck) || JUNIT5_ASSERT_PACKAGE_NAME.equals(packageNameToCheck);
    }
}