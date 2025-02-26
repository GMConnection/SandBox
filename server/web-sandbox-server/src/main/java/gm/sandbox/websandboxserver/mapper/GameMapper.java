package gm.sandbox.websandboxserver.mapper;

import gm.sandbox.websandboxserver.config.CommonMapperConfiguration;
import gm.sandbox.websandboxserver.dto.GameDto;
import gm.sandbox.websandboxserver.entity.Game;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfiguration.class)
public interface GameMapper {

    Game mapToEntity(GameDto game);

    GameDto mapToDto(Game game);
}
