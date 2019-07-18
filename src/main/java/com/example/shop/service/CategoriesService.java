package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.exception.EntityNotFoundException;
import com.example.shop.repo.CategoriesRepository;
import com.example.shop.repo.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesService {

   private final GameRepository gameRepository;
   private final GameService gameService;
   private final CategoriesRepository categoriesRepository;

   public List<Category>findAll(){
       return categoriesRepository.findAll();
   }
   public Category findOne(Long id){
       return categoriesRepository.findById(id)
               .orElseThrow(()->new EntityNotFoundException(Category.class, id));
   }
   public Category findOne(String name){
       return categoriesRepository.findByName(name);
   }

   public Category findOne(Category category){
       if (category.getCategoryName()==null){
           return null;
       }
       return findOneByName(category.getCategoryName());
   }

//   public Category findOneByName(Category category, String name){
//       Category entity=findOne(name);
//       entity.setCategoryName(category.getCategoryName());
//       return categoriesRepository.save(entity);
//   }

    public Category findOneByName(String name) {
        List<Category> all = findAll();
        for (Category x : all)
            if (x.getCategoryName().equals(name))
                return x;

        return null;
    }
    public Category findOneByName(Category category) {
        List<Category> all = findAll();
        String name = category.getCategoryName();
        for (Category x : all)
            if (x.getCategoryName().equals(name))
                return x;
        return null;
    }

    public void save(Category category){
        if (category==null){
            return;
        }
        Category existCategory=findOneByName(category.getCategoryName());
        if (existCategory != null){
            existCategory.setGame(category.getGame());
            existCategory.getGame().forEach(x-> {
                x.setCategory(category);
                gameRepository.save(x);
            });
            categoriesRepository.save(existCategory);
        }else {
            categoriesRepository.save(category);
        }
    }

    public void save(Collection<Category>categories){
        if (categories.size()>0){
            categories.forEach(category -> {
                if (category != null){
                    save(category);
                }
            });
        }
    }
    private void deleteOperation(Category category){
        if (category==null){
            return;
        }
        category.getGame().forEach(gameService::delete);
        categoriesRepository.delete(category.getId());
    }

    public void delete(Long id){
        Category foundGame = categoriesRepository.findId(id);
        deleteOperation(foundGame);
    }
    public void delete(String name){
        deleteOperation(categoriesRepository.findByName(name));
    }

    public void delete(Category category){
       delete(category.getId());
    }


}
