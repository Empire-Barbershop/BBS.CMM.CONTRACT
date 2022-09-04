package com.bbs.cmm.contract.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @name          RequestPropertyExpected.
 * @description   Representa el model de las propiedades esperadas de la peticion.
 * @creation      03/08/2022.
 * @user          @serbuitrago
 * @update        03/08/2022.
 * @user          @serbuitrago
 * @version       1.0.0.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPropertyExpected extends RequestProperty {
    private boolean ignoreKey;
    private boolean ignoreValue;
}