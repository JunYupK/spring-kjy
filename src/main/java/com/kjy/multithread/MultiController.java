package com.kjy.multithread;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multithread")
public class MultiController {
    @GetMapping("{id}/like")
    public ResponseEntity<?> getMyLike(){
        return ResponseEntity.ok("좋아용");
    }
}
