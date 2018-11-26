package com.codingdojo.Auctions.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.codingdojo.Auctions.models.Auction;
import com.codingdojo.Auctions.models.User;
import com.codingdojo.Auctions.repositories.AuctionRepository;



@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }
    public User findByname(String name) {
        return auctionRepository.findByname(name);
    }
    public Auction findTask(Long id) {
        Optional<Auction> optionalBook = auctionRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
	public Auction createTask( Auction task) {
		// TODO Auto-generated method stub
		return auctionRepository.save(task) ;
	}
	public List<Auction> findAll() {
		return  auctionRepository.findAll();
	}
	public void deleteAuction(Long id) {
		// TODO Auto-generated method stub
		auctionRepository.deleteById(id);
	}
	public void updateAuction( Auction auction) {
		// TODO Auto-generated method stub
		 auctionRepository.save(auction);
	}
}
