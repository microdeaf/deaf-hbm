package org.microdeaf.common.utility;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private List<T> result;
    private Integer pageNumber;
    private Integer total;
    private Integer pageSize;

}
