package org.fzb.multDatasource.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * BaseDO
 *
 * @author: fengzhenbing
 */
@Data
public class BaseDO  implements Serializable {
    private BigInteger id;
}
