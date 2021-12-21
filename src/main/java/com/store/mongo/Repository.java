package com.store.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.store.entities.Customer;
import com.store.entities.OrderedService;

@Component
public class Repository {

	private static final Logger logger = LoggerFactory.getLogger(Repository.class);

	private MongoCollection<Customer> collectionCustomer;
	private MongoCollection<OrderedService> collectionOrderedService;

	@SuppressWarnings("resource")
	public Repository() {

		CodecRegistry codecRegistry = MongoClient.getDefaultCodecRegistry();
		Codec<Document> documentCodec = codecRegistry.get(Document.class);
		Codec<Customer> dataCodec = new CustomerCodec(documentCodec);

		codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(documentCodec, dataCodec));

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

		MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017), options);

		collectionCustomer = mongo.getDatabase("db").getCollection("customer", Customer.class);
		collectionOrderedService = mongo.getDatabase("db").getCollection("orderedService", OrderedService.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Customer> getCustomers() {
		logger.debug("Entering getCustomers");

		List<Customer> customers = (List<Customer>) collectionCustomer.find().into(new ArrayList());

		logger.debug("Leaving getCustomers");
		return customers;
	}

	public Customer getCustomerById(String id) {
		logger.debug("Entering getCustomerById");

		Customer customer = collectionCustomer.find(Filters.eq("id", id)).first();

		logger.debug("Leaving getCustomerById");
		return customer;
	}

	public void insertCustomer(Customer customer) {
		logger.debug("Entering insertCustomer");

		collectionCustomer.insertOne(customer);

		logger.debug("Leaving insertCustomer");
	}

	public Customer updateCustomer(String id, Customer customer) {

		logger.debug("Entering updateCustomer");

		Customer replacedCustomer = collectionCustomer.findOneAndReplace(Filters.eq("id", id), customer);

		logger.debug("Leaving updateCustomer");

		return replacedCustomer;
	}

	public Customer deleteCustomer(String id) {
		logger.debug("Entering deleteCustomer");

		Customer removedCustomer = collectionCustomer.findOneAndDelete(Filters.eq("id", id));

		logger.debug("Leaving deleteCustomer");

		return removedCustomer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<OrderedService> getOrderedServices() {
		logger.debug("Entering getOrderedServices");

		List<OrderedService> orderedServices = (List<OrderedService>) collectionOrderedService.find()
				.into(new ArrayList());

		logger.debug("Leaving getOrderedServices");
		return orderedServices;
	}

	public OrderedService getOrderedServiceById(String id) {
		logger.debug("Entering getOrderedServiceById");

		OrderedService orderedService = collectionOrderedService.find(Filters.eq("id", id)).first();

		logger.debug("Leaving getOrderedServiceById");
		return orderedService;
	}

	public void insertOrderedService(OrderedService orderedService) {
		logger.debug("Entering insertOrderedService");

		collectionOrderedService.insertOne(orderedService);

		logger.debug("Leaving insertOrderedService");
	}

	public OrderedService updateOrderedService(String id, OrderedService orderedService) {

		logger.debug("Entering updateOrderedService");

		OrderedService replacedOrderedService = collectionOrderedService.findOneAndReplace(Filters.eq("id", id),
				orderedService);

		logger.debug("Leaving updateOrderedService");

		return replacedOrderedService;
	}

	public OrderedService deleteOrderedService(String id) {
		logger.debug("Entering deleteOrderedService");

		OrderedService removedOrderedService = collectionOrderedService.findOneAndDelete(Filters.eq("id", id));

		logger.debug("Leaving deleteOrderedService");

		return removedOrderedService;
	}
}
