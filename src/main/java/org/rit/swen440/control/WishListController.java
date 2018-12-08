package org.rit.swen440.control;

import org.rit.swen440.dataLayer.User;
import org.rit.swen440.dataLayer.WishList;
import org.rit.swen440.repository.WishListRepository;
import java.util.ArrayList;
import java.util.List;

public class WishListController {

    public void createWishList(WishList wishlist){
        WishListRepository.createRecord(wishlist);
    }

    public WishList getWishList(User user){
        List<WishList> allWishLists = getAllWishLists();
        for (WishList wish : allWishLists){
            if (wish.getUserId().getId() == user.getId()){
                return wish;
            }
        }
        return null;
    }

    public List<WishList> getAllWishLists(){
        return WishListRepository.getAllRecords();
    }

    public void updateWishList(WishList wishlist){
        WishListRepository.updateRecord(wishlist);
    }
}
