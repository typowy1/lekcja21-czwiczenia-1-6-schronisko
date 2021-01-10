package pl.javastart.lekcja21czwiczenia16schronisko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model, @RequestParam(required = false, name = "gatunek") AnimalSpecies species) { // dodajemy pętle która wywoła sie tyle razy ile jest zwierząt
        //@RequestParam(required = false) AnimalSpecies gatunek - parametr nie jst wymagany, i w parametrze sa enumy
        Set<Animal> animals;
        if (species != null) {
            animals = animalRepository.findBySpecies(species); //jak znajdzie w adrsie url gatunek z parametrem np cat to go wyswietli pod tym adresem

        } else {
            animals = animalRepository.findAll(); //gatunek w adresie url nie wystepuje i wtedy wyswietla wszystkie
        }
        model.addAttribute("animals", animals);
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
}
