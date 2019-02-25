package com.app.repository;

import com.app.model.phone.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneProfileRepository extends JpaRepository<Profile, Long>
{
    Optional<Profile> findDistinctByName(String profileName);
    Optional<Profile> findById(long id);
}
