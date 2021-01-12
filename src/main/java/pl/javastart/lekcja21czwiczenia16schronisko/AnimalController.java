package pl.javastart.lekcja21czwiczenia16schronisko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
public class AnimalController {

    //zeby wstrzyknąć repozytorium, deklarujemy pole w ramach kontrolera i stworzyć cosnstruktor przyjmujący to pole
    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    } //podczas tworzenia controlera spring poszuka repozytorium, uruchomi konstruktor tworzac repozytorium i poda do kontrolera

    //getmaping obsługujący strone główną
    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false, name = "gatunek") AnimalSpecies species,
                       @RequestParam(required = false) String order, @RequestParam(required = false) String searchText) { // dodajemy pętle która wywoła sie tyle razy ile jest zwierząt
        //@RequestParam(required = false) AnimalSpecies gatunek - parametr nie jst wymagany, i w parametrze sa enumy
        // @RequestParam String order parametr do sortowania, sortowanie nie jest wymagane
        Set<Animal> animals;

        if (searchText != null) {
            animals = animalRepository.findByNameContains(searchText);
        } else {
            if (species != null) {
                animals = animalRepository.findBySpecies(species); //jak znajdzie w adrsie url gatunek z parametrem np cat to go wyswietli pod tym adresem

            } else {
                animals = animalRepository.findAll(); //gatunek w adresie url nie wystepuje i wtedy wyswietla wszystkie
            }
        }

        List<Animal> animalList = new ArrayList<>(animals); // ze zbioru tworzymy liste którą możemy posortować

        if (order != null) { // jeśli sortowanie jest różne od null to wtedy będziemy sortować

            int asc = (order.equals("ASC")) ? 1 : -1; // jeżeli moja wartość będzie ASC(rosnąco) to będzie 1 to
            // sortuje rosnąco jeśli wartość nie będzie ASC to będzie -1 i to będzie malejąco
            animalList.sort(new Comparator<Animal>() { //sortujemy jako klase anonimową zapomoca imienia
                @Override
                public int compare(Animal o1, Animal o2) {
                    return o1.getName().compareTo(o2.getName()) * asc;
                }
            });

        }

        model.addAttribute("animals", animalList); //sortujemy wtedy kiedy jest potrzeba
        model.addAttribute("species", species); //dodanie do modelu gatunku do sortowania
        return "home"; // -> resources/templates/home.html
    }


    @GetMapping("/zwierzak")//mapuje stronę zwiarzaka
    public String details(@RequestParam String imie, Model model) {  // przewchwytuje parametr imie, i przeszukamy repozytorium szukając zwierzaka o danym imieniu
        //szukamy w repo zwierzaka o wybranym imieniu
        Animal animal = animalRepository.findByName(imie);
        //jeśli zwierzak został znaleziony to użyjemy szablonu animal z informacjami o zwierzaku który znajduje sie w templates
        if (animal != null) {
            //w przypadku znalezienia animal do modelu dodamy atrybut name o wrtoscie getname i dynamicznie wyświetlimy w h2 w pliku animal.html
//            model.addAttribute("name", animal.getName());
//            model.addAttribute("description", animal.getDescription());

            //lepiej jest dac w modelu obiekt i w pliku odniesc sie do obiektu i pobrac potrzebna informacje np animal.name
            model.addAttribute("animal", animal);
            return "animal"; // -> resources/templates/animal.html
        } else {  // jeśli nie znajdziemy zwierzaka to zwróci przekierowanie na strone główną
            return "redirect:/";
        }
    }

    //dodajemy kolejną strone dodawania /dodaj, model obiektu animal
    @GetMapping("/dodaj")
    public String addForm(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("mode", "add"); // dodajemy informacje o tym ze jestesmy w trybie dodawania
        return "addOrEdit"; //pod adresem /dodaj zostanie zwrócony szablon add
    }

    //dodanie użytkownika do repozytorium
    @PostMapping("/dodaj")
    public String addAnimal(Animal animal) {
        animalRepository.add(animal);
        return "redirect:/zwierzak?imie=" + animal.getName(); //zwraca przekierowanie na stronę zwierzaka utworzonego
    }

    //dodajemy stronę edycji z parametrami, tu po przejsciu na strone automatycznie będą wpisane w pola informacje o zwierzaku
    @GetMapping("/edytuj")
    public String editForm(Model model, @RequestParam String imie) {
        Animal animal = animalRepository.findByName(imie);
        model.addAttribute("animal", animal); //do modelu dodałem obbiekt
        model.addAttribute("mode", "edit"); // dodajemy informacje o tym ze jestesmy w trybie edycji
        return "addOrEdit";
    }

    //dodajemy możliwosć edycji za pomocą metody post
    @PostMapping("/edytuj")
    public String editAnimal(Animal animal) {
        Animal animalInDB = animalRepository.findById(animal.getId()); //szukamy wpisanego zwierzaka w bazie danych po id
        animal.setName(animal.getName());
        animal.setDescription(animal.getDescription());
        animal.setImg(animal.getImg());
        animal.setSpecies(animal.getSpecies());
        animalRepository.update(animalInDB);
        return "redirect:/zwierzak?imie=" + animal.getName(); //zwraca przekierowanie na stronę zwierzaka utworzonego
    }
}
