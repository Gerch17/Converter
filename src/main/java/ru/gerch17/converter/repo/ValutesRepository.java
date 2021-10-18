package ru.gerch17.converter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerch17.converter.entity.Valutes;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface ValutesRepository extends JpaRepository<Valutes, Integer> {
    Valutes findById(int i);
    Valutes findValutesByValuteName(String str);
    Valutes findValutesByValuteText(String str);
}
