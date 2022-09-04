package com.bbs.cmm.contract.model.request;

import com.bbs.cmm.contract.model.generic.Contract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @name          Request.
 * @description   Representa el model de la peticion.
 * @creation      03/08/2022.
 * @user          @serbuitrago
 * @update        03/08/2022.
 * @user          @serbuitrago
 * @version       1.0.0.
 */
@Getter
@Setter
@NoArgsConstructor
public class Request extends Contract implements Serializable {
    protected List<RequestProperty> properties;
}
