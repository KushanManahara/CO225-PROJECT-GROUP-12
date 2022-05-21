package com.bitmart.bitmartserver.controller;

import com.bitmart.bitmartserver.model.user.auction.Auction;
import com.bitmart.bitmartserver.model.user.auction.AuctionDao;
import com.bitmart.bitmartserver.model.authentication.AuthenticationAccept;
import com.bitmart.bitmartserver.model.authentication.AuthenticationRequest;
import com.bitmart.bitmartserver.model.bid.Bid;
import com.bitmart.bitmartserver.model.bid.BidDao;
import com.bitmart.bitmartserver.model.initialvalue.InitialValue;
import com.bitmart.bitmartserver.model.initialvalue.InitialValueDao;
import com.bitmart.bitmartserver.model.user.User;
import com.bitmart.bitmartserver.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    // Data access objects
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuctionDao auctionDao;
    @Autowired
    private InitialValueDao initialValueDao;

    @Autowired
    private BidDao bidDao;

    // Post Mappings
    @PostMapping("/login")
    public AuthenticationAccept login(@RequestBody AuthenticationRequest authenticationRequest){

        System.out.println("new login --- " + authenticationRequest.getEmail() + " -- " + authenticationRequest.getPassword());
        AuthenticationAccept authenticationAccept = new AuthenticationAccept();

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        List<User> users = userDao.getAllUsers();

        for (User user:users) {
            if (user.getEmail().equals(authenticationRequest.getEmail()) && encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                authenticationAccept.setUserID(user.getUserId());
                authenticationAccept.setAuthorized(true);
                System.out.println(authenticationAccept.getUserID() + " -- ");
                return authenticationAccept;
            }
        }
        System.out.println(authenticationAccept.getUserID() + " -- ");
        return authenticationAccept;
    }

    @PostMapping("/save/initial-value")
    public String saveInitialValue(@RequestBody InitialValue initialValue){
        System.out.println("place initial value"  + initialValue.getSymbol());
        initialValueDao.save(initialValue);

        return "successfully set initial value";
    }

    @PostMapping("/has-init-val")
    public InitialValue hasInitValue(@RequestBody InitialValue initialValue){
        System.out.println("initial value requested -- " + initialValue.getSymbol());
        InitialValue newInitialValue = new InitialValue();
        if(initialValueDao.existByID(initialValue.getSymbol())){
            newInitialValue = initialValueDao.getInitValue(initialValue.getSymbol());
            newInitialValue.setHasInitial(true);
        }
        else{
            newInitialValue = initialValue;
        }

        System.out.println("initial value sent -- " + newInitialValue.getSymbol() + "--" + newInitialValue.getInitialValue());
        return newInitialValue;
    }

    @PostMapping("save/bid")
    public String saveBid(@RequestBody Bid bid){
        System.out.println("successfully place the bid -- " + bid.getSymbol());
        bidDao.save(bid);

        return "Successfully saved";
    }



    // Get Mappings
    @GetMapping("/users/get-all")
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @GetMapping("/auctions/get-on-going")
    public Auction getAllAuctions(){
        System.out.println("Requested ongoing Auctions");
        return auctionDao.getAllAuctions();
    }

    @GetMapping("/has")
    public InitialValue hasInitValue(){
        InitialValue newInitialValue = new InitialValue();
        newInitialValue = initialValueDao.getInitValue("BTC");
        return newInitialValue;
    }

}
