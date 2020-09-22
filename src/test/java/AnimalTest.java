import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_false() {
        Animal testAnimal = new Animal("Zebra");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Zebra() {
        Animal testAnimal = new Animal("Zebra");
        assertEquals("Zebra", testAnimal.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        Animal firstAnimal = new Animal("Zebra");
        Animal anotherAnimal = new Animal("Zebra");
        assertTrue(firstAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_assignsIdToObjectAndSavesObjectToDatabase() {
        Animal testAnimal = new Animal("Zebra");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_false() {
        Animal firstAnimal = new Animal("Zebra");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Lion");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal("Zebra");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Lion");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void delete_deletesAnimalFromDatabase_0() {
        Animal testAnimal = new Animal("Zebra");
        testAnimal.save();
        testAnimal.delete();
        assertEquals(0, Animal.all().size());
    }

}
