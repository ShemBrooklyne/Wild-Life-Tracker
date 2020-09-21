import org.sql2o.Connection;

import java.util.List;

public class Category {

    private String category;
    private int id;
    public Category(String type) {
        this.category = type;
    }

    public String getType() {
        String category = this.category;
        return category;
    }



    @Override
    public boolean equals(Object othercategory) {
        if (!(othercategory instanceof Category)) {
            return false;
        } else {
            Category newCategory = (Category) othercategory;
            return this.getType().equals(newCategory.getType()) &&
                    this.getId() == newCategory.getId();
        }
    }

    public static  List<Category> all() {
        String sql = "SELECT id, category FROM category;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Category.class);
        }
    }

    public static Category find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM category where id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Category.class);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO category (category) VALUES (:category)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("category", this.category)
                    .executeUpdate()
                    .getKey();
        }

    }

    public int getId() {
        return id;
    }
}
