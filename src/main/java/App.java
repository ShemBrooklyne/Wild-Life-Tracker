import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        staticFileLocation("/public");


//        String layout = "public/templates/layout.hbs";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "index.hbs");
        }, new HandlebarsTemplateEngine()

        );

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "category-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/categories", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(new HashMap(), "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/category/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            Category category = Category.find(Integer.parseInt(request.params(":id")));
//            model.put("category", category);
            return new ModelAndView(new HashMap(), "category.hbs");
        }, new HandlebarsTemplateEngine());

        get("/report/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            Report report = Report.find(Integer.parseInt(request.params(":id")));
//            model.put("report", report);
            return new ModelAndView(new HashMap(), "report.hbs");
        }, new HandlebarsTemplateEngine());


        post("/categories",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String category = request.queryParams("category");
            Category newCategory = new Category(category);
            newCategory.save();
            model.put("template", "public/templates/proceed.hbs");
            return new ModelAndView(model, "proceed.hbs");
        }, new HandlebarsTemplateEngine());

        post("/reports", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangername = request.queryParams("rangername");
            int category = Integer.parseInt(request.queryParams("categoryId"));
            String type = request.queryParams("category");
            String zone = request.queryParams("zone");
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            int count = Integer.parseInt(request.queryParams("count"));
            Report newReport = new Report(rangername,type,zone,name,health,age,category,count);
            newReport.save();
            model.put("template", "public/templates/proceed.hbs");
            return new ModelAndView(model, "proceed.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/check", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Report animal = Report.find(Integer.parseInt(request.queryParams("id")));
            animal.delete();
            animal.sort();
            model.put("reports", animal);
            model.put("template", "public/templates/proceed.vtl");
            return new ModelAndView(model, "proceed.hbs");
        }, new HandlebarsTemplateEngine());




    }
}
