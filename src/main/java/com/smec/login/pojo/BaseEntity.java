package com.smec.login.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * 数据库超类
 * @author 刘鸿亮
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "CREATED_BY",columnDefinition="VARCHAR(50)")
    private String operator;

    @CreationTimestamp
    @Column(name = "CREATION_DATE",columnDefinition="DATE")
    private Date created;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATE_DATE",columnDefinition="DATE")
    private Date updated;

    @LastModifiedBy
    @Column(name = "LAST_UPDATED_BY",columnDefinition="VARCHAR(50)")
    private String modifier;

    @Version
    @Column(name = "VERSION_NUMBER",columnDefinition="Integer")
    private Integer numberVersion;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Integer getNumberVersion() {
        return numberVersion;
    }

    public void setNumberVersion(Integer numberVersion) {
        this.numberVersion = numberVersion;
    }
}
