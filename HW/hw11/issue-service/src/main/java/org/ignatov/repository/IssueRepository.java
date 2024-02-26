package org.ignatov.repository;

import org.ignatov.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT I FROM Issues I WHERE I.id = :id")
    List<Issue> findIssueByReaderId(long id);
}
