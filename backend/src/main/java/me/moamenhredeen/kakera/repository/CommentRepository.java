package me.moamenhredeen.kakera.repository;

import me.moamenhredeen.kakera.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, CrudRepository<Comment, Long> {
    Page<Comment> findBySnippetId(Long snippetId,
                                  Pageable pageable);
}
