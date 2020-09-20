import static spark.Spark.*;
import static spark.route.HttpMethod.post;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {


    public static void main(String[] args) {


        staticFileLocation("/public");

//        main page
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "index.hbs");
        }, new HandlebarsTemplateEngine()
        );

        //        Keying Info page
        get("/Endangered", (request, response) -> {
                    Map<String, Object> model = new HashMap<String, Object>();
                    return new ModelAndView(new HashMap(), "Endangered.hbs");
                }, new HandlebarsTemplateEngine()
        );

    }
}
