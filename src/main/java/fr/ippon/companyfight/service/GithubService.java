package fr.ippon.companyfight.service;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.model.Repository;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

@Stateless
public class GithubService {

    @Inject
    private Logger log;

    public Organization fetchOrganizationFromGithub(String name) {
        log.info("Fetching organization : " + name);
        Organization organization = new Organization();
        try {
            GitHub github = GitHub.connect();
            GHOrganization ghOrganization = github.getOrganization(name);
            organization.setId(name);
            organization.setFollowersCount(ghOrganization.getFollowersCount());
            organization.setAvatarUrl(ghOrganization.getAvatarUrl());
            for (GHRepository ghRepository : ghOrganization.getRepositories().values()) {
                Repository repository = new Repository();
                repository.setName(ghRepository.getName());
                repository.setUrl(ghRepository.getUrl());
                repository.setForks(ghRepository.getForks());
                organization.addRepository(repository);
            }

            Collection<GHUser> members = ghOrganization.getMembers();
            for (GHUser member : members) {
                log.fine("Creating person: " + member.getLogin());
                Person person = new Person();
                person.setLogin(member.getLogin());
                person.setFollowersCount(member.getFollowersCount());
                person.setAvatarUrl(member.getAvatarUrl());
                organization.addMember(person);
            }

            Map<String, GHRepository> ghRepositories = ghOrganization.getRepositories();
            for (GHRepository ghRepository : ghRepositories.values()) {
                log.fine("Creating repository: " + ghRepository.getName());
                Repository repository = new Repository();
                repository.setUrl(ghRepository.getUrl());
                repository.setName(ghRepository.getName());
                repository.setForks(ghRepository.getForks());
                organization.addRepository(repository);
            }

        } catch (IOException e) {
            log.info("Organization could not be fetched: " + e.getMessage());
            return null;
        }
        return organization;
    }
}
