import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());


        port(4567);

        staticFileLocation("/public");
//        String layout = "templates/layout.hbs";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", Endangered.all());
            model.put("sightings", Sighting.all());
            model.put("template", "index.hbs");
            return new ModelAndView(new HashMap(), "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", Endangered.all());
            model.put("sightings", Sighting.all());
            model.put("template", "sightings.hbs");
            return new ModelAndView(new HashMap(), "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/non-endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("sightings", Sighting.all());
            model.put("template", "animals.hbs");
            return new ModelAndView(new HashMap(), "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("endangeredAnimals", Endangered.all());
            model.put("sightings", Sighting.all());
            model.put("template", "endangered-animals.hbs");
            return new ModelAndView(new HashMap(), "endangered-animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", Endangered.all());
            model.put("sightings", Sighting.all());
            model.put("template", "sightings-form.hbs");
            return new ModelAndView(new HashMap(), "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered_sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
            String location = request.queryParams("location");
            String animal_type = request.queryParams("endangered");
            Sighting sighting = new Sighting(animalIdSelected, location, rangerName, animal_type);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", Endangered.all());
            String animal = Endangered.find(animalIdSelected).getName();
            model.put("animal", animal);
            response.redirect("/sightings");
            return new ModelAndView(new HashMap(), "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
            String location = request.queryParams("location");
            String animal_type = request.queryParams("non-endangered");
            Sighting sighting = new Sighting(animalIdSelected, location, rangerName, animal_type);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", Animal.all());
            String animal = Animal.find(animalIdSelected).getName();
            model.put("animal", animal);
            response.redirect("/sightings");
            return new ModelAndView(new HashMap(), "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            boolean endangered = request.queryParamsValues("endangered")!=null;
            if (endangered) {
                String name = request.queryParams("name");
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                Endangered endangeredAnimal = new Endangered(name, health, age);
                endangeredAnimal.save();
                model.put("animals", Animal.all());
                model.put("endangeredAnimals", Endangered.all());
            } else {
                String name = request.queryParams("name");
                Animal animal = new Animal(name);
                animal.save();
                model.put("animals", Animal.all());
                model.put("endangeredAnimals", Endangered.all());
            }
            response.redirect("/");
            return null;
        });

        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal animal = Animal.find(Integer.parseInt(request.params("id")));
            model.put("animal", animal);
            model.put("template", "animal.hbs");
            return new ModelAndView(new HashMap(), "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered_animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Endangered endangeredAnimal = Endangered.find(Integer.parseInt(request.params("id")));
            model.put("endangeredAnimal", endangeredAnimal);
            model.put("template", "endangered-animal.hbs");
            return new ModelAndView(new HashMap(), "endangered-animal.hbs");
        }, new HandlebarsTemplateEngine());
    }
}