package pl.javastart.lekcja21czwiczenia16schronisko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimalController {

    //zeby wstrzyknąć repozytorium, deklarujemy pole w ramach kontrolera i stworzyć cosnstruktor przyjmujący to pole
    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    } //podczas tworzenia controlera spring poszuka repozytorium, uruchomi konstruktor tworzac repozytorium i poda do kontrolera

    @GetMapping("/zwierzak")//mapuje stronę zwiarzaka
    public String details(@RequestParam String imie, Model model) {  // przewchwytuje parametr imie, i przeszukamy repozytorium szukając zwierzaka o danym imieniu
        //szukamy w repo zwierzaka o wybranym imieniu
        Animal animal = animalRepository.findByName(imie);
        //jeśli zwierzak został znaleziony to użyjemy szablonu animal z informacjami o zwierzaku który znajduje sie w templates
        if (animal != null) {
            //w przypadku znalezienia animal do modelu dodamy atrybut name o wrtoscie getname i dynamicznie wyświetlimy w h2 w pliku animal.html
//            model.addAttribute("name", animal.getName());
//            model.addAttribute("description", animal.getDescription());

            //lepiej jest dac w modelu obiekt i w pliku odniesc sie do obiektu i pobrac potrzebna informacje np animal.getName
            model.addAttribute("animal", animal);
            return "animal"; // -> resources/templates/animal.html
        } else {  // jeśli nie znajdziemy zwierzaka to zwróci przekierowanie na strone główną
            return "redirect:/";
        }
    }
}
