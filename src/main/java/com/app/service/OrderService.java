package com.app.service;

import com.app.exceptions.MyException;
import com.app.model.order.Order;
import com.app.model.phone.Phone;
import com.app.repository.OrderRepository;
import com.app.repository.PhoneRepository;
import com.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private PhoneRepository phoneRepository;
    private UserService userService;
    private UserRepository userRepository;



    public OrderService(OrderRepository orderRepository, PhoneRepository phoneRepository, UserService userService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.phoneRepository = phoneRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Order findOneById(Long id) {
        return orderRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void addOrder(Long userId, Long phoneId)
    {
        BigDecimal price = phoneRepository.findById(phoneId).get().getPrice();

        if (phoneRepository.findById(phoneId).get().getAvailable() == 0)
        {
            throw new MyException("TEN TELEFON JEST JUZ NIEDOSTEPNY",LocalDateTime.now());
        }

        if (orderRepository.findAll().isEmpty())
        {
            Phone phone = phoneRepository.findById(phoneId).orElseThrow(NullPointerException::new);
            phone.setAvailable(phone.getAvailable()-1);
            orderRepository.save(Order.builder()
                    .phone(phone)
                    .user(userRepository.findById(userId).orElseThrow(NullPointerException::new))
                    .approved(false)
                    .date(LocalDate.now())
                    .quantity(1)
                    .price(price)
                    .build());
        } else {
            Order order = findOrderByUserAndPhoneId(phoneId, userId);
            if (order != null && order.isApproved() != true) {
                increaseQuantity(order.getId());
            } else {
                Phone phone = phoneRepository.findById(phoneId).orElseThrow(NullPointerException::new);
                phone.setAvailable(phone.getAvailable()-1);
                orderRepository.save(Order.builder()
                        .phone(phone)
                        .user(userRepository.findById(userId).orElseThrow(NullPointerException::new))
                        .approved(false)
                        .date(LocalDate.now())
                        .quantity(1)
                        .price(price)
                        .build());
            }
        }

    }

    public void increaseQuantity(Long orderId)
    {


        Order order = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);

        if (order.getPhone().getAvailable() == 0)
        {
            throw new MyException("TO BYLA OSTATNIA SZTUKA TEGO MODELU, PROSIMY SPRÓBOWA PÓŹNIEJ",LocalDateTime.now());
        }


        order.setQuantity(order.getQuantity() + 1);
        order.getPhone().setAvailable(order.getPhone().getAvailable() - 1);
        order.setPrice(BigDecimal.valueOf(order.getQuantity() * phoneRepository.findById(order.getPhone().getId()).orElseThrow(NullPointerException::new).getPrice().intValue()));
        orderRepository.save(order);


    }

    public void decreaseQuantity(Long orderId)
    {

        Order order = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);


        order.setQuantity(order.getQuantity() - 1);
        order.getPhone().setAvailable(order.getPhone().getAvailable() + 1);
        order.setPrice(BigDecimal.valueOf(order.getQuantity() * phoneRepository.findById(order.getPhone().getId()).orElseThrow(NullPointerException::new).getPrice().intValue()));
        if (order.getQuantity() == 0)
        {
            orderRepository.deleteById(orderId);
        } else {
            orderRepository.save(order);
        }

    }


    public List<Order> getAllUnApprovedOrdersFromUser(Long userId) {
        return getAllOrdersFromUser(userId).stream().filter(order -> order.isApproved() == false).collect(Collectors.toList());
    }

    public List<Order> getAllApprovedOrdersFromUser(Long userId) {
        return getAllOrdersFromUser(userId).stream().filter(order -> order.isApproved() == true).collect(Collectors.toList());
    }

    public void changeOrdersToApproved(Long userId) {
        orderRepository
                .findAll()
                .stream()
                .filter(order -> order.getUser().getId().equals(userId) && !order.isApproved())
                .forEach(order -> {
                    order.setApproved(true);
                    orderRepository.save(order);
                });
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllOrdersFromUser(Long userId)
    {
        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public double invoicePrice()
    {
        return getAllUnApprovedOrdersFromUser(userService.getLoggedUserId())
                .stream()
                .collect(Collectors.summingDouble(o -> o.getPrice().doubleValue()))
                .doubleValue();
    }



    public Order findOrderByUserAndPhoneId(Long phoneId, Long userId) {

        if (phoneId == null) {
            throw new MyException("NIE MA TAKIEGO TELEFONU",LocalDateTime.now());
        }

        if (userId == null) {
            throw new MyException("NIE MA TAKIEGO UZYTKOWNIKA",LocalDateTime.now());
        }

        if (orderRepository.findAll() == null) {
            throw new MyException("NIE MA ZADNYCH ZAMOWIEN",LocalDateTime.now());
        }

        return orderRepository
                .findByUserIdAndPhoneId(userId, phoneId)
                .orElseThrow(() -> new MyException("",LocalDateTime.now()));
    }

}
