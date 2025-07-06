package com.example.spring_6_webapp.bootstrap;

import com.example.spring_6_webapp.domain.Author;
import com.example.spring_6_webapp.domain.Book;
import com.example.spring_6_webapp.domain.Publisher;
import com.example.spring_6_webapp.repositories.AuthorRepository;
import com.example.spring_6_webapp.repositories.BookRepository;
import com.example.spring_6_webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
            ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Created Author1
        Author a1 = new Author();
        a1.setFirstName("Mohil");
        a1.setLastName("Mokaria");
        
        //Created Author2
        Author a2 = new Author();
        a2.setFirstName("Alex");
        a2.setLastName("Monalisa");

        //Created Book1
        Book b1 = new Book();
        b1.setTitle("Book 1");
        b1.setIsbn("123");
        
        //Created Book2
        Book b2 = new Book();
        b2.setTitle("Book 2");
        b2.setIsbn("456");

        //saving Authors & Books to DB
        Author a1Saved = authorRepository.save(a1);
        Author a2Saved = authorRepository.save(a2);
        Book b1Saved = bookRepository.save(b1);
        Book b2Saved = bookRepository.save(b2);

        //Adding saved books to Author's BOOKS set
        a1Saved.getBooks().add(b1Saved);
        a2Saved.getBooks().add(b2Saved);

        //Created a Publisher
        Publisher royal = new Publisher();
        royal.setPublisherName("Royal Publications");
        royal.setAddress("Kalavad Road");
        royal.setCity("Rajkot");
        royal.setState("Gujarat");
        royal.setZip(360005);

        //Saved publisher to DB
        Publisher royalSaved = publisherRepository.save(royal);

        //Adding publisher to book's PUBLISHER
        b1Saved.setPublisher(royalSaved);
        b2Saved.setPublisher(royalSaved);

        //Updating saved records to Author & Book
        authorRepository.save(a1Saved);
        authorRepository.save(a2Saved);
        bookRepository.save(b1Saved);
        bookRepository.save(b2Saved);

        System.out.println("In Bootstrap!");
        System.out.println("Author Count = " + authorRepository.count());
        System.out.println("Book Count = " + bookRepository.count());
        System.out.println("Publisher Count = " + publisherRepository.count());
        System.out.println(a1Saved.toString());
        System.out.println(a2Saved.toString());
        System.out.println(b1Saved.toString());
        System.out.println(b2Saved.toString());
        System.out.println(royalSaved.toString());
    }
}
