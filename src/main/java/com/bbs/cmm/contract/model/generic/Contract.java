package com.bbs.cmm.contract.model.generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * @name          Contract.
 * @description   Representa el model del contrato.
 * @creation      03/08/2022.
 * @user          @serbuitrago
 * @update        03/08/2022.
 * @user          @serbuitrago
 * @version       1.0.0.
 */
@Getter
@Setter
@NoArgsConstructor
public class Contract implements Serializable {

    protected UUID id;
    protected UUID traceId;
    protected String dateTime;
    protected ContractAddress address;
    protected UUID userId;
    protected Object any;
}
