package com.catalogue.Repository.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogue.Model.Inventory;

@Repository
public interface IProductRepository extends JpaRepository<Inventory, Long> {

}
