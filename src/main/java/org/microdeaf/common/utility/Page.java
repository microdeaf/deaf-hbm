package org.microdeaf.common.utility;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {

    private Integer pageCount;
    private Long total;
    private Integer pageSize;

}
