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
        return GitHubBuilder.fromEnvironment().build();
    }
}
