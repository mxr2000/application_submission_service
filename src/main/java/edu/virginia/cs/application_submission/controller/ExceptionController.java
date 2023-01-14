package edu.virginia.cs.application_submission.controller;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.SeasonDoesNotExistException;
import edu.virginia.cs.application_submission.exception.UpdateNoEffectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException() {
        return ResponseEntity.badRequest().body("bad format");
    }

    @ExceptionHandler(SeasonDoesNotExistException.class)
    public ResponseEntity<String> handleSeasonNotFoundException() {
        return ResponseEntity.badRequest().body("season not found");
    }

    @ExceptionHandler(ApplicationDoesNotExistException.class)
    public ResponseEntity<String> handleApplicationNotFoundException() {
        return ResponseEntity.badRequest().body("application not found");
    }

    @ExceptionHandler(UpdateNoEffectException.class)
    public ResponseEntity<String> handleUpdateNotFoundException() {
        return ResponseEntity.badRequest().body("update failed");
    }
}
