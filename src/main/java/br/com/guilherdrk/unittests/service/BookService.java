package br.com.guilherdrk.unittests.service;


import br.com.guilherdrk.controller.TestLogController;
import br.com.guilherdrk.controller.BookController;
import br.com.guilherdrk.data.dto.v1.BookDTO;
import br.com.guilherdrk.exception.RequiredObjectIsNullExecption;
import br.com.guilherdrk.exception.ResourceNotFoundExecption;
import br.com.guilherdrk.model.Book;
import br.com.guilherdrk.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static br.com.guilherdrk.mapper.ObjectMapper.parseListObjects;
import static br.com.guilherdrk.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());
    @Autowired
    BookRepository bookRepository;

    public List<BookDTO> findAll(){
        logger.info("Finding all Books!");
        var books = parseListObjects(bookRepository.findAll(), BookDTO.class);
        books.forEach(b -> addHateoasLinks(b));
        return books;

    }
    public BookDTO findById(Long id){
        logger.info("Finding one Book!");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExecption("No records found for this id"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    public BookDTO create(BookDTO book){

        if (book == null) throw new RequiredObjectIsNullExecption();

        logger.info("Creating one Book!");
        var entity = parseObject(book, Book.class);
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;

    }

    public BookDTO update(BookDTO book){

        if (book == null) throw new RequiredObjectIsNullExecption();

        logger.info("Updating one Book!");
        Book entity = bookRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundExecption("No records found for this id"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    public void delete(Long id){
        logger.info("Deleting one Book");
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExecption("No records found for this id"));
        bookRepository.delete(entity);

    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));

    }


}
