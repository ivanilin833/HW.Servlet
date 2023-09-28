package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostRepository {
    private final List<Post> posts;

    public PostRepository() {
        this.posts = new CopyOnWriteArrayList<>();
    }

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Post save(Post post) {
        posts.add(post);
        return post;
    }

    public void removeById(long id) {
        var post = getById(id);
        post.ifPresent(posts::remove);
    }
}
