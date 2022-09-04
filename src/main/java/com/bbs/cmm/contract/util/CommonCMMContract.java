package com.bbs.cmm.contract.util;

import com.bbs.cmm.CommonGenerateCMM;
import com.bbs.cmm.CommonParseCMM;
import com.bbs.cmm.CommonValidateCMM;
import com.bbs.cmm.contract.model.generic.Contract;
import com.bbs.cmm.contract.model.generic.ContractAddress;
import com.bbs.cmm.contract.model.request.Request;
import com.bbs.cmm.contract.model.request.RequestProperty;
import com.bbs.cmm.contract.model.request.RequestPropertyExpected;
import com.bbs.cmm.contract.model.response.Response;
import com.bbs.cmm.contract.model.response.ResponseStatus;
import com.bbs.cmm.exception.BBSParseException;
import com.bbs.cmm.exception.BBSValidateException;
import com.bbs.cmm.util.ConstantCMM;
import com.bbs.cmm.util.ConstantSystemCMM;

import java.util.List;
import java.util.UUID;

/**
 * @name          CommonCMMContract.
 * @description   Representa el codigo comun del contrato.
 * @creation      03/08/2022.
 * @user          @serbuitrago
 * @update        03/08/2022.
 * @user          @serbuitrago
 * @version       1.0.0.
 */
public class CommonCMMContract {

    private CommonCMMContract(){}

    /**
     * Metodo que permite construir la peticion.
     * @param id representa el id.
     * @param address representa la direccion.
     * @param any representa el cuerpo.
     * @param properties representa las propiedades.
     * @return {@link Request}
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    public static Request buildRequest(UUID id, ContractAddress address, Object any, List<RequestProperty> properties) throws BBSValidateException {
        return buildRequest(id, address, ConstantCMM.VALUE_UUID_SYSTEM, any, properties);
    }

    /**
     * Metodo que permite construir la peticion.
     * @param id representa el id.
     * @param address representa la direccion.
     * @param userId representa el id del usuario.
     * @param any representa el cuerpo.
     * @param properties representa las propiedades.
     * @return {@link Request}
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    public static Request buildRequest(UUID id, ContractAddress address, UUID userId, Object any, List<RequestProperty> properties) throws BBSValidateException {
        isProperties(properties);
        Request request = new Request();
        request.setId(id);
        request.setTraceId(CommonGenerateCMM.uuid());
        request.setDateTime(CommonGenerateCMM.date());
        request.setAddress(address);
        request.setUserId(userId);
        request.setAny(any);
        request.setProperties(properties);
        isContract(request, (UUID) ConstantCMM.VALUE_OBJECT_DEFAULT);
        return request;
    }

    /**
     * Metodo que permite construir una respuesta.
     * @param request representa el request.
     * @param any representa el objeto de respuesta.
     * @param status representa el estado de respuesta.
     * @return {@link Response}
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    public static Response buildResponse(Request request, Object any, ResponseStatus status) throws BBSValidateException {
        isContract(request, (UUID) ConstantCMM.VALUE_OBJECT_DEFAULT);
        isStatus(status);
        if(!CommonValidateCMM.isObject(any))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PAYLOAD , ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PAYLOAD);
        return buildContract(request, new Response(status), ConstantCMM.VALUE_BOOLEAN_TRUE);
    }

    /**
     * Metodo que permite parsear el objeto a una clase.
     * @param any representa el objeto.
     * @param clazz representa la instancia de la clase.
     * @return {@link T}
     * @param <T> representa el tipo de la clase.
     * @throws BBSParseException representa la excepcion de parseo capturada.
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    public static <T> T objectToClass(Object any, Class<?> clazz) throws BBSParseException, BBSValidateException {
        T parseAny = CommonParseCMM.ObjectToClass(any, clazz);
        if(!CommonValidateCMM.isObject(parseAny))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PAYLOAD_FORMAT, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PAYLOAD_FORMAT);
        return parseAny;
    }

    /**
     * Metodo que valida la peticion.
     * @param request representa la peticion.
     * @param traceId representa el id de la traza.
     * @param properties representa las propiedades esperadas.
     * @return {@link boolean}
     * @throws BBSValidateException representa la excepcion capturada.
     */
    public static boolean isRequest(Request request, UUID traceId, List<RequestPropertyExpected> properties) throws BBSValidateException {
        isContract(request, traceId);
        isProperties(request.getProperties(), properties);
        return ConstantCMM.VALUE_BOOLEAN_TRUE;
    }

