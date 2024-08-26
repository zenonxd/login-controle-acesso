package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT obj FROM Product obj " +
            "WHERE upper(obj.name) LIKE upper(concat('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);
}
