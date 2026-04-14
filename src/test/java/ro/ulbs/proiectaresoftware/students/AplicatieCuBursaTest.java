package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AplicatieCuBursaTest {
    private AplicatieCuBursa app;

    @BeforeEach
    void setUp() {
        app = new AplicatieCuBursa();
    }

    @Test
    void sortTest1() {
        List<StudentBursier> lista = app.genereaza();

        app.sorteaza(lista);

        assertTrue(lista.get(0).getFormatieDeStudiu().compareTo(lista.get(1).getFormatieDeStudiu()) <= 0);

        assertFalse(lista.isEmpty());
        assertEquals(4, lista.size());
    }
}