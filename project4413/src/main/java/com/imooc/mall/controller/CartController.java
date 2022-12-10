package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.filter.UserFilter;
import com.imooc.mall.model.vo.CartVO;
import com.imooc.mall.service.CartService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Shopping Cart Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/list")
    @ApiOperation("Shopping Cart List")
    public ApiRestResponse list() {
        //Obtain user IDs internally to prevent Broken access control
        List<CartVO> cartList = cartService.list(UserFilter.userThreadLocal.get().getId());
        return ApiRestResponse.success(cartList);
    }

    @PostMapping("/add")
    @ApiOperation("Add products to cart")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.add(UserFilter.userThreadLocal.get().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/update")
    @ApiOperation("Update Shopping Cart")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService
                .update(UserFilter.userThreadLocal.get().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/delete")
    @ApiOperation("Delete Shopping Cart")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        //Can't pass in userID, cartID, otherwise you can delete other people's shopping cart
        List<CartVO> cartVOList = cartService.delete(UserFilter.userThreadLocal.get().getId(), productId);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/select")
    @ApiOperation("Select/unselect an item in the shopping cart")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected) {
        //Can't pass in userID, cartID, otherwise you can delete other people's shopping cart
        List<CartVO> cartVOList = cartService
                .selectOrNot(UserFilter.userThreadLocal.get().getId(), productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    @ApiOperation("Select all/select none of an item in the shopping cart")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        //Can't pass in userID, cartID, otherwise you can delete other people's shopping cart
        List<CartVO> cartVOList = cartService
                .selectAllOrNot(UserFilter.userThreadLocal.get().getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}
