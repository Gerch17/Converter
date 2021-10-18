package ru.gerch17.converter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerch17.converter.entity.History;

import java.sql.Date;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    Iterable<History> findAllByUserName(String str);
    Iterable<History> findAllByOutValute(String str);
    Iterable<History> findAllByInValute(String str);
    Iterable<History> findAllByDate(Date date);
    Iterable<History> findAllByInValuteAndOutValute(String str1, String str2);
    Iterable<History> findAllByOutValuteAndAndInValuteAndDate(String str1, String str2, Date date);
    Iterable<History> findAllByInValuteAndDate(String str1, Date date);
    Iterable<History> findAllByOutValuteAndDate(String str1, Date date);
}
