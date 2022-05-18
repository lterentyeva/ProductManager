package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.manager.Book;
import ru.netology.manager.Product;
import ru.netology.manager.Smartphone;


import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    Product first = new Book(1, "Idiot", 560, "Dostoevsky");
    Product second = new Book(2, "War and peace", 1500, "Tolstoy");
    Product third = new Smartphone(3, "Samsung Note 9", 70000, "Samsung");

    @Test
    void shouldFindAllProduct() {
        Repository repository = new Repository();

        repository.save(first);
        repository.save(second);
        repository.save(third);

        Product[] expected = {first, second, third};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        Repository repository = new Repository();

        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.removeById(1);

        Product[] expected = {second, third};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByText() {
        Repository repository = new Repository();
        ProductManager manager = new ProductManager(repository);

        repository.save(first);
        repository.save(second);
        repository.save(third);

        Product[] expected = {first};
        Product[] actual = manager.searchBy("Idiot");

        assertArrayEquals(expected, actual);


    }

}