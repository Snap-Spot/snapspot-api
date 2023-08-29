package snap.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerDomainService;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthPhotographerArgumentResolver implements HandlerMethodArgumentResolver {
    private final PhotographerDomainService photographerDomainService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPhotographer.class);
    }

    @Override
    public Photographer resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthPhotographer photographerAnnotation = parameter.getParameterAnnotation(AuthPhotographer.class);
        assert photographerAnnotation != null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        log.info(authentication.getName());
        log.info(authentication.getAuthorities().toString());

        return photographerDomainService.findByEmail(authentication.getName());
    }
}
