package snap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import snap.resolver.AuthMemberArgumentResolver;
import snap.resolver.AuthPhotographerArgumentResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthMemberArgumentResolver authMemberArgumentResolver;
    private final AuthPhotographerArgumentResolver authPhotographerArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authMemberArgumentResolver);
        argumentResolvers.add(authPhotographerArgumentResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("https://snapspot.co.kr")
                .allowedOrigins("https://www.snapspot.co.kr")
                .allowedMethods(HttpMethod.GET.name())
                .allowedMethods(HttpMethod.POST.name())
                .allowedMethods(HttpMethod.PUT.name())
                .allowedMethods(HttpMethod.DELETE.name())
                .allowedHeaders("Authorization")
                .allowedHeaders("refresh-token")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
