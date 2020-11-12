package org.microdeaf.common.view;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseView<PK extends Serializable> implements Serializable {

    private PK id;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Integer isEnabled;
}
