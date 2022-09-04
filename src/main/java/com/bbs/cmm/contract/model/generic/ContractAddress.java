package com.bbs.cmm.contract.model.generic;

import lombok.*;

/**
 * @name          ContractAddress.
 * @description   Representa el model de la direccion del contrato.
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
public class ContractAddress {
    protected String deviceAddress;
    protected String networkAddress;
}
