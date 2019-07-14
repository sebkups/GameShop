package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.entity.Game;
import com.example.shop.exception.EntityNotFoundException;
import com.example.shop.repo.CategoriesRepository;
import com.example.shop.repo.GameRepository;
import com.example.shop.repo.OrdersRepository;
import com.example.shop.repo.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final OrdersRepository ordersRepository;
    private final CategoriesRepository categoriesRepository;
    private final PictureRepository pictureRepository;


    public void save(Game game){
        if (game==null){
            return;
        }if (game.getCategory()==null) {
            return;
        }
    }
    public void save(Collection<Game>games){
        if (games.size()>0){
            games.forEach(x->{if (x!=null){
            save(x);}
            });
        }
    }
    public Iterable<Game>findAll(){
        return gameRepository.findAll();
    }

//    public Game find(Long id,String name, Game game){
//        Game entity =findEntity(id, game);
//        entity.setGameName(game.getGameName());
//        entity.setId(game.getId());
//        return gameRepository.save(entity);
//    }

    public Game findOne(Long id){
        return gameRepository.findOne(id);
    }
    public Game findOne(String name){
        return gameRepository.findByName(name);
    }
    public Game findOne(Game game){
        if (game.getId()==null){
            return null;
        }
        return gameRepository.findOne(game.getId());

    }

    public Game findEntity(Long id, Game game){
        return gameRepository.findIdAndName(game, id)
                .orElseThrow(()->new EntityNotFoundException(Game.class, id));
    }






}
