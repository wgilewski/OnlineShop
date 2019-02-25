package com.app.repository;

import com.app.model.phone.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
