package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.PostDTO;
import ru.netology.repository.PostRepository;
import ru.netology.utils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDTO> all() {
        return repository.all().stream().filter(p -> !p.isRemove())
                .map(MappingUtils::mapToPostDTO).collect(Collectors.toList());
    }

    public PostDTO getById(long id) {
        var post = repository.getById(id).filter(p->!p.isRemove()).orElseThrow(NotFoundException::new);
        return MappingUtils.mapToPostDTO(post);
    }

    public PostDTO save(PostDTO postDTO) {
        var post = repository.getById(postDTO.getId());
        if(post.isPresent()){
            if(!post.get().isRemove()){
                post.get().setContent(postDTO.getContent());
                return MappingUtils.mapToPostDTO(repository.save(post.get()));
            } else {
                throw new NotFoundException("this post was deleted");
            }
        } else {
            return MappingUtils.mapToPostDTO(repository.save(MappingUtils.mapToPost(postDTO)));
        }

    }


    public void removeById(long id) {
        repository.removeById(id);
    }
}

