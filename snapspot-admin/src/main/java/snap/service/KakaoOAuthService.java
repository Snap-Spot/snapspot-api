package snap.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import snap.dto.KakaoRes;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOAuthService {

    @Value("${kakao.url}")
    private String kakaoUrl;

    @Value("${kakao.apiurl}")
    private String kakaoApiUrl;

    @Value("${kakao.clientId}")
    private String clientId;

    public KakaoRes getKakaoInfo(String accessToken) {

        WebClient kakaoApiWebClient =
                WebClient.builder()
                        .baseUrl(kakaoApiUrl)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();


        Map<String, Object> kakaoResponse =
                kakaoApiWebClient
                        .post()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v2/user/me")
                                .build())
                        .header("Authorization", "Bearer " + accessToken)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

        Map<String, Object> kakaoAccountMap = (Map<String, Object>) kakaoResponse.get("kakao_account");
        Map<String, String> profileMap = (Map<String, String>) kakaoAccountMap.get("profile");

        return KakaoRes.builder()
                .nickname(profileMap.get("nickname"))
                .profile(profileMap.get("profile_image_url"))
                .email(kakaoAccountMap.get("email").toString())
                .build();
    }
}
