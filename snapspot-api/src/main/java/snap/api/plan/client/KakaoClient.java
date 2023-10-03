package snap.api.plan.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoClient {
    @Value("${kakao.url}")
    private String kakaoUrl;

    private String kakaoApiUrl = "https://dapi.kakao.com/v2";

    @Value("${kakao.clientId}")
    private String clientId;

    WebClient kakaoApiWebClient =
            WebClient.builder()
                    .baseUrl(kakaoApiUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

    public List<Object> getCoordinateFromAddress(String address) {
        Map<String, Object> response = kakaoApiWebClient.get().uri(uriBuilder ->
                uriBuilder.path("/local/search/address")
                        .queryParam("query", address)
                        .queryParam("page", 1)
                        .queryParam("size", 1).build()
        ).retrieve().bodyToMono(Map.class).block();

        return (List<Object>) response.get("documents");
    }
}
