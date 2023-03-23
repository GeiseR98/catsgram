package ru.yandex.practicum.catsgram.exception;

public class PostNotFountException extends RuntimeException{
    public PostNotFountException(String message) {
        super(message);
    }
}
