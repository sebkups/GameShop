package com.example.shop.service;

import com.example.shop.entity.Address;
import com.example.shop.exception.EntityNotFoundException;
import com.example.shop.repo.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


    public Address findOne(Address address, Long id){
        return addressRepository.findIdAndAddress(address, id)
                .orElseThrow(()->new EntityNotFoundException(Address.class, id));
    }

    public Iterable<Address>findAll(){
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

    private Address findEntity(Long id, Address address){
        return addressRepository.findIdAndAddress(address, id).orElseThrow(()->new EntityNotFoundException(Address.class,id));
    }

    public void delete(Long id, Address address){
        Address entity =findEntity(id, address);
        addressRepository.delete(entity);
    }


}
