package com.example.shop.repo;

import com.example.shop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
Optional<Address>findIdAndAddress(Address address, Long id);
Address findByName(String name);

}
