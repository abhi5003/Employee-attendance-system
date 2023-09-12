package com.abhi.swipemicroservices.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.swipemicroservices.dto.SwipeEventRequestDTO;
import com.abhi.swipemicroservices.services.SwipeEventService;

@RestController
@RequestMapping("/employee")
public class SwipeEventController {

	@Autowired
	private SwipeEventService swipeEventService;
   
    @PostMapping("/swipe")
    public ResponseEntity<String> createSwipeEvent(@RequestBody SwipeEventRequestDTO requestDTO) {
        swipeEventService.processSwipeEvent(requestDTO);
        
        // Return a success response
        return ResponseEntity.ok("SwipeEvent created successfully");
    }
    
    
    
    
}
