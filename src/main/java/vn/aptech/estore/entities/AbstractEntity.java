package vn.aptech.estore.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    protected Timestamp createdDate = new Timestamp(System.currentTimeMillis());

    @LastModifiedDate
    @Column(name = "modified_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    protected Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());

    @CreatedBy
    @Column(name = "created_by", length = 45, columnDefinition = "VARCHAR(45) DEFAULT 'admin'")
    protected String createdBy = "admin";

    @LastModifiedBy
    @Column(name = "modified_by", length = 45, columnDefinition = "VARCHAR(45) DEFAULT 'admin'")
    protected String updatedBy = "admin";
}