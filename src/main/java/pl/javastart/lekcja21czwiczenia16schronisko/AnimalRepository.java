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
        animals.add(new Animal("Azor", "Azor to super piesek"));
        animals.add(new Animal("Rudy", "Rudy to kot"));
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
}
