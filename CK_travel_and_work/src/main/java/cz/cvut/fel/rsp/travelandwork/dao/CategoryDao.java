package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Category;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends BaseDao<Category>{
    protected CategoryDao() {
        super(Category.class);
    }

    public boolean add(Category category, Trip trip){
        return category.add(trip);
    }
}