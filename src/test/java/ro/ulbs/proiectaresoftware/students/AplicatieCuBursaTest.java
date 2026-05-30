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

        assertFalse(lista.isEmpty());
        assertEquals(4, lista.size());

        // Verificam pentru TOATE elementele din lista (Lab 6.8.3)
        for (int i = 0; i < lista.size() - 1; i++) {
            StudentBursier curent = lista.get(i);
            StudentBursier urmator = lista.get(i + 1);

            // Verificam ca formatia de studiu curenta e <= formatia de studiu a urmatorului
            assertTrue(curent.getFormatieDeStudiu().compareTo(urmator.getFormatieDeStudiu()) <= 0,
                    "Lista nu este sortata corect la indexul " + i);
        }
    }
}