package com.kjy.database;

import com.kjy.DAO.Ticket;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.availableStock = t.availableStock - 1 WHERE t.id = :id AND t.availableStock > 0")
    int decreaseStockById(@Param("id") Long id);
}
