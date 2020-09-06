package telran.ashkelon2020.person.repository;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	Stream<Person> findByName(String name);
	
	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
}
