package com.bbs.cmm.contract.model.response;

import com.bbs.cmm.contract.model.generic.Contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @name          Response.
 * @description   Representa el model de la respuesta.
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
public class Response extends Contract implements Serializable {
    protected ResponseStatus status;
}