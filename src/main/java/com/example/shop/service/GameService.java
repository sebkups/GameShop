package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.entity.Game;
import com.example.shop.repo.CategoriesRepository;
import com.example.shop.repo.GameRepository;
import com.example.shop.repo.OrdersRepository;
import com.example.shop.repo.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        }if (game.getCategory()==null){
            return;
        }

    }



}
