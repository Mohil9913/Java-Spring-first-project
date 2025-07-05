package com.example.spring_6_webapp.bootstrap;

import com.example.spring_6_webapp.domain.Author;
import com.example.spring_6_webapp.domain.Book;
import com.example.spring_6_webapp.repositories.AuthorRepository;
import com.example.spring_6_webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author mohil = new Author();
        mohil.setFirstName("Mohil");
        mohil.setLastName("Mokaria");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("654321");

        Author mohilSaved = authorRepository.save(mohil);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        mohilSaved.getBooks().add(noEJBSaved);

        System.out.println("In Bootstrap!");
        System.out.println("Author Count = " + authorRepository.count());
        System.out.println("Book Count = " + bookRepository.count());
        System.out.println(ericSaved.toString());
        System.out.println(mohil.toString());
    }
}
