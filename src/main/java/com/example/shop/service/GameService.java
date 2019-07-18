package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.entity.Game;
import com.example.shop.entity.Orders;
import com.example.shop.exception.EntityNotFoundException;
import com.example.shop.repo.CategoriesRepository;
import com.example.shop.repo.GameRepository;
import com.example.shop.repo.OrdersRepository;
import com.example.shop.repo.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    private final OrdersRepository ordersRepository;
    private final CategoriesRepository categoriesRepository;
    private final PictureRepository pictureRepository;
    private final CategoriesService categoriesService;

    public void save(Game game){
        if (game==null){
            return;
        }if (game.getCategory()==null) {
            return;
        }
        Category category =categoriesService.findOne(game.getCategory());
        if (category==null){
            return;
        }
        game.setCategory(category);
        game.getCategory().getGame().add(game);
        categoriesService.save(game.getCategory());
    }
    public void save(Collection<Game>games){
        if (games.size()>0){
            games.forEach(game->{
                if (game!=null){
            save(game);}
            });
        }
    }
    public List<Game> findAll(){
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


    public void delete(Game game){
        delete(game.getId());
    }
    public void delete(Long id){
        Game foundGame = gameRepository.findOne(id);
    }

    public void delete(Collection<Game>games){
        if (games.size()>0){
            games.forEach(game -> {
                if (game!=null){
                    delete(game.getId());
                }
            });
        }
    }
    private void deleteOperation(Game game){
        if (game==null){
            return;
        }
        deleteFromCategory(game);
        deleteGameFromOrders(game);
    }

    private void deleteFromCategory(Game game){
        Iterable<Category>categories= categoriesRepository.findAll();
        for (Iterator<Category>categoryIterator= categories.iterator(); categoryIterator.hasNext();){
            Category category = categoryIterator.next();
            for (Iterator<Game>gameIterator=category.getGame().iterator();gameIterator.hasNext();){
                Game game1 = gameIterator.next();
                if (game1.getId().equals(game.getId())){
                    gameIterator.remove();
                    categoriesRepository.save(category);
                }
            }
        }
    }

    private void deleteGameFromOrders(Game game){
        Iterable<Orders>orders= ordersRepository.findAll();
        for (Iterator<Orders> ordersIterable = orders.iterator(); ordersIterable.hasNext();){
            Orders orders1 =ordersIterable.next();
            for (Iterator<Game>ordersIt=orders1.getGames().iterator(); ordersIt.hasNext();){
                Game orders2 = ordersIt.next();
                if (orders2.getId().equals(game.getId())){
                    ordersIt.remove();
                    ordersRepository.save(orders1);
                }
            }
        }
    }
//
//    private void deleteGameFromOrders1(Game game){
//        Iterable<Orders>orders= ordersRepository.findAll();
//        for (Iterator<Orders> ordersIterable = orders.iterator(); ordersIterable.hasNext();){
//            Orders orders1 =ordersIterable.next();
//            orders1.getGames().removeIf(orders2 -> orders2.getId().equals(game.getId()));
//        }
//    }

}
