package pl.devapo.product.listening.security;

public class BadTokenException extends RuntimeException {
    BadTokenException(String msg) {
        super(msg);
    }
}