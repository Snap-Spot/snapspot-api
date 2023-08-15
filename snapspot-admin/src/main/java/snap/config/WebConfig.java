package snap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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
}
