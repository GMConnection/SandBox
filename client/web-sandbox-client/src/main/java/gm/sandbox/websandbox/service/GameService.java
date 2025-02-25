package gm.sandbox.websandbox.service;

import gm.sandbox.websandbox.dto.GameDto;
import gm.sandbox.websandbox.mapper.GameMapper;
import gm.sandbox.websandbox.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    public GameService(GameRepository repository, GameMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void saveOrUpdate(GameDto game) {
        repository.save(mapper.mapToEntity(game));
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
