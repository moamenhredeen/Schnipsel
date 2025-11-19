package me.moamenhredeen.kakera.repository;

import me.moamenhredeen.kakera.model.Snippet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SnippetRepository extends PagingAndSortingRepository<Snippet, Long>, CrudRepository<Snippet, Long> {

}
