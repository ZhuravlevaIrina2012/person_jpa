package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.person.dto.NameDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.exceptions.UserNotFoundException;
import telran.ashkelon2020.person.model.Person;
import telran.ashkelon2020.person.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean addPerson(PersonDto personDto) {
		Person person = modelMapper.map(personDto, Person.class);
		Person exist = personRepository.findById(person.getId()).orElse(null);
		if (exist != null) {
			return false;
		}else {
			personRepository.save(person);
			return true;
		}
	}

	@Override
	public PersonDto findById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePerson(Integer id, NameDto nameDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		person.setName(nameDto.getName());
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public Iterable<PersonDto> findAllByName(String name) {
		return personRepository.findByName(name)
					.map(p -> modelMapper.map(p, PersonDto.class))
					.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Iterable<PersonDto> findAllByAge(LocalDate dateFrom, LocalDate dateTo) {
		return personRepository.findByBirthDateBetween(dateFrom, dateTo)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

}
