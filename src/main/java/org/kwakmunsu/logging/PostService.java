package org.kwakmunsu.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void create(Long userId, String title, String content) {
        Post post = Post.create(userId, title, content);

        postRepository.save(post);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

}