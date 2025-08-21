package br.com.guilherdrk.repositories;

import br.com.guilherdrk.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
