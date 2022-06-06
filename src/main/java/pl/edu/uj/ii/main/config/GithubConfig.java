package pl.edu.uj.ii.main.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.github.v3.clients.GitHubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class GithubConfig {
    @Bean
    public GitHubClient getGithub() {
        return GitHubClient.create(URI.create("https://api.github.com"), System.getenv("token"));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
