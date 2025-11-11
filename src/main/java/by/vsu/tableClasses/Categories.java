package by.vsu.tableClasses;

public class Categories {
    private int category_id;
    private String categoriy_name;

    public Categories() {}

    public Categories(int category_id,String name) {
        setCategory_id(category_id);
        setName(name);
    }

    public Categories(String name) {
        setName(name);
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return categoriy_name;
    }

    public void setName(String name) {
        this.categoriy_name = name;
    }

    @Override
    public String toString() {
        return "Category " + category_id +
               ", " + categoriy_name + ";";
    }
}
