package com.shopshoe.repository;

import com.shopshoe.beans.User;
import com.shopshoe.beans.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUserName(String userName);

    void deleteByUserNameAndMasp(String userName, String masp);
}
