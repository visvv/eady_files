package com.vv.easy.server.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EasyFileInitializer implements CommandLineRunner {
    Logger logger  = LoggerFactory.getLogger(EasyFileInitializer.class);
    @Override
    public void run(String... args) throws Exception {
        String easyFileHome = System.getProperty("user.home") + "/easy_files";
        File homeFolder = new File(easyFileHome);
        if(homeFolder.exists()) {
           logger.info("easy_file home folder is already present.");
        } else {
            homeFolder.mkdir();
            logger.info("easy file home folder has been created" + homeFolder.getCanonicalPath());
        }
    }
}
