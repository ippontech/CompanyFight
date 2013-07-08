package fr.ippon.companyfight.init;

import fr.ippon.companyfight.service.GithubService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

@Singleton
@Startup
public class DBPopulator {

    @Inject
    private Logger log;

    @Inject
    private GithubService githubDataFetcher;

    @PostConstruct
    private void populateDB() {
        log.info("Starting application");
        //githubDataFetcher.fetchDataFromGithub();
    }

}
