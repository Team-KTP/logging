package org.kwakmunsu.logging.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts/{userId}")
    public ResponseEntity<Void> create(
            @PathVariable Long userId,
            @RequestBody PostCreateRequest request
    ) {
        postService.create(userId, request.title(), request.content());

        log.trace("PostCreate {}", request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {

        return ResponseEntity.ok(postService.getPost(id));
    }

}
