package pl.edu.uj.ii.main.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GithubConfig {
    @Bean
    public GitHub getGithub() throws IOException {
        // TODO: Change to take the token from properties or even better from ENV variables
        return GitHubBuilder.fromPropertyFile().build();
    }
}
