package telran.ashkelon2020.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.person.dto.NameDto;
import telran.ashkelon2020.person.dto.PeriodDto;
import telran.ashkelon2020.person.dto.PersonDto;
import telran.ashkelon2020.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findById(id);
	}
	
	@PutMapping("/{id}")
	public PersonDto updatePerson(@PathVariable Integer id, @RequestBody NameDto nameDto) {
		return personService.updatePerson(id, nameDto);
	}
	
	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
	@GetMapping("/persons/name/{name}")
	public Iterable<PersonDto> findAllByName(@PathVariable String name) {
		return personService.findAllByName(name);
	}
	
	@GetMapping("/persons/date/period")
	public Iterable<PersonDto> findAllByAge(@RequestBody PeriodDto periodDto){
		return personService.findAllByAge(periodDto.getFrom(), periodDto.getTo());
	}
}
