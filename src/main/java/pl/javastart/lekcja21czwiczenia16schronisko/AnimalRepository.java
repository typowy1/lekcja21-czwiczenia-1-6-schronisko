package pl.javastart.lekcja21czwiczenia16schronisko;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AnimalRepository {
    //repozytorium dla animal

    //deklarujemy zbiór zwierząt
    private Set<Animal> animals;

    // w konstruktorze inicjalizujemy hasset

    public AnimalRepository() {
        animals = new HashSet<>();
        animals.add(new Animal("Azor", "Azor to super piesek", "/azor.jpg")); //plik dostepny w folderze static, a jego adres to /azor.jpg, czyli po głównej stronie/azor.jpg
        animals.add(new Animal("Rudy", "Rudy to kot", "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg"));
        animals.add(new Animal("Urek", "Urek to Ryś", "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg"));
        animals.add(new Animal("Nyga", "Nyga to Dzik", "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg"));
        animals.add(new Animal("Ziom", "Zioom to mały pies", "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg"));
    }

    public Animal findByName(String name) {
        //przechodzimy po wszystkich elementach zbioru
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal; //zwracamy znalziony obiekt
            }
        }
        return null; //jeśli nie znajdziemy nic to zwracamy nulla
    }

    public Set<Animal> findAll() {
        return animals;
    }
}
