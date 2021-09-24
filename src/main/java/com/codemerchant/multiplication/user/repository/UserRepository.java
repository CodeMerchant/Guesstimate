package com.codemerchant.multiplication.user.repository;

import com.codemerchant.multiplication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @return a user if found, else return an optional object if no matching user is found matching the passed alias
     */
    Optional<User> findByAlias(final String alias);
}
