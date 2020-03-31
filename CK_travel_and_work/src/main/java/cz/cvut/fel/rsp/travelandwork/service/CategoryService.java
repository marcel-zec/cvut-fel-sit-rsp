package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.CategoryDao;
import cz.cvut.fel.rsp.travelandwork.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * adds trip to category
     * @return true if successed
     */
    @Transactional
    public void add(Category category, Trip trip){
        category.add(trip);
        categoryDao.update(category);
       // return categoryDao.add(category, trip);
    }
}
