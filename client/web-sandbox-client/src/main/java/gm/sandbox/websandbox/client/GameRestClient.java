package gm.sandbox.websandbox.client;

import gm.sandbox.websandbox.dto.GameDto;

import java.util.List;

public interface GameRestClient {

//    saveOrUpdate findAll  findById deleteById

    List<GameDto> findAll();

    GameDto findById(Long id);

    void deleteById(Long id);

    GameDto create(GameDto game);

    GameDto update(GameDto game);
}
