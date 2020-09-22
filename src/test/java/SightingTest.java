import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        Sighting anotherSighting = new Sighting(testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sighting testSighting = new Sighting (testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        testSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sighting testSighting = new Sighting (testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        testSighting.save();
        Animal secondTestAnimal = new Animal("Panther");
        secondTestAnimal.save();
        Sighting secondTestSighting = new Sighting (testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        secondTestSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Animal testAnimal = new Animal("Leopard");
        testAnimal.save();
        Sighting testSighting = new Sighting (testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        testSighting.save();
        Animal secondTestAnimal = new Animal("Panther");
        secondTestAnimal.save();
        Sighting secondTestSighting = new Sighting (testAnimal.getId(), "zone A", "Wepukullo", "non-endangered");
        secondTestSighting.save();
        assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
    }

}
