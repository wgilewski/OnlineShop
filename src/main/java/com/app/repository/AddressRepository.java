package com.app.repository;

import com.app.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();
    Optional<Address> findAddressByUserId(long userId);

}
