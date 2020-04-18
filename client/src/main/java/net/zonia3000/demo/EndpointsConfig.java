package net.zonia3000.demo;

import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.DefaultUserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class EndpointsConfig extends AuthorizationServerEndpointsConfiguration {

    @Value("${check-token-uri}")
    private String checkTokenUri;

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new InMemoryClientDetailsService();
    }

    @Bean
    public UserInfoRestTemplateFactory userInfoRestTemplateFactory(
            ObjectProvider<List<UserInfoRestTemplateCustomizer>> customizers,
            ObjectProvider<OAuth2ProtectedResourceDetails> details,
            ObjectProvider<OAuth2ClientContext> oauth2ClientContext) {
        return new DefaultUserInfoRestTemplateFactory(customizers, details,
                oauth2ClientContext);
    }

    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(checkTokenUri);
        return services;
    }
}
