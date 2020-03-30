package cz.cvut.fel.rsp.travelandwork.service;

import cz.cvut.fel.rsp.travelandwork.dao.CategoryDao;
import cz.cvut.fel.rsp.travelandwork.model.Category;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
    public boolean add(Category category, Trip trip){
        return categoryDao.add(category, trip);
    }
}
