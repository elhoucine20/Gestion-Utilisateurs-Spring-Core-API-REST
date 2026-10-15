package com.userApi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice //
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // va recuperer les champs que ne pas valider par @Valid avec message du error
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request){
        Map<String,String> fieldErrors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e->fieldErrors.put(e.getField(),e.getDefaultMessage()));
        Map<String , Object> body = build(HttpStatus.BAD_REQUEST,"Donnees invalides",request);
        body.put("fieldErrors",fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    // va recuperer ResponseStatusException 404 ou 409
    public ResponseEntity<Map<String, Object>> handleStatus(ResponseStatusException ex, HttpServletRequest req) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        return ResponseEntity.status(status).body(build(status, ex.getReason(), req));
    }


    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    // (bad request) mauvais JSON ,ou role contient dans enum ,ou type de id => transfere 500 vers 400
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(build(HttpStatus.BAD_REQUEST, "Requete invalide (JSON, role ou id incorrect)", req));
    }

    @ExceptionHandler(Exception.class)
    // les autre errors de spring 415-405  (on handle le reel error par cette lethod)
    public ResponseEntity<Map<String, Object>> handleOther(Exception ex, HttpServletRequest req) {
        HttpStatus status = (ex instanceof ErrorResponse er)
                ? HttpStatus.valueOf(er.getStatusCode().value())
                : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = status.is5xxServerError() ? "Erreur interne du serveur" : ex.getMessage();
        return ResponseEntity.status(status).body(build(status, message, req));
    }

    private Map<String, Object> build(HttpStatus status, String message, HttpServletRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", req.getRequestURI());
        return body;
    }


}
