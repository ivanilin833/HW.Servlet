package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final Map<Long, Post> posts;
    private final AtomicLong id = new AtomicLong(1);

    public PostRepository() {
        this.posts = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return posts.values().stream().toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {

            while (posts.containsKey(id.get())) {
                id.incrementAndGet();
            }

            post.setId(id.getAndIncrement());
            posts.put(post.getId(), post);
            return post;
        } else {
            var postFromDb = getById(post.getId());
            if (postFromDb.isPresent()) {
                postFromDb.get().setContent(post.getContent());
                posts.put(post.getId(), postFromDb.get());
                return postFromDb.get();
            } else {
                posts.put(post.getId(), post);
                return post;
            }
        }
    }

    public void removeById(long id) {
        var post = getById(id);
        post.ifPresent(post1 -> posts.remove(post1.getId()));
    }
}
