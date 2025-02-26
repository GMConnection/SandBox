package gm.sandbox.websandbox.client;

import gm.sandbox.websandbox.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientGame implements GameRestClient {

    private final ParameterizedTypeReference<List<GameDto>> GAME_DTO_LIST = new ParameterizedTypeReference<>() {
    };

    private final RestClient restClient;

    @Override
    public List<GameDto> findAll() {
        return restClient.get().uri("/api/games").retrieve().body(GAME_DTO_LIST);
    }

    @Override
    public GameDto findById(Long id) {
        return restClient
                .get()
                .uri("/api/games/{id}", id)
                .retrieve()
                .body(GameDto.class);
    }

    @Override
    public void deleteById(Long id) {
        try {
            restClient
                    .delete()
                    .uri("/api/games/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new NoSuchElementException("Game with id " + id + " not found");
        }

    }

    @Override
    public GameDto create(GameDto game) {
        try {

            return restClient.post()
                    .uri("/api/games")
                    .body(game)
                    .retrieve()
                    .body(GameDto.class);
        } catch (HttpClientErrorException.BadRequest ex) {
            ProblemDetail problem = ex.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestExp((List<String>) problem.getProperties().get("errors"));
        }
    }

    @Override
    public GameDto update(GameDto game) {
        try {

            return restClient.post()
                    .uri("/api/games/{id}", game.id())
                    .body(game)
                    .retrieve()
                    .body(GameDto.class);
        } catch (HttpClientErrorException.BadRequest ex) {
            ProblemDetail problem = ex.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestExp((List<String>) problem.getProperties().get("errors"));
        }
    }
}
