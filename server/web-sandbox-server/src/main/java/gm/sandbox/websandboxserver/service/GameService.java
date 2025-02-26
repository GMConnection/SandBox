package gm.sandbox.websandboxserver.service;

import gm.sandbox.websandboxserver.dto.GameDto;
import gm.sandbox.websandboxserver.entity.Game;
import gm.sandbox.websandboxserver.mapper.GameMapper;
import gm.sandbox.websandboxserver.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    public GameDto saveOrUpdate(GameDto game) {
        Game save = repository.save(mapper.mapToEntity(game));
        return mapper.mapToDto(save);
    }

    public GameDto findById(Long id) {
        return mapper.mapToDto(repository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<GameDto> findAll() {
        return repository.findAll().stream().map(mapper::mapToDto).toList();
    }
}
