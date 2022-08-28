package com.eyoco.springboot.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.eyoco.springboot.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
