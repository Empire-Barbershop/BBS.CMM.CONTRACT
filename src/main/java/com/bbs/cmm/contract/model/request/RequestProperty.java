package com.bbs.cmm.contract.model.request;

import lombok.*;

import java.io.Serializable;

/**
 * @name          RequestProperty.
 * @description   Representa el model de las propiedades de la peticion.
 * @creation      03/08/2022.
 * @user          @serbuitrago
 * @update        03/08/2022.
 * @user          @serbuitrago
 * @version       1.0.0.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestProperty implements Serializable {
    private String key;
    private String value;
}