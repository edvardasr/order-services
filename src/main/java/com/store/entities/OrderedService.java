package com.store.entities;

import org.bson.types.ObjectId;

public class OrderedService {

	private ObjectId _id;
	private String id;
	private String name;
	private String type;
	private String description;
	private Integer activeFrom;
	private Integer activeTo;
	
	private Integer timestamp;

	public ObjectId getOid() {
		return _id;
	}

	public void setOid(ObjectId _id) {
		this._id = _id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(Integer activeFrom) {
		this.activeFrom = activeFrom;
	}

	public Integer getActiveTo() {
		return activeTo;
	}

	public void setActiveTo(Integer activeTo) {
		this.activeTo = activeTo;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}
}
