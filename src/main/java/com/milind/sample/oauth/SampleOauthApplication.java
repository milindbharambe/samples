package com.milind.sample.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import com.milind.sample.oauth.config.CustomUserDetails;
import com.milind.sample.oauth.repository.UserRepository;

@SpringBootApplication
public class SampleOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleOauthApplication.class, args);
    }

    @Autowired
    private void authenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder,
            UserRepository repository) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(username -> new CustomUserDetails(repository.getUserByUsername(username)));
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}
