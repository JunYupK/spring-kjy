package com.kjy.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long availableStock;
    public Ticket(String name, Long availableStock){

        this.name = name;
        this.availableStock = availableStock;
    }

    public synchronized void generalDecrease(){
        validateStockCount();
        this.availableStock -= 1;
    }
    public synchronized void SyncDecrease(){
        validateStockCount();
        this.availableStock -= 1;
    }
    public void LockDecrease(){
        final Lock lock = new ReentrantLock();
        lock.lock();
        validateStockCount();
        try{
            this.availableStock -= 1;
        } finally {
            lock.unlock();
        }

    }
    private void validateStockCount(){
        if(availableStock < 1){
            throw new IllegalArgumentException();
        }
    }
}
