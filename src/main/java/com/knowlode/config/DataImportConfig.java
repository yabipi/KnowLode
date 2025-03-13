package com.knowlode.config;

import com.knowlode.util.DataImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataImportConfig implements CommandLineRunner {

    @Autowired
    private DataImporter dataImporter;

    @Override
    public void run(String... args) throws Exception {
        dataImporter.importDataFromJson("/home/ku/workspaces/KnowLode/src/main/resources/json/2010-2022_Math_I_MCQs.json");
    }
}