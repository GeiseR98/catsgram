package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFountException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return posts.stream().sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate());
            if (sort.equals("desc")) {
                comp = -1 * comp;
            }
            return comp;
        }).skip(from).limit(size).collect(Collectors.toList());
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