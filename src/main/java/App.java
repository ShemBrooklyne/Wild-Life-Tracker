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
        staticFileLocation("/public");

        port(getHerokuAssignedPort());


//        String layout = "templates/layout.hbs";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "index.hbs");
        }, new HandlebarsTemplateEngine());



        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "sightings.hbs");
        }, new HandlebarsTemplateEngine());



        get("/animals/non-endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "animals.hbs");
        }, new HandlebarsTemplateEngine());


        get("/animals/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "endangered-animals.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/endangered_sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            Endangered endangeredAnimal = new Endangered(rangerName, health, age);
            endangeredAnimal.save();
            System.out.println("Please enter all input fields.");
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/wildlife", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Animal animal = new Animal(name);
            animal.save();
            return new ModelAndView(model,"ess.hbs");
        }, new HandlebarsTemplateEngine());


        post("/animal/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            Endangered endangeredAnimal = new Endangered(name, health, age);
            endangeredAnimal.save();
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine());



        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "animal.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered_animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "endangered-animal.hbs");
        }, new HandlebarsTemplateEngine());
    }
}