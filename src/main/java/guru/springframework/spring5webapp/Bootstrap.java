package guru.springframework.spring5webapp;

import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Bootstrap implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        final Author author = new Author("Eric", "Evans");
        final Book ddd = new Book("DomainDriven Design", "124");

        author.addBook(ddd);
        ddd.addAuthor(author);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(author);
        bookRepository.save(ddd);

        final Author jon = new Author("jon", "Rod");
        final Book jeeWithoutEjb = new Book("J2EE without ejb", "973048");

        jon.addBook(jeeWithoutEjb);
        jeeWithoutEjb.addAuthor(jon);
        jeeWithoutEjb.setPublisher(publisher);
        publisher.getBooks().add(jeeWithoutEjb);


        authorRepository.save(jon);
        bookRepository.save(jeeWithoutEjb);
        publisherRepository.save(publisher);

        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books saved: " + bookRepository.count());


    }
}
