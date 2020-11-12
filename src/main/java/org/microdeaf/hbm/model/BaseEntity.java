package org.microdeaf.hbm.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private PK id;

    @CreatedBy
    @Column(name = "CREATE_BY", length = 15)
    @Size(max = 15)
    private String createBy;

    @CreatedDate
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @LastModifiedBy
    @Column(name = "UPDATE_BY", length = 15)
    @Size(max = 15)
    private String updateBy;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "IS_ACTIVE")
    private Integer isEnabled;

}
