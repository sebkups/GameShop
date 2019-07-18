package com.example.shop.service;

import com.example.shop.entity.Address;
import com.example.shop.exception.EntityNotFoundException;
import com.example.shop.repo.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


    public Address find(Address address, Long id){
        return addressRepository.findIdAndAddress(address, id)
                .orElseThrow(()->new EntityNotFoundException(Address.class, id));
    }

    public Address findOne(Long id){
        return addressRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Address.class, id));
    }
    public Address findOne(Address address) {
            return findOne(address.getId());
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public void save(Address address){
        if (address.getId() !=null) {
            addressRepository.save(address);
        }
    }

    public void save(Collection<Address>addresses){
        addresses.forEach(address -> {
            if (address!=null)
                addressRepository.save(address);
        });
    }

    public void delete(Long id, Address address){
        Address entity =find(address, id);
        addressRepository.delete(entity);
    }



}
