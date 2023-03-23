package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFountException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();
    private Integer id = 0;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }
    public Post findPostById(Integer postId) {
        for (Post post : posts) {
            if (post.getId() == postId) {
                return post;
            }
        }
        throw new PostNotFountException("Пост с номером " + postId + " не найден");
    }
    
    public List<Post> findAll(Integer size, Integer from, String sort) {

        return posts;
    }
    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }
        post.setId(++id);
        posts.add(post);
        return post;
    }
}