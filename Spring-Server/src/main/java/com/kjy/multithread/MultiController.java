package com.kjy.multithread;

import com.kjy.DAO.Ticket;
import com.kjy.database.TicketRepository;
import com.kjy.database.UserEntity;
import com.kjy.database.UserRepository;
import com.kjy.service.TicketDecreaseService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/multithread")
public class MultiController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    TicketDecreaseService ts;
    @GetMapping("{id}/check")
    public UserEntity check(@PathVariable int id){
        UserEntity user = userRepository.findById(id);
        System.out.println(user.getId());
        ArrayDeque<Integer> q = new ArrayDeque<>();
        return user;
    }

//    @PostMapping("{id}/deposit")
//    public ResponseEntity<?> deposit(@PathVariable int id, @RequestBody long balance){
//        UserEntity user = userRepository.findById(id);
//        if(user != null){
//            user.setBalance(user.getBalance() + balance);
//            userRepository.save(user);
//            return ResponseEntity.ok("입금 성공!");
//        }
//        return ResponseEntity.notFound().build();
//    }
    @PostMapping("/makeTicket")
    public ResponseEntity<?> makeTicket(){
        Ticket ticket = new Ticket("데이식스", 100L);
        ticketRepository.save(ticket);
        return ResponseEntity.ok(ticket);
    }
    @GetMapping("/{id}/ticket")
    public ResponseEntity<?> checkTicket(@PathVariable Long id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ResponseEntity.ok(ticket);
    }
    @PostMapping("/ticket")
    public ResponseEntity<?> issueTicket(){
        Ticket t = ts.ticketDecrease(2L);
        return ResponseEntity.ok(t);
    }
//    @PostMapping("/ticket/safe")
//    public Long testTicket() throws InterruptedException {
//        int numberOfThreads = 10000;
//        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
//        for (int i = 0; i < numberOfThreads; i++) {
//            service.submit(ticket::decrease);
//        }
//        service.shutdown();
//        service.awaitTermination(1, TimeUnit.MINUTES);
//        return ticket.getAvailableStock();
//    }
}
