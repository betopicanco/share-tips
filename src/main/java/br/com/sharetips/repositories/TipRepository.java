package br.com.sharetips.repositories;

import br.com.sharetips.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> { }