    /**
     * Metodo que valida la respuesta.
     * @param response representa la respuesta.
     * @param status representa el estado esperado.
     * @return {@link boolean}
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    public static boolean isResponse(Response response, ResponseStatus status) throws BBSValidateException{
        isContract(response, (UUID) ConstantCMM.VALUE_OBJECT_DEFAULT);
        if(!CommonValidateCMM.isObject(response.getStatus(), status))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_STATUS, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_STATUS);
        if(!isStatus(status) || !isStatus(response.getStatus()))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_STATUS_NOT_VALID, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_STATUS_NOT_VALID);
        if(!isStatus(response, status.getCode()))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_STATUS_NOT_EXPECTED, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_STATUS_NOT_EXPECTED);
        return ConstantCMM.VALUE_BOOLEAN_TRUE;
    }

    /**
     * Metodo que permite validar la informacion del contrato.
     * @param contract representa el contrato.
     * @param traceId representa el identificador de la traza.
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    private static void isContract(Contract contract, UUID traceId) throws BBSValidateException{
        if(!CommonValidateCMM.isObject(contract))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_FORMAT, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_FORMAT);
        contract.setTraceId(CommonValidateCMM.isObject(traceId) ? traceId : contract.getTraceId());
        if(!CommonValidateCMM.isObject(contract.getId(), contract.getTraceId(), contract.getAddress(), contract.getUserId()))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_FORMAT, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_FORMAT);
        if(!CommonValidateCMM.isString(contract.getDateTime(), contract.getAddress().getDeviceAddress(), contract.getAddress().getNetworkAddress()))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_FORMAT, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_FORMAT);
        if(contract.getId().compareTo(contract.getTraceId()) == ConstantCMM.VALUE_INTEGER_ZERO)
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_UUID_EQUALS , ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_UUID_EQUALS );
        if(!CommonValidateCMM.isObject(contract.getAny()))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PAYLOAD, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PAYLOAD);
    }

    /**
     * Metodo que valida si el estado de respuesta es valido.
     * @param status representa el estado.
     * @return {@link boolean}
     */
    private static boolean isStatus(ResponseStatus status){
        return CommonValidateCMM.isObject(status, status.getCode(), status.getMessage());
    }

    /**
     * Metodo que permite validar el estado de la respuesta con otros estados esperados.
     * @param response representa la respuesta.
     * @param codes representa estados esperados.
     * @return {@link boolean}
     */
    private static boolean isStatus(Response response, String ... codes) {
        for (String code : codes){
            if(response.getStatus().getCode().equals(code))
                return ConstantCMM.VALUE_BOOLEAN_TRUE;
        }
        return ConstantCMM.VALUE_BOOLEAN_FALSE;
    }

    /**
     * Metodo que permite validar las propiedades.
     * @param properties representa las propiedades.
     * @return {@link boolean}
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    private static boolean isProperties(List<RequestProperty> properties) throws BBSValidateException {
        if(!CommonValidateCMM.isList(properties))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTIES, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTIES);
        for(RequestProperty property : properties){
            if(!isProperty(property))
                throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTIES, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTIES);
        }
        return ConstantCMM.VALUE_BOOLEAN_TRUE;
    }

    /**
     * Metodo que permite validar una propiedad.
     * @param property representa la propiedad.
     * @return {@link boolean}
     */
    private static boolean isProperty(RequestProperty property){
        return ConstantCMM.VALUE_OBJECT_DEFAULT != property && CommonValidateCMM.isString(property.getKey(), property.getValue());
    }

    /**
     * Metodo que permite validar si las propiedades son las esperadas.
     * @param propertiesActual representa las propiedades actuales.
     * @param propertiesExpected representa las propiedades esperadas.
     * @throws BBSValidateException representa la excepcion de validacion capturada.
     */
    private static boolean isProperties(List<RequestProperty> propertiesActual, List<RequestPropertyExpected> propertiesExpected) throws BBSValidateException {
        boolean isValid, isIgnore;
        int position;
        for(RequestProperty propertyActual : propertiesActual){
            if(!isProperty(propertyActual))
                throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTIES, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTIES);
            isValid = ConstantCMM.VALUE_BOOLEAN_FALSE;
            isIgnore = ConstantCMM.VALUE_BOOLEAN_FALSE;
            position = ConstantCMM.VALUE_INTEGER_ZERO;
            for(RequestPropertyExpected propertyExpected : propertiesExpected){
                if(!isProperty(propertyExpected))
                    throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTIES, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTIES);
                if(!propertyExpected.isIgnoreKey()){
                    if(propertyActual.getKey().equals(propertyExpected.getKey())){
                        isValid = ConstantCMM.VALUE_BOOLEAN_TRUE;
                        if(!propertyExpected.isIgnoreValue() && !propertyActual.getValue().equals(propertyExpected.getValue()))
                            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTY_VALUE , ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTY_VALUE);
                        break;
                    }
                    position++;
                }else{
                    isValid = ConstantCMM.VALUE_BOOLEAN_TRUE;
                    isIgnore = ConstantCMM.VALUE_BOOLEAN_TRUE;
                }
            }
            if(!isValid && !isIgnore)
                throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTY_FORMAT, ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTY_FORMAT);
            else if(isValid && !isIgnore) {
                propertiesExpected.remove(position);
                if(!CommonValidateCMM.isList(propertiesExpected))
                    return ConstantCMM.VALUE_BOOLEAN_TRUE;
            }
        }
        if(CommonValidateCMM.isList(propertiesExpected))
            throw new BBSValidateException(ConstantSystemCMM.CODE_ERROR_RESPONSE_STRUCTURE_PROPERTY_KEY , ConstantSystemCMM.MESSAGE_ERROR_RESPONSE_STRUCTURE_PROPERTY_KEY);
        return ConstantCMM.VALUE_BOOLEAN_TRUE;
    }

    /**
     * Metodo que convierte construye un objeto apartir de otro.
     * @param value representa el objeto con los valores nuevos.
     * @param type representa el objeto asignarle los valores.
     * @param isDate representa si se debe generar la fecha.
     * @return {@link T}
     * @param <D> representa el tipo de dato de los valores.
     * @param <T> representa el tipo de dato de la respuesta.
     */
    private static <D extends Contract, T extends Contract> T buildContract(D value, T type, boolean isDate){
        type.setId(value.getId());
        type.setTraceId(value.getTraceId());
        type.setDateTime(isDate ? CommonGenerateCMM.date() : value.getDateTime());
        type.setAddress(value.getAddress());
        type.setUserId(value.getUserId());
        type.setAny(value.getAny());
        return type;
    }
}
