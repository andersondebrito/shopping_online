package com.abo.user.repository;

import com.abo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdentification(String identification);
    List<User> queryByNameLike(String name);

}
