package com.sadiar.insurancemangement.repository;

import com.sadiar.insurancemangement.entity.FirePolicy;
import com.sadiar.insurancemangement.entity.User;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserReporisitory extends JpaRepository<FirePolicy,Long> {

    Optional<User> findByEmail(String email);
}
