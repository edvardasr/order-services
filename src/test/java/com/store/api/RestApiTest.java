package com.store.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.store.entities.Customer;
import com.store.entities.OrderedService;
import com.store.mongo.Repository;

@ExtendWith(MockitoExtension.class)
public class RestApiTest {

	@Mock
	private Repository repository;

	private RestApi restApi;

	@BeforeEach
	void init() {
		restApi = new RestApi();
		restApi.setRepository(repository);
	}

	@Test
	void test_getAllCustomers_success() {

		// prepare
		List<Customer> list = new ArrayList<Customer>();
		when(repository.getCustomers()).thenReturn(list);

		// act
		ResponseEntity<List<Customer>> response = restApi.getAllCustomers();

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), list);
	}

	@Test
	void test_getAllCustomers_notFound() {

		// prepare
		when(repository.getCustomers()).thenReturn(null);

		// act
		ResponseEntity<List<Customer>> response = restApi.getAllCustomers();

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	void test_getCustomer_success() {

		// prepare
		String id = "id";
		Customer customer = new Customer();
		when(repository.getCustomerById(id)).thenReturn(customer);

		// act
		ResponseEntity<Customer> response = restApi.getCustomer(id);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), customer);
	}

	@Test
	void test_getCustomer_notFound() {

		// prepare
		String id = "id";
		when(repository.getCustomerById(id)).thenReturn(null);

		// act
		ResponseEntity<Customer> response = restApi.getCustomer(id);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	
	@Test
	void test_saveCustomer_success() {

		// prepare
		Customer customer = new Customer();
	

		// act
		ResponseEntity<Customer> response = restApi.saveCustomer(customer);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		verify(repository).insertCustomer(customer);
	}
	
	@Test
	void test_updateCustomer_success() {

		// prepare
		String id = "id";
		Customer customer = new Customer();
		when(repository.updateCustomer(id, customer)).thenReturn(customer);
	

		// act
		ResponseEntity<Customer> response = restApi.updateCustomer(id, customer);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), customer);
		verify(repository).updateCustomer(id, customer);
	}
	
	@Test
	void test_deleteCustomer_success() {

		// prepare
		String id = "id";
		Customer customer = new Customer();
		when(repository.deleteCustomer(id)).thenReturn(customer);
	

		// act
		ResponseEntity<Customer> response = restApi.deleteCustomer(id);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), customer);
		verify(repository).deleteCustomer(id);
	}
	
	@Test
	void test_getAllOrderedServices_success() {

		// prepare
		List<OrderedService> list = new ArrayList<OrderedService>();
		when(repository.getOrderedServices()).thenReturn(list);

		// act
		ResponseEntity<List<OrderedService>> response = restApi.getAllOrderedServices();

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), list);
	}

	@Test
	void test_getAllOrderedServices_notFound() {

		// prepare
		when(repository.getOrderedServices()).thenReturn(null);

		// act
		ResponseEntity<List<OrderedService>> response = restApi.getAllOrderedServices();

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	void test_getOrderedService_success() {

		// prepare
		String id = "id";
		OrderedService orderedService = new OrderedService();
		when(repository.getOrderedServiceById(id)).thenReturn(orderedService);

		// act
		ResponseEntity<OrderedService> response = restApi.getOrderedService(id);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), orderedService);
	}

	@Test
	void test_getOrderedService_notFound() {

		// prepare
		String id = "id";
		when(repository.getOrderedServiceById(id)).thenReturn(null);

		// act
		ResponseEntity<OrderedService> response = restApi.getOrderedService(id);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	
	@Test
	void test_saveOrderedService_success() {

		// prepare
		OrderedService orderedService = new OrderedService();
	

		// act
		ResponseEntity<OrderedService> response = restApi.saveOrderedService(orderedService);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		verify(repository).insertOrderedService(orderedService);
	}
	
	@Test
	void test_updateOrderedService_success() {

		// prepare
		String id = "id";
		OrderedService orderedService = new OrderedService();
		when(repository.updateOrderedService(id, orderedService)).thenReturn(orderedService);
	

		// act
		ResponseEntity<OrderedService> response = restApi.updateOrderedService(id, orderedService);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), orderedService);
		verify(repository).updateOrderedService(id, orderedService);
	}
	
	@Test
	void test_deleteOrderedService_success() {

		// prepare
		String id = "id";
		OrderedService orderedService = new OrderedService();
		when(repository.deleteOrderedService(id)).thenReturn(orderedService);
	

		// act
		ResponseEntity<OrderedService> response = restApi.deleteOrderedService(id);

		// verify
		assertNotEquals(null, response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), orderedService);
		verify(repository).deleteOrderedService(id);
	}
	


}
