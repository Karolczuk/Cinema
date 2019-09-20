package com.app.cinema.repository;

import com.app.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

   // UserDto save(UserDto userDto);

}
