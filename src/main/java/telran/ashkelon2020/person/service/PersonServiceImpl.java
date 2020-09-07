package telran.ashkelon2020.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.person.dto.CityPopulationDto;
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
	@Transactional
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = modelMapper.map(personDto, Person.class);
		personRepository.save(person);
		return true;
	}

	@Override
	public PersonDto findById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public PersonDto updatePerson(Integer id, NameDto nameDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		person.setName(nameDto.getName());
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findAllByName(String name) {
		return personRepository.findByName(name)
					.map(p -> modelMapper.map(p, PersonDto.class))
					.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findAllByAge(int from, int to) {
		LocalDate dateTo = LocalDate.now().minusYears(from);
		LocalDate dateFrom = LocalDate.now().minusYears(to);
		return personRepository.findByBirthDateBetween(dateFrom, dateTo)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findAllByCity(String city) {
		return personRepository.findByAddressCity(city)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personRepository.getCityPopulation();
	}

}
