package kr.co.inseok.mapapi.framework.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResult> illegalExHandle(IllegalArgumentException e) {
        log.error("IllegalArgumentException 에러 : {}", ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(ErrorResult.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResult> globalExHandle(Throwable e) {
        log.error("Throwable 에러 : {}", ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(ErrorResult.builder()
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errorMessage(e.getMessage())
                        .build());
    }
}
