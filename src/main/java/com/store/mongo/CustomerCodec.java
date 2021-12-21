package com.store.mongo;

import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.store.entities.Customer;

public class CustomerCodec implements CollectibleCodec<Customer> {

	private Codec<Document> documentCodec;

	public CustomerCodec() {

		this.documentCodec = new DocumentCodec();
	}

	public CustomerCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Customer value, EncoderContext encoderContext) {
		Document document = new Document();

		ObjectId oid = value.getOid();
		String id = value.getId();
		String name = value.getName();
		String surname = value.getSurname();
		String companyName = value.getCompanyName();
		String companyCode = value.getCompanyCode();
		String personalCode = value.getPersonalCode();
		List<String> services = value.getServices();
		Integer timestamp = value.getTimestamp();

		if (null != oid) {
			document.put("_id", oid);
		}

		if (null != id) {
			document.put("id", id);
		}

		if (null != name) {
			document.put("title", name);
		}

		if (null != surname) {
			document.put("surname", surname);
		}
		
		if (null != companyName) {
			document.put("companyName", companyName);
		}
		
		if (null != companyCode) {
			document.put("companyCode", companyCode);
		}
		
		if (null != personalCode) {
			document.put("personalCode", personalCode);
		}
		
		if (null != services) {
			document.put("services", services);
		}
		
		if (null != timestamp) {
			document.put("timestamp", timestamp);
		}

		documentCodec.encode(writer, document, encoderContext);

	}

	@Override
	public Class<Customer> getEncoderClass() {
		return Customer.class;
	}

	@Override
	public Customer decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		Customer customer = new Customer();

		customer.setOid(document.getObjectId("_id"));

		customer.setId(document.getString("id"));

		customer.setName(document.getString("name"));
		
		customer.setSurname(document.getString("surname"));
		
		customer.setCompanyName(document.getString("companyName"));
		
		customer.setCompanyCode(document.getString("companyCode"));
		
		customer.setPersonalCode(document.getString("personalCode"));
		
		customer.setServices(document.getList("services", String.class));

		customer.setTimestamp(document.getInteger("timestamp"));

		return customer;
	}

	@Override
	public Customer generateIdIfAbsentFromDocument(Customer data) {
		if (!documentHasId(data)) {
			data.setOid(new ObjectId());
		}

		return data;
	}

	@Override
	public boolean documentHasId(Customer data) {
		return null == data.getOid();
	}

	@Override
	public BsonValue getDocumentId(Customer data) {
		if (!documentHasId(data)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(data.getOid().toHexString());
	}
}