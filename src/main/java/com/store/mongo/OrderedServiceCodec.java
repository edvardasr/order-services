package com.store.mongo;

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

import com.store.entities.OrderedService;

public class OrderedServiceCodec implements CollectibleCodec<OrderedService> {

	private Codec<Document> documentCodec;

	public OrderedServiceCodec() {

		this.documentCodec = new DocumentCodec();
	}

	public OrderedServiceCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, OrderedService value, EncoderContext encoderContext) {
		Document document = new Document();
		
		ObjectId oid = value.getOid();
		String id = value.getId();
		String name = value.getName();
		String type = value.getType();
		String description = value.getDescription();
		Integer activeFrom = value.getActiveFrom();
		Integer activeTo = value.getActiveTo();
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

		if (null != type) {
			document.put("type", type);
		}
		
		if (null != description) {
			document.put("description", description);
		}
		
		if (null != activeFrom) {
			document.put("activeFrom", activeFrom);
		}
		
		if (null != activeTo) {
			document.put("activeTo", activeTo);
		}
		
		if (null != timestamp) {
			document.put("timestamp", timestamp);
		}

		documentCodec.encode(writer, document, encoderContext);

	}

	@Override
	public Class<OrderedService> getEncoderClass() {
		return OrderedService.class;
	}

	@Override
	public OrderedService decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		OrderedService orderedService = new OrderedService();

		orderedService.setOid(document.getObjectId("_id"));

		orderedService.setId(document.getString("id"));

		orderedService.setName(document.getString("name"));
		
		orderedService.setType(document.getString("type"));
		
		orderedService.setDescription(document.getString("description"));
		
		orderedService.setActiveFrom(document.getInteger("activeFrom"));
		
		orderedService.setActiveTo(document.getInteger("activeTo"));
		
		orderedService.setTimestamp(document.getInteger("timestamp"));

		return orderedService;
	}

	@Override
	public OrderedService generateIdIfAbsentFromDocument(OrderedService orderedService) {
		if (!documentHasId(orderedService)) {
			orderedService.setOid(new ObjectId());
		}

		return orderedService;
	}

	@Override
	public boolean documentHasId(OrderedService orderedService) {
		return null == orderedService.getOid();
	}

	@Override
	public BsonValue getDocumentId(OrderedService orderedService) {
		if (!documentHasId(orderedService)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(orderedService.getOid().toHexString());
	}
}