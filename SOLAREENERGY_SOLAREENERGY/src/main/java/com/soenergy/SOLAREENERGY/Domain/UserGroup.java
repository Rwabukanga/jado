package com.soenergy.SOLAREENERGY.Domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)  ////  doneAt and lastUpdatedAt fields should automatically get populated whenever we create or update an entity.
public class UserGroup implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String uuid=UUID.randomUUID().toString();
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Date doneAt; //= new Timestamp(new Date().getTime());
	private String doneBy = "";
	@Temporal(TemporalType.TIMESTAMP) 
    @LastModifiedDate
	private Date lastUpdatedAt; // = null;
	private String lastUpdatedBy = "";
	private boolean deletedStatus = false;

	
	//private String objectName; // which object that is referred to								
	//private long objectId; // id of the object that the is referred to
	private String applicationName; // an application where the object belong
	
	@ManyToOne
	public SystemUser systemUser;
	@ManyToOne
	public TheGroup group;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getDoneAt() {
		return doneAt;
	}
	public void setDoneAt(Date doneAt) {
		this.doneAt = doneAt;
	}
	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
	public boolean isDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(boolean deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public String getDoneBy() {
		return doneBy;
	}
	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public TheGroup getGroup() {
		return group;
	}
	public void setGroup(TheGroup group) {
		this.group = group;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	
	
}
