package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {


    Product first = new Book(1, "Idiot", 560, "Dostoevsky");
    Product second = new Book(2, "Demons", 650, "Dostoevsky");
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
    void shouldAddProduct() {
        Repository repository = new Repository();
        ProductManager manager = new ProductManager(repository);

        manager.add(first);
        manager.add(second);


        Product[] expected = {first, second};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);


    }

    @Test
    void shouldSearchByText() {
        ProductManager manager = new ProductManager();

        manager.add(first);
        manager.add(second);
        manager.add(third);


        Product[] expected = {third};
        Product[] actual = manager.searchBy("Samsung");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchByTextDifferentProducts() {

        ProductManager manager = new ProductManager();

        manager.add(first);
        manager.add(second);
        manager.add(third);


        Product[] expected = {first, second};
        Product[] actual = manager.searchBy("Dostoevsky");

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldFindMatches() {
        ProductManager manager = new ProductManager();

        String text = "Samsung";

        Boolean expected = true;
        Boolean actual = manager.matches(third, text);

        assertEquals(expected, actual);

    }

}