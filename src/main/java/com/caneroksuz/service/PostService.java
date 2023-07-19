package com.caneroksuz.service;

import com.caneroksuz.repository.ICrud;
import com.caneroksuz.repository.PostRepository;
import com.caneroksuz.repository.entity.Post;

import java.util.List;
import java.util.Optional;

public class PostService implements ICrud<Post> {

    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new PostRepository();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public Post deleteById(Long id) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }
}
