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
import snap.domains.member.entity.Member;
import snap.domains.member.service.MemberDomainService;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberDomainService memberDomainService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthMember.class);
    }

    @Override
    public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthMember authMemberAnnotation = parameter.getParameterAnnotation(AuthMember.class);
        assert authMemberAnnotation != null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return memberDomainService.findMemberByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 올바르지 않습니다."));
    }
}
