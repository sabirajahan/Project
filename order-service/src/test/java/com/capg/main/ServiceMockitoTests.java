//package com.capg.main;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.mockito.Mock;
//import org.mockito.InjectMocks;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.Order;
//
//import com.capg.main.entity.Orders;
//import com.capg.main.entity.Product;
//import com.capg.main.repository.OrderRepository;
//import com.capg.main.service.OrderService;
//
//@SpringBootTest(classes= {ServiceMockitoTests.class})
//public class ServiceMockitoTests {
//@Mock         //Used for mocking the repository class
//OrderRepository crepo;
//
//@InjectMocks  //Used for injecting/invoking the methods of service class
//OrderService service;
//
//public List<Orders> ords;
//
//@Test
//@Order(1)         //For executing this method first
//public void test_getOrders()
//{
//	List<Orders> myorders=new ArrayList<Orders>();
//	 myorders.add(new Orders("1","01-11-2022","Roumita","Malda","TV", "Electronics",3000,"Awesome television"));
//	 myorders.add(new Orders("2","02-12-2022","Rohan","Kolkata", "Ac","Electronics",2590, "Awesome product"));
//	when(crepo.findAll()).thenReturn( myorders);
//	assertEquals(2,service.getOrder().size());        //Invoke the method from the service class
//}
//
//@Test
//@Order(2)
//public void test_addOrders()
//{
//	Orders order = new Orders("3","03-12-2022","Rohan","Kolkata", "Ac","Electronics",2590, "Awesome product" );	
//	when(crepo.save(order)).thenReturn(order);
//	assertEquals(order,service.addOrder(order));
//}
//
//@Test
//@Order(3)
//public void test_updateOrder()
//{
//	Orders order =new Orders("3","03-12-2022","Rohana","Kolkata", "Ac","Electronics",2590, "Awesome product");	
//	when(crepo.save(order)).thenReturn(order);
//	assertEquals(order,service.updateOrder(order));
//}
//
//
////@Test
////@Order(3)
////public void deleteById23() {
//	
//	//UserProfile user = new UserProfile(112, "Rohan Biswas", "RohanBiswas.com","Male", "Malda","Rohan@2000");
//	//when(crepo.deleteById23().thenReturn(user));
//	//assertEquals(user,service.deleteById23(1));
//	
//@Test
//@Order(4)
//public void test_deleteProduct() {
//	Orders order =new Orders("3","03-12-2022", "Rohana","Kolkata", "Ac","Electronics",2590, "Awesome product");	
//	service.deleteById("3");
//	verify(crepo, times(1)).deleteById(order.getOid());
//}
//}
