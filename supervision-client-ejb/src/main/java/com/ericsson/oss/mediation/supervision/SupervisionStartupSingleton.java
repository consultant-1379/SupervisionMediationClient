package com.ericsson.oss.mediation.supervision;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * 
 * @author emaomic
 * 
 */
//@javax.ejb.Singleton
@Startup
public class SupervisionStartupSingleton {

    @Inject
    private Logger logger;

    @Inject
    private SupervisionService serviceSupervision;

    @PostConstruct
    public void initSupervision() {
        this.logger.debug("init all supervisions ..");
        this.serviceSupervision.initAllSupervisions();
        this.logger.info("init all supervisions .. DONE");
    }
}
