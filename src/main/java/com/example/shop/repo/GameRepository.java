package com.example.shop.repo;

import com.example.shop.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findIdAndName(Game game, Long id);
    Game findByName(String name);
    Game findOne(Long id);
}
