package ru.netology.utils;

import ru.netology.model.Post;
import ru.netology.model.PostDTO;

public class MappingUtils {
    public static PostDTO mapToPostDTO(Post post){
        return new PostDTO(post.getId(), post.getContent());
    }
    public static Post mapToPost(PostDTO postDTO){
        return new Post(postDTO.getId(), postDTO.getContent());
    }
}
