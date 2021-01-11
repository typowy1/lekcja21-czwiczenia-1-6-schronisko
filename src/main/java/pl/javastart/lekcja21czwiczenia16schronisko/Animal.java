package pl.javastart.lekcja21czwiczenia16schronisko;

public class Animal {
    //obiekt Animal

    private Long id;
    private String name;
    private String description;
    private String img; //link do obrazka
    private AnimalSpecies species;

    public Animal() {
    }

    public Animal(Long id, String name, String description, String img, AnimalSpecies species) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.species = species;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public void setSpecies(AnimalSpecies species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
