package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final PostRepository repository;
    private final List<Long> listId = new ArrayList<>();
    private long countId = 1;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            validationId();
            post.setId(countId);
            countId++;

            return repository.save(post);
        } else {
            try {
                var postToId = getById(post.getId());
                postToId.setContent(post.getContent());
                return postToId;
            } catch (NotFoundException ex) {
                listId.add(post.getId());
                return repository.save(post);
            }
        }
    }

    private void validationId() {
        while (listId.contains(countId)) {
            countId++;
        }
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

