package gm.sandbox.websandboxserver.repository;

import gm.sandbox.websandboxserver.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
