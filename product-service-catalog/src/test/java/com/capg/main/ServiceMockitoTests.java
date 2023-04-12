package com.capg.main;

import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Order;
import com.capg.main.entity.Product;
import com.capg.main.repository.ProductRepository;
import com.capg.main.service.ProductService;

@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {
@Mock         //Used for mocking the repository class
ProductRepository crepo;

@InjectMocks  //Used for injecting/invoking the methods of service class
ProductService service;

public List<Product> myproducts;

@Test
@Order(1)         //For executing this method first
public void test_getProducts()
{
	List<Product> myproducts=new ArrayList<Product>();
	myproducts.add(new Product("1", "Tv", "Electronics",35908,"good"));
	myproducts.add(new Product("2", "Fridge","Electronics",35908,"good"));
	when(crepo.findAll()).thenReturn(myproducts);
	assertEquals(2,service.getProducts().size());        //Invoke the method from the service class
}

@Test
@Order(2)
public void test_addProduct()
{
	Product product = new Product("3", "Ac","Electronics",35908,"good");	
	when(crepo.save(product)).thenReturn(product);
	assertEquals(product,service.addProduct(product));
}

@Test
@Order(3)
public void test_updateProduct()
{
	Product product =new Product("3", "Ac", "Electronics",35908,"good");	
	when(crepo.save(product)).thenReturn(product);
	assertEquals(product,service.updateProduct(product));
}


//@Test
//@Order(3)
//public void deleteById23() {
	
	//UserProfile user = new UserProfile(112, "Rohan Biswas", "RohanBiswas.com","Male", "Malda","Rohan@2000");
	//when(crepo.deleteById23().thenReturn(user));
	//assertEquals(user,service.deleteById23(1));
	
@Test
@Order(4)
public void test_deleteProduct() {
	Product product =new Product("3", "Ac", "Electronics",35908,"good");	
	service.deleteById("3");
	verify(crepo, times(1)).deleteById(product.getId());
}
}
