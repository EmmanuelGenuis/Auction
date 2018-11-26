package com.codingdojo.Auctions.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.Auctions.models.Auction;
import com.codingdojo.Auctions.models.User;


@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {
    User findByname(String name);
    List<Auction> findAll();
    
}
