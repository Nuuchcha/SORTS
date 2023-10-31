package ru.netology.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("Moscow", "Novosibirsk", 27_000, 2, 7);
    Ticket ticket2 = new Ticket("Moscow", "Novosibirsk", 25_000, 2, 6);
    Ticket ticket3 = new Ticket("Moscow", "Chelyabinsk", 18_000, 4, 7);
    Ticket ticket4 = new Ticket("Moscow", "Omsk", 26_000, 8, 12);
    Ticket ticket5 = new Ticket("Moscow", "Novosibirsk", 30_000, 14, 19);
    Ticket ticket6 = new Ticket("Moscow", "Yakutsk", 30_000, 12, 18);
    Ticket ticket7 = new Ticket("Kazan", "Chelyabinsk", 20_000, 12, 14);

    AviaSouls manager = new AviaSouls();
    TicketTimeComparator timeComparator = new TicketTimeComparator();

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
    }

    @Test
    public void shouldFindAll() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7};
        Ticket[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareToMin() {
        int expected = -1;
        int actual = ticket2.compareTo(ticket1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToMax() {
        int expected = 1;
        int actual = ticket4.compareTo(ticket3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToIdentical() {
        int expected = 0;
        int actual = ticket5.compareTo(ticket6);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchAllTicketAlongRoute() {

        Ticket[] expected = {ticket2, ticket1, ticket5};
        Ticket[] actual = manager.search("Moscow", "Novosibirsk");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoTicketAlongRoute() {

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Moscow", "Neryungry");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchOneTicketAlongRoute() {

        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.search("Moscow", "Chelyabinsk");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldComparatorMin() {
        int expected = -1;
        int actual = timeComparator.compare(ticket4, ticket6);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldComparatorMax() {
        int expected = 1;
        int actual = timeComparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldComparatorIdentical() {
        int expected = 0;
        int actual = timeComparator.compare(ticket1, ticket5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchAllTicketAlongRouteComparator() {

        Ticket[] expected = {ticket2, ticket1, ticket5};
        Ticket[] actual = manager.searchAndSortBy("Moscow", "Novosibirsk", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoTicketAlongRouteComparator() {

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("Moscow", "Neryungri", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
