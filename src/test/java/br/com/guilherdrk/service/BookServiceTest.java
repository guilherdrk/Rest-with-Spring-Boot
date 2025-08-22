    package br.com.guilherdrk.service;

    import br.com.guilherdrk.data.dto.v1.BookDTO;
    import br.com.guilherdrk.data.dto.v1.PersonDTO;
    import br.com.guilherdrk.exception.RequiredObjectIsNullExecption;
    import br.com.guilherdrk.model.Book;
    import br.com.guilherdrk.model.Person;
    import br.com.guilherdrk.repositories.BookRepository;
    import br.com.guilherdrk.repositories.PersonRepository;
    import br.com.guilherdrk.unitetests.mapper.mocks.MockBook;
    import br.com.guilherdrk.unitetests.mapper.mocks.MockPerson;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.TestInstance;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import org.mockito.junit.jupiter.MockitoExtension;

    import java.math.BigDecimal;
    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @ExtendWith(MockitoExtension.class)
    class BookServiceTest {

        MockBook input;

        @InjectMocks
        private BookService service;

        @Mock
        BookRepository bookRepository;

        @BeforeEach
        void setUp() {
            input = new MockBook();

            MockitoAnnotations.openMocks(this);
        }

        @Test
        void findById() {

            Book book = input.mockEntity(1);
            book.setId(1L);
            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

            var result = service.findById(1L);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getLinks());

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("GET")
                    ));

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("GET")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("POST")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("PUT")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("DELETE")
                    )

            );

            assertEquals("Some Author1", result.getAuthor());
            assertNotNull(result.getLaunchDate());
            assertEquals( BigDecimal.valueOf(25D), result.getPrice());
            assertEquals("Some title1", result.getTitle());
        }


        @Test
        void create() {
            Book persistedEntity = input.mockEntity(1);
            persistedEntity.setId(1L);
            BookDTO dto = input.mockDTO(1);
            when(bookRepository.save(any(Book.class))).thenReturn(persistedEntity);


            var result = service.create(dto);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getLinks());

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("GET")
                    ));

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("GET")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("POST")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("PUT")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("DELETE")
                    )

            );

            assertEquals("Some Author1", result.getAuthor());
            assertNotNull(result.getLaunchDate());
            assertEquals( BigDecimal.valueOf(25D), result.getPrice());
            assertEquals("Some title1", result.getTitle());
        }
        @Test
        void testCreateWithNullPerson() {
            Exception exception = assertThrows(RequiredObjectIsNullExecption.class, () -> {
                service.create(null);
            });

            String expectedMessage = "It is not allowed to persist a null object";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        void update() {
            Book book = input.mockEntity(1);
            Book persisted = book;
            persisted.setId(1L);
            BookDTO dto = input.mockDTO(1);

            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
            when(bookRepository.save(book)).thenReturn(persisted);

            var result = service.update(dto);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getLinks());

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("GET")
                    ));

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("GET")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("POST")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("PUT")
                    )
            );

            assertNotNull(result.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("DELETE")
                    )

            );

            assertEquals("Some Author1", result.getAuthor());
            assertNotNull(result.getLaunchDate());
            assertEquals( BigDecimal.valueOf(25D), result.getPrice());
            assertEquals("Some title1", result.getTitle());
        }
        @Test
        void testUpdateWithNullPerson() {
            Exception exception = assertThrows(RequiredObjectIsNullExecption.class, () -> {
                service.update(null);
            });

            String expectedMessage = "It is not allowed to persist a null object";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        void delete() {
            Book book = input.mockEntity(1);
            book.setId(1L);
            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
            service.delete(1L);

            verify(bookRepository, times(1)).findById(anyLong());
            verify(bookRepository, times(1)).delete(any(Book.class));
            verifyNoMoreInteractions(bookRepository);

        }
        @Test
        void findAll() {
            List<Book> list = input.mockEntityList();
            when(bookRepository.findAll()).thenReturn(list);

            List<BookDTO> book = service.findAll();

            assertNotNull(book);
            assertEquals(14, book.size());

            var bookOne = book.get(1);


            assertNotNull(bookOne);
            assertNotNull(bookOne.getId());
            assertNotNull(bookOne.getLinks());

            assertNotNull(bookOne.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("GET")
                    ));

            assertNotNull(bookOne.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("GET")
                    )
            );

            assertNotNull(bookOne.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("POST")
                    )
            );

            assertNotNull(bookOne.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("PUT")
                    )
            );

            assertNotNull(bookOne.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("DELETE")
                    )

            );
            assertEquals("Some Author1", bookOne.getAuthor());
            assertNotNull(bookOne.getLaunchDate());
            assertEquals(  BigDecimal.valueOf(25D), bookOne.getPrice());
            assertEquals("Some title1", bookOne.getTitle());


            var bookFour = book.get(4);

            assertNotNull(bookFour);
            assertNotNull(bookFour.getId());
            assertNotNull(bookFour.getLinks());

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("GET")
                    ));

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("GET")
                    )
            );

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("POST")
                    )
            );

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("PUT")
                    )
            );

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("DELETE")
                    )
            );
            assertEquals("Some Author4", bookFour.getAuthor());
            assertNotNull(bookFour.getLaunchDate());
            assertEquals( BigDecimal.valueOf(25D), bookFour.getPrice());
            assertEquals("Some title4", bookFour.getTitle());


            var bookSeven = book.get(7);


            assertNotNull(bookSeven);
            assertNotNull(bookSeven.getId());
            assertNotNull(bookSeven.getLinks());

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("self")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("GET")
                    ));

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("findAll")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("GET")
                    )
            );

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("create")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("POST")
                    )
            );

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("update")
                            && link.getHref().endsWith("/api/book/v1")
                            && link.getType().equals("PUT")
                    )
            );

            assertNotNull(bookFour.getLinks().stream()
                    .anyMatch(link -> link.getRel().value().equals("delete")
                            && link.getHref().endsWith("/api/book/v1/1")
                            && link.getType().equals("DELETE")
                    )
            );
            assertEquals("Some Author7", bookSeven.getAuthor());
            assertNotNull(bookSeven.getLaunchDate());
            assertEquals( BigDecimal.valueOf(25D), bookSeven.getPrice());
            assertEquals("Some title7", bookSeven.getTitle());
        }


    }