package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/*
方法命名規則：https://blog.csdn.net/sbin456/article/details/53304148
1. 查詢方法以 find | read | get 開頭
2. 涉及條件查詢時，條件的屬性(首字母大寫)用條件關鍵字連結
3. 若要使用集聯屬性，則屬性之間使用 _ 進行連結
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 根據 name 來取得 User
    User getByName(String name);
    
    // Where name LIKE ?% AND id < ?
    List<User> getByNameStartingWithAndIdLessThan(String name, Long id);
        
    // Where name LIKE ?% AND id >= ?
    List<User> getByNameStartingWithAndIdGreaterThanEqual(String name, Long id);
    
    // Where id in (?, ?, ?) OR birth < ?
    List<User> getByIdInOrBirthLessThan(List<Long> ids, Date birth);
    
    // Where birth >= ? And birth <= ?
    List<User> getByBirthGreaterThanEqualAndBirthLessThanEqual(Date birthBegin, Date birthEnd);
    
    // Where birth between ?(含) and ?(含)
    List<User> getByBirthBetween(Date birthBegin, Date birthEnd);
    
    // 查詢 id 值最大的 User
    @Query("SELECT u FROM User u WHERE u.id = (SELECT MAX(u2.id) FROM User u2)")
    User getMaxIdUser();
    
    // 查詢 User + Age > ?
    @Query("SELECT u FROM User u WHERE (YEAR(CURRENT_DATE)-YEAR(u.birth)) >= :age")
    //@Query("SELECT u FROM User u WHERE (YEAR(CURRENT_DATE)-YEAR(u.birth)) >= ?1") // ?1 表示方法中第一個參數, 其它用法 LIKE %?1%
    List<User> getUserByAge(Integer age);
    
    @Query(value = "SELECT count(id) FEOM T_SQL", nativeQuery = true)
    long getTotalCount();
}
