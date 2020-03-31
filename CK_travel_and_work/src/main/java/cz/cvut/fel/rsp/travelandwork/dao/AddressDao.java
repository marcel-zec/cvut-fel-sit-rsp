package cz.cvut.fel.rsp.travelandwork.dao;

import cz.cvut.fel.rsp.travelandwork.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao extends BaseDao<Address> {

    public AddressDao() {

        super(Address.class);
    }
}
