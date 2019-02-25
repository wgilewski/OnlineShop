package com.app.repository;

import com.app.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByPhoneId(Long id);
    Optional<Order> findByUserIdAndPhoneId(Long userId, Long phoneId);

}
