package com.ghifar.microservices.core.product.productservice;

import com.ghifar.microservices.core.product.productservice.persistence.ProductEntity;
import com.ghifar.microservices.core.product.productservice.persistence.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PersistenceTests {

    @Autowired
    private ProductRepository repository;
    private ProductEntity savedEntity;

    @Before
    public void setupDb(){
        repository.deleteAll();
        ProductEntity entity= new ProductEntity(1,"n",1);
        savedEntity= repository.save(entity);
        assertEquals(entity, savedEntity);
    }


    @Test
    public void create(){
        ProductEntity newEntity= new ProductEntity(2,"n",2);
        savedEntity= repository.save(newEntity);

        ProductEntity foundEntity= repository.findById(newEntity.getId()).get();
        assertEqualsProduct(newEntity, foundEntity);

        assertEquals(2, repository.count());
    }

    @Test
    public void update(){
        savedEntity.setName("n2");
        repository.save(savedEntity);

        ProductEntity foundEntity=
                repository.findById(savedEntity.getId()).get();
        assertEquals(1,(long)foundEntity.getVersion());
        assertEquals("n2", foundEntity.getName());
    }

    @Test
    public void delete(){
        repository.delete(savedEntity);
        assertFalse(repository.existsById(savedEntity.getId()));
    }

    @Test
    public void getByProductId(){
        Optional<ProductEntity> entity=
                repository.findByProductId(savedEntity.getProductId());
        assertTrue(entity.isPresent());
        assertEqualsProduct(savedEntity, entity.get());
    }


    @Test(expected = DuplicateKeyException.class)
    public void duplicateError(){
        ProductEntity entity= new ProductEntity(savedEntity.getProductId(),"n",1);
        repository.save(entity);
    }

    private void assertEqualsProduct(ProductEntity expectedEntity, ProductEntity actualEntity) {
        assertEquals(expectedEntity.getId(),               actualEntity.getId());
        assertEquals(expectedEntity.getVersion(),          actualEntity.getVersion());
        assertEquals(expectedEntity.getProductId(),        actualEntity.getProductId());
        assertEquals(expectedEntity.getName(),           actualEntity.getName());
        assertEquals(expectedEntity.getWeight(),           actualEntity.getWeight());
    }
}
