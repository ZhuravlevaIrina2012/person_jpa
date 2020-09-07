package telran.ashkelon2020.person.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.ashkelon2020.person.dto.CityPopulationDto;
import telran.ashkelon2020.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
//	@Query(value = "select * from persons where name=?1", nativeQuery = true) нативный запрос
	@Query("select p from Person p where p.name=?1") // запрос на языке JPQL
	Stream<Person> findByName(String name);
	
//	Stream<Person> findByName(String name);
	
	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

//	Stream<Person> findByAddressCity(String city);

	@Query("select p from Person p where p.address.city=:city")
	Stream<Person> findByAddressCity(@Param("city") String city);

	@Query("select new telran.ashkelon2020.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCityPopulation();
	
	@Query("select e from Employee e where e.salary >= ?1 and e.salary < ?2")
	Stream<Person> findByEmployeeSalaryBetween(int min, int max);
	
	@Query("select c from Child c")
	Stream<Person> findAllChild();
}
