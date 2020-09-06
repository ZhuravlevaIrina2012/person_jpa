package telran.ashkelon2020.person.service;

import java.time.LocalDate;

import telran.ashkelon2020.person.dto.NameDto;
import telran.ashkelon2020.person.dto.PersonDto;

public interface PersonService {

	boolean addPerson(PersonDto personDto);
	
	PersonDto findById(Integer id);
	
	PersonDto updatePerson(Integer id, NameDto nameDto);
	
	PersonDto deletePerson(Integer id);
	
	Iterable<PersonDto> findAllByName(String name);
	
	Iterable<PersonDto> findAllByAge(LocalDate dateFrom, LocalDate dateTo);
}
