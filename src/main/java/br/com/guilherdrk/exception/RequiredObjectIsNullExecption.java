package br.com.guilherdrk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullExecption extends RuntimeException {



    public RequiredObjectIsNullExecption() {
        super("It is not allowed to persist a null object");
    }
    public RequiredObjectIsNullExecption(String message) {
        super(message);
    }
}
