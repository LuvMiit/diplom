package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.Post;
import org.ssmp.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPostList(){
        return postRepository.findAll();
    }
    public Post getPostByName(String name){
        return postRepository.findByPostName(name);
    }
}
