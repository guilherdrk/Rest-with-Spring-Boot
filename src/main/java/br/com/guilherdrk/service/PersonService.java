package br.com.guilherdrk.service;


import br.com.guilherdrk.controller.PersonController;
import br.com.guilherdrk.controller.TestLogController;
import br.com.guilherdrk.data.dto.v1.PersonDTO;

import br.com.guilherdrk.exception.RequiredObjectIsNullExecption;
import br.com.guilherdrk.exception.ResourceNotFoundExecption;
import static br.com.guilherdrk.mapper.ObjectMapper.parseListObjects;
import static br.com.guilherdrk.mapper.ObjectMapper.parseObject;

import br.com.guilherdrk.model.Person;
import br.com.guilherdrk.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;


@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());
    @Autowired
    PersonRepository personRepository;



    public List<PersonDTO> findAll(){
        logger.info("Finding all People!");
        var persons = parseListObjects(personRepository.findAll(), PersonDTO.class);
        persons.forEach(p -> addHateoasLinks(p));
        return persons;

    }

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExecption("No records found for this id"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    public PersonDTO create(PersonDTO person){

        if (person == null) throw new RequiredObjectIsNullExecption();


        logger.info("Creating one Person!");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;

    }

    public PersonDTO update(PersonDTO person){

        if (person == null) throw new RequiredObjectIsNullExecption();

        logger.info("Updating one Person!");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundExecption("No records found for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    public void delete(Long id){
        logger.info("Deleting one Person");
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExecption("No records found for this id"));
        personRepository.delete(entity);

    }
    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));

    }


}
