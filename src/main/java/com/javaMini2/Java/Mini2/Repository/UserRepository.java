package com.javaMini2.Java.Mini2.Repository;

import com.javaMini2.Java.Mini2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

