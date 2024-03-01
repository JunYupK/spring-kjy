package com.kjy.multithread;

import com.kjy.database.UserEntity;
import com.kjy.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/multithread")
public class MultiController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("{id}/check")
    public UserEntity check(@PathVariable int id){
        UserEntity user = userRepository.findById(id);
        System.out.println(user.getId());
        return user;
    }

    @PostMapping("{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable int id, @RequestBody long balance){
        UserEntity user = userRepository.findById(id);
        if(user != null){
            user.setBalance(user.getBalance() + balance);
            userRepository.save(user);
            return ResponseEntity.ok("입금 성공!");
        }
        return ResponseEntity.notFound().build();
    }
}
