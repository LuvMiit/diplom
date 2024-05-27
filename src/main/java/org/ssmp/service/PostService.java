package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.Post;
import org.ssmp.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post getPostByName(String name){
        return postRepository.findByPostName(name);
    }
}
