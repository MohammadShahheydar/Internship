package mohammad.shahheydar.internshipprocessmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String , String>> handleException(ResponseStatusException ex){
        Map<String , String> map = new HashMap<>();
        map.put("error" , ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(map);
    }
}
