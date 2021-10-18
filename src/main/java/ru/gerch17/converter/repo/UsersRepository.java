package ru.gerch17.converter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerch17.converter.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
}
