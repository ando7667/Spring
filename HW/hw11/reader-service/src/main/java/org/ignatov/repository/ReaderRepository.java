package org.ignatov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ignatov.Reader;


@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
