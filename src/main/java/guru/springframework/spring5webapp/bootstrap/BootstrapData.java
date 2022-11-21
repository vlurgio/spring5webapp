package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = Author.builder()
                .firstName("Eric")
                .lastName("Evans")
                .build();

        Book ddd = Book.builder()
                .title("Domain Driven Design")
                .isbn("123123")
                .build();

        Publisher pub = Publisher.builder()
                        .name("Publisher 1")
                        .address("This is an address")
                        .build();

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(pub);

        publisherRepository.save(pub);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        pub.getBooks().add(ddd);
        publisherRepository.save(pub);

        Author rod = Author.builder()
                .firstName("Rod")
                .lastName("Johnson")
                .build();


        Book noEJB = Book.builder()
                .title("J2EE Development without EJB")
                .isbn("129083120983")
                .build();

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pub);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
//        pub.getBooks().add(noEJB);
//        publisherRepository.save(pub);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

        System.out.println("Publisher Nomber of Books: " + publisherRepository.count());


    }
}
