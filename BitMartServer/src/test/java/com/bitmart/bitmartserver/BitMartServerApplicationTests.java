package com.bitmart.bitmartserver;

import com.bitmart.bitmartserver.model.user.auction.Auction;
import com.bitmart.bitmartserver.model.user.auction.AuctionDao;
import com.bitmart.bitmartserver.model.bid.Bid;
import com.bitmart.bitmartserver.model.bid.BidDao;
import com.bitmart.bitmartserver.model.initialvalue.InitialValue;
import com.bitmart.bitmartserver.model.initialvalue.InitialValueDao;
import com.bitmart.bitmartserver.model.user.User;
import com.bitmart.bitmartserver.model.user.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Timestamp;

@SpringBootTest
class BitMartServerApplicationTests {

	@Test
	void contextLoads() {
		addUserTest();
		auctionTest();
		initTest();
		bidTest();
	}

	@Autowired
	UserDao userDao;

	@Autowired
	InitialValueDao initialValueDao;

	@Autowired
	BidDao bidDao;

	@Autowired
	AuctionDao auctionDao;

	void addUserTest() {
		User user = new User();

		user.setFullName("Dhananjaya weerasinghe");
		user.setEmail("sample.com");
		user.setPassword("123");
		user.setBidCount(12);
		user.setAccBalance(1000);

		userDao.save(user);
	}
	void auctionTest(){
		Auction auction = new Auction();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		auction.setStartTime(timestamp);
		auction.setEndTime(timestamp);
		auction.setAuctionStatus("OnGoing");

		auctionDao.save(auction);
	}

	void initTest(){
		InitialValue initialValue = new InitialValue();

		initialValue.setInitialValue(1000);
		initialValue.setSymbol("BTC");

		initialValueDao.save(initialValue);
	}

	void bidTest(){
		Bid bid = new Bid();

		bid.setSymbol("BTC");
		bid.setAmount(1000);
		bid.setUserID(1);

		bidDao.save(bid);
	}

}
