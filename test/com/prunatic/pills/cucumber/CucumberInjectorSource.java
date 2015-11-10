package com.prunatic.pills.cucumber;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.prunatic.pills.cucumber.domain.CucumberDomainModule;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

import java.util.Arrays;
import java.util.List;

public class CucumberInjectorSource implements InjectorSource {
    @Override
    public Injector getInjector() {
        final List<Module> modules = Arrays.asList(
                CucumberModules.SCENARIO,
                new CucumberDomainModule()
        );
        return Guice.createInjector(Stage.PRODUCTION, modules);
    }
}
