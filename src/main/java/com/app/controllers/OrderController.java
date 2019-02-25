package com.app.controllers;

import com.app.model.order.Order;
import com.app.service.AddressService;
import com.app.service.OrderService;
import com.app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private UserService userService;
    private AddressService addressService;


    public OrderController(OrderService orderService, UserService userService, AddressService addressService) {
        this.orderService = orderService;
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/orderPhone/{phoneId}")
    public String addOrderGet(@PathVariable Long phoneId) {
        orderService.addOrder(userService.getLoggedUserId(), phoneId);
        return "redirect:/order/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        model.addAttribute("orders", orderService.getAllUnApprovedOrdersFromUser(userService.getLoggedUserId()));
        model.addAttribute("price", orderService.invoicePrice());
        return "order/shoppingCart";
    }

    @GetMapping("/decreaseQuantity/{orderId}")
    public String decreaseQuantity(@PathVariable Long orderId) {
        orderService.decreaseQuantity(orderId);
        return "redirect:/order/shoppingCart";
    }

    @GetMapping("/increaseQuantity/{orderId}")
    public String increaseQuantity(@PathVariable Long orderId) {
        orderService.increaseQuantity(orderId);
        return "redirect:/order/shoppingCart";
    }

    @GetMapping("/orderDetails")
    public String orderDetailsGet(Model model) {
        model.addAttribute("user", userService.getLoggedUser());
        model.addAttribute("address", addressService.findAddress(userService.getLoggedUserId()));
        model.addAttribute("orders", orderService.getAllUnApprovedOrdersFromUser(userService.getLoggedUserId()));
        model.addAttribute("price", orderService.invoicePrice());
        return "order/orderDetails";
    }

    @GetMapping("/confirmOrder")
    public String confirmOrder()
    {
        orderService.changeOrdersToApproved(userService.getLoggedUserId());
        return "roles/user";
    }

    @GetMapping("/orderHistory")
    public String orderHistoryGet(Model model) {
        model.addAttribute("orders", orderService.getAllApprovedOrdersFromUser(userService.getLoggedUserId()));
        return "order/orderHistory";
    }

    @GetMapping("/order/{id}")
    public String orderGet(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findOneById(id));
        return "order/order";
    }





}
