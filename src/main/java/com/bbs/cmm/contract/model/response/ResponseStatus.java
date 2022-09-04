package com.bbs.cmm.contract.model.response;

import lombok.*;

/**
 * @name          ResponseStatus.
 * @description   Representa el model del estado de la respuesta.
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
public class ResponseStatus {
    protected String code;
    protected String message;
}