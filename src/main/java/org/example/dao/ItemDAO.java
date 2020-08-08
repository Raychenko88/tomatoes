package org.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemDAO, Integer> {

    //тут хз, помоему не нужно дао на итем
}
