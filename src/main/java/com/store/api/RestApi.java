package com.store.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.entities.Customer;
import com.store.entities.OrderedService;
import com.store.mongo.Repository;

@RestController
@RequestMapping("/api/v1")
public class RestApi {

	private static final Logger logger = LoggerFactory.getLogger(RestApi.class);

	private Repository repository;

	public RestApi() {

	}

	@Autowired
	public RestApi(Repository repository) {

		this.repository = repository;
	}

	@RequestMapping(value = "/customer/retrieveAll", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers() {

		logger.debug("Entering getAllCustomers");

		List<Customer> list = repository.getCustomers();

		if (null == list) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
		}

		logger.debug("Leaving getAllCustomers");

		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/retrieve", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@RequestParam("id") String id) {

		logger.debug("Entering getCustomer");

		Customer customer = repository.getCustomerById(id);

		if (null == customer) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		logger.debug("Leaving getCustomer");

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {

		logger.debug("Entering saveCustomer");

		repository.insertCustomer(customer);

		logger.debug("Leaving saveCustomer");

		return new ResponseEntity<Customer>(HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {

		logger.debug("Entering insertCustomer");

		Customer replacedCustomer = repository.updateCustomer(id, customer);

		logger.debug("Leaving insertCustomer");

		return new ResponseEntity<Customer>(replacedCustomer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") String id) {

		logger.debug("Entering deleteCustomer");

		Customer deletedCustomer = repository.deleteCustomer(id);

		logger.debug("Leaving deleteCustomer");

		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orderedservice/retrieveAll", method = RequestMethod.GET)
	public ResponseEntity<List<OrderedService>> getAllOrderedServices() {

		logger.debug("Entering getAllOrderedServices");

		List<OrderedService> list = repository.getOrderedServices();

		if (null == list) {
			return new ResponseEntity<List<OrderedService>>(HttpStatus.NOT_FOUND);
		}

		logger.debug("Leaving getAllOrderedServices");

		return new ResponseEntity<List<OrderedService>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/orderedservice/retrieve", method = RequestMethod.GET)
	public ResponseEntity<OrderedService> getOrderedService(@RequestParam("id") String id) {

		logger.debug("Entering getOrderedService");

		OrderedService orderedService = repository.getOrderedServiceById(id);

		if (null == orderedService) {
			return new ResponseEntity<OrderedService>(HttpStatus.NOT_FOUND);
		}

		logger.debug("Leaving getOrderedService");

		return new ResponseEntity<OrderedService>(orderedService, HttpStatus.OK);
	}

	@RequestMapping(value = "/orderedservice", method = RequestMethod.POST)
	public ResponseEntity<OrderedService> saveOrderedService(@RequestBody OrderedService orderedService) {

		logger.debug("Entering saveOrderedService");

		repository.insertOrderedService(orderedService);

		logger.debug("Leaving saveOrderedService");

		return new ResponseEntity<OrderedService>(HttpStatus.OK);
	}

	@RequestMapping(value = "/orderedservice/{id}", method = RequestMethod.PUT)
	public ResponseEntity<OrderedService> updateOrderedService(@PathVariable("id") String id, @RequestBody OrderedService orderedService) {

		logger.debug("Entering updateOrderedService");

		OrderedService replacedOrderedService = repository.updateOrderedService(id, orderedService);

		logger.debug("Leaving updateOrderedService");

		return new ResponseEntity<OrderedService>(replacedOrderedService, HttpStatus.OK);
	}

	@RequestMapping(value = "/orderedservice/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<OrderedService> deleteOrderedService(@PathVariable("id") String id) {

		logger.debug("Entering deleteOrderedService");

		OrderedService deletedOrderedService = repository.deleteOrderedService(id);

		logger.debug("Leaving deleteOrderedService");

		return new ResponseEntity<OrderedService>(deletedOrderedService, HttpStatus.OK);
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
}