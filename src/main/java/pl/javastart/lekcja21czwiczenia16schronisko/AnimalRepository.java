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
        animals.add(new Animal(1L, "Azor", "Azor to super piesek", "/azor.jpg", AnimalSpecies.DOG)); //plik dostepny w folderze static, a jego adres to /azor.jpg, czyli po głównej stronie/azor.jpg
        animals.add(new Animal(2L, "Rudy", "Rudy to kot",
                "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg", AnimalSpecies.CAT));
        animals.add(new Animal(3L, "Urek", "Urek to Ryś",
                "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg", AnimalSpecies.OTHER));
        animals.add(new Animal(4L, "Nyga", "Nyga to Dzik",
                "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg", AnimalSpecies.OTHER));
        animals.add(new Animal(5L, "Ziom", "Zioom to mały pies",
                "https://www.koty.pl/wp-content/uploads/2019/02/rudy-kotek-3-e1550138386733.jpg", AnimalSpecies.DOG));
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

    public Animal findById(Long id) {
        //przechodzimy po wszystkich elementach zbioru
        for (Animal animal : animals) {
            if (animal.getId().equals(id)) {
                return animal; //zwracamy znalziony obiekt
            }
        }
        return null; //jeśli nie znajdziemy nic to zwracamy nulla
    }

    public Set<Animal> findAll() {
        return animals;
    }

    public Set<Animal> findBySpecies(AnimalSpecies species) {
        Set<Animal> filtered = new HashSet<>();
        for (Animal animal : animals) {
            if (animal.getSpecies() == species) {
                filtered.add(animal);
            }
        }
        return filtered;
        //stowrzyłem set przefiltrowanych zwierzaków, jak któryś będzie się zgadzał z wybranym to dodajemt go do pofiltrowanego setu
    }

    public void add(Animal animal) {
        animals.add(animal);
    }

    public void update(Animal animalInDB) {
        animals.add(animalInDB);
    }

    public Set<Animal> findByNameContains(String searchText) { // szukanie po wpisanej nazwie, częsci nazwy np jeśli nazwa zawiera rud wyświetl je
        Set<Animal> filtered = new HashSet<>();



        for (Animal animal : animals) {
            if (animal.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filtered.add(animal);
            }
        }
        return filtered;
    }
}
