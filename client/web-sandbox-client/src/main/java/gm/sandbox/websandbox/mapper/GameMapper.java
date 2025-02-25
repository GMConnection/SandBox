package gm.sandbox.websandbox.mapper;

import gm.sandbox.websandbox.config.CommonMapperConfiguration;
import gm.sandbox.websandbox.dto.GameDto;
import gm.sandbox.websandbox.entity.Game;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfiguration.class, componentModel = "spring")
public interface GameMapper {

    GameDto mapToDto(Game game);

    Game mapToEntity(GameDto gameDto);
}
