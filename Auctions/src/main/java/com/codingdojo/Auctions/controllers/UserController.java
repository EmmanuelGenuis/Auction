package com.codingdojo.Auctions.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.Auctions.models.Auction;
import com.codingdojo.Auctions.models.User;
import com.codingdojo.Auctions.services.AuctionService;
import com.codingdojo.Auctions.services.UserService;
import com.codingdojo.Auctions.validator.UserValidator;





@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator ;
    private final AuctionService auctionService ;

    
    public UserController(UserService userService, UserValidator userValidator, AuctionService auctionService) {
        this.userService = userService;
        this.userValidator = userValidator ;
        this.auctionService=auctionService ;
       
    }
    
    @GetMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registration.jsp";
    }
  
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
			return "registration.jsp" ;
		}
    	user.setWallet(1000);
    	User u = userService.registerUser(user) ;
    	session.setAttribute("userid", u.getId());
    	session.setAttribute("username", u.getFirstname());
    	return "redirect:/home" ;
    	
        // if result has errors, return the registration page (don't worry about validations just now)
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password,  Model model, HttpSession session) {
    	boolean isAuthenticated =userService.authenticateUser(username, password);
		if(!isAuthenticated) {
			model.addAttribute("errors","Invalid credentials. Please try again.");
			
			return "redirect:/" ;
		}else {
			User u= userService.findByUsername(username) ;
			session.setAttribute("userid", u.getId());
			session.setAttribute("username", u.getFirstname());
			return "redirect:/home" ;
		}
    }
    @RequestMapping("/home")
    public String login(HttpSession session,Model model) {
    	Long userId = (Long) session.getAttribute("userid") ;
    	User u= userService.findUserById(userId) ;
    	model.addAttribute("user",u) ;
    	 List<Auction> auctions = auctionService.findAll();
         model.addAttribute("auctions", auctions);
     
        return "home.jsp";
    }
    @GetMapping("/new")
    public String task( @ModelAttribute("auction") Auction auction,Model model) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate localDate = LocalDate.now();
    	model.addAttribute("date", localDate) ;
    	return "new.jsp";	
    }
    @PostMapping("/newAuction")
    public String addTask(@Valid @ModelAttribute("auction") Auction auction, BindingResult result, HttpSession session,Model model) {
    	
    	if(result.hasErrors()) {
			return "new.jsp" ;
		}
    	
    	
    	Date currentDate = new Date() ;
    	currentDate.getTime() ;
    	
    	long difference =  (auction.getEndDate().getTime()-currentDate.getTime())/86400000;
    	Math.abs(difference) ;
    	System.out.println(difference) ;
    	
    	
    	auction.setDifference(difference);
    	
    	Long id= (Long) session.getAttribute("userid") ;
    	User user = userService.findUserById(id) ;
    	auction.setSeller(user.getFirstname());
    	auction.setHighestBid(user.getFirstname()+" "+user.getLastname());
    	Auction t = auctionService.createTask(auction) ;
    	
    	return "redirect:/home" ;
    	
        // if result has errors, return the registration page (don't worry about validations just now)
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    }
    @GetMapping("/auction/show/{id}")
    public String show(@PathVariable("id") Long id, Model model,HttpSession session) {
        Auction auction= auctionService.findTask(id);
        String username = (String) session.getAttribute("username") ;
        model.addAttribute("username", username) ;
        model.addAttribute("auction", auction);
        return "show.jsp";
    }
    @RequestMapping("/bid/{id}")
    public String bidding(@PathVariable("id") Long id,@RequestParam("username") int username,HttpSession session,Model model) {
    	Long userid= (Long) session.getAttribute("userid") ;
    	Auction auction= auctionService.findTask(id);
    	User user = userService.findUserById(userid) ;
    	auction.setHighestBid(user.getFirstname()+" "+user.getLastname());
    	if(auction.getBid()<username) {
    		auction.setBid(username);	
    	}
    	else {
    		
    	}
    	user.setWallet(user.getWallet()-username);
    	userService.updateUser(user);
    	auctionService.updateAuction(auction) ;
		return "redirect:/home";
		   
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
    	Auction auction= auctionService.findTask(id);
    	auctionService.deleteAuction(id);
    	return "redirect:/home" ;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
		return "redirect:/";
        // invalidate session
        // redirect to login page
    }

}