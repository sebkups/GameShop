package com.example.shop.repo;

import com.example.shop.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
