package ro.ulbs.proiectaresoftware.students;

import java.util.*;

public class AplicatieCuBursa {

    public List<StudentBursier> genereaza() {
        List<StudentBursier> lista = new ArrayList<>();
        lista.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        lista.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        lista.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));
        lista.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        return lista;
    }

    public List<StudentBursier> sorteaza(List<StudentBursier> lst) {
        lst.sort((s1, s2) -> {
            // 1. Formatie de studiu
            int res = s1.getFormatieDeStudiu().compareTo(s2.getFormatieDeStudiu());
            if (res != 0) return res;

            // 2. Nume
            res = s1.getNume().compareTo(s2.getNume());
            if (res != 0) return res;

            // 3. Prenume
            res = s1.getPrenume().compareTo(s2.getPrenume());
            if (res != 0) return res;

            // 4. Nota (descrescator)
            res = Float.compare(s2.getNota(), s1.getNota());
            if (res != 0) return res;

            // 5. Cuantum bursa
            return Double.compare(s2.getCuantumBursa(), s1.getCuantumBursa());
        });
        return lst;
    }

    public static void main(String[] args) {
        AplicatieCuBursa instanta = new AplicatieCuBursa();
        List<StudentBursier> lista = instanta.genereaza();

        System.out.println("Lista initiala:");
        lista.forEach(System.out::println);

        instanta.sorteaza(lista);

        System.out.println("\nLista sortata:");
        lista.forEach(System.out::println);
    }
}