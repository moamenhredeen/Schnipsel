package me.moamenhredeen.kakera.repository;

import me.moamenhredeen.kakera.model.Snippet;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SnippetRepository extends PagingAndSortingRepository<Snippet, Long>, CrudRepository<Snippet, Long>, JpaSpecificationExecutor<Snippet> {

    @Query("SELECT s FROM Snippet s LEFT JOIN s.comments WHERE s.id = ?1")
    Optional<Snippet> findByIdWithComments(Long id);

}
