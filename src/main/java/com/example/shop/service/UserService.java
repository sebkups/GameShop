package com.example.shop.service;

import com.example.shop.entity.User;
import com.example.shop.exception.EntityNotFoundException;
import com.example.shop.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrderService orderService;

    public void save(User user){
        if (user==null){
            return;
        }
        User actualUser =userRepository.findByLogin(user.getLogin());
        User actualUser1= userRepository.findByMail(user.getEMail());

        boolean existUser= false;
        if (actualUser != null || actualUser1 !=null){
            existUser = true;
        }

        if (existUser){
            actualUser.setAddress(user.getAddress());
            actualUser.setAge(user.getAge());
            actualUser.setCookieCode(user.getCookieCode());
            actualUser.setEMail(user.getEMail());
            actualUser.setId(user.getId());
            actualUser.setLogin(user.getLogin());
            actualUser.setPassword(user.getPassword());
            actualUser.setOrders(user.getOrders());
            userRepository.save(actualUser);
        }
    }

    public void save(Collection<User>category){
        if (category.size()>0){
            category.forEach(x->{
                if (x!=null){
                    save(x);
                }
            });
        }
    }

    public User findOne(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(User.class, id));
    }
    public User findByMail(User user){
        return userRepository.findByMail(user.getEMail());
    }
    public User findByMail(String name){
        return userRepository.findByMail(name);
    }
    public User findByLogin(User user){
        return userRepository.findByLogin(user.getLogin());
    }
    public User findByLogin(String name){
        return userRepository.findByLogin(name);
    }


}
