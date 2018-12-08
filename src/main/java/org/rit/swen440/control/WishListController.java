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

    public List<WishList> getWishList(User user){
        List<WishList> allWishLists = getAllWishLists();
        List<WishList> usersWishList = new ArrayList<>();
        for (WishList wish : allWishLists){
            if (wish.getUserId().getId() == user.getId()){
                usersWishList.add(wish);
            }
        }
        return usersWishList;
    }

    public List<WishList> getAllWishLists(){
        return WishListRepository.getAllRecords();
    }

    public void updateWishList(WishList wishlist){
        WishListRepository.updateRecord(wishlist);
    }
}
