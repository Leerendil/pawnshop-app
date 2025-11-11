package by.vsu.service;

import by.vsu.repository.CategoriesRepository;
import by.vsu.tableClasses.Categories;

public class CategoryService extends Service<Categories> {

    public CategoryService(CategoriesRepository categoriesRepository) {
        super(categoriesRepository);
    }

}
