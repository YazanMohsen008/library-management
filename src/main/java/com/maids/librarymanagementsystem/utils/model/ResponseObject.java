package com.maids.librarymanagementsystem.utils.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject<Domain> implements Serializable {

    private List<Domain> list;
    private Object model;
    private String returnCode;
    private String message;
    private Map<String, Object> extraData;


    private ResponseObject(Object responseToBeReturned, ReturnCode returnCode, Text text, Map<String, Object> extraData) {
        this();
        if (responseToBeReturned instanceof List)
            this.list = (List) responseToBeReturned;
        else
            this.model = responseToBeReturned;
        this.returnCode = returnCode.getReturnCode();
        this.message = text.getText();
        this.extraData.put("extraData", extraData);

    }

    public ResponseObject(String message, ReturnCode returnCode, List<Domain> list, Domain model, Map<String, Object> extraData) {
        this.message = message;
        this.returnCode = returnCode.getReturnCode();
        this.list = list;
        this.model = model;
        this.extraData = extraData;
    }

    public ResponseObject(Text text, ReturnCode returnCode, List<Domain> list, Domain model, Map<String, Object> extraData) {
        this.message = text.getText();
        this.returnCode = returnCode.getReturnCode();
        this.list = list;
        this.model = model;
        this.extraData = extraData;
    }

    public ResponseObject() {
        super();
        this.extraData = new HashMap<>();

    }


    public static ResponseObject LOGGED_IN(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.SUCCESS, Text.LOGGED_IN, extraData);
    }

    public static ResponseObject<?> LOGIN_FAILED(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.FAILED, Text.LOGIN_FAILED, extraData);
    }

    public static ResponseObject ADDED_SUCCESS(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.SUCCESS, Text.ADDED_SUCCESSFULLY, extraData);

    }

    public static ResponseObject<?> ADDING_FAILED(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.FAILED, Text.ADDING_FAILED, extraData);
    }

    public static ResponseObject<?> UPDATED_SUCCESS(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.SUCCESS, Text.UPDATED_SUCCESSFULLY, extraData);

    }

    public static ResponseObject<?> UPDATING_FAILED(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.FAILED, Text.UPDATING_FAILED, extraData);

    }


    public static ResponseObject<?> DELETED_SUCCESS(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.SUCCESS, Text.DELETED_SUCCESSFULLY, extraData);
    }

    public static ResponseObject<?> DELETING_FAILED(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.FAILED, Text.DELETING_FAILED, extraData);
    }


    public static ResponseObject<?> FETCHED_SUCCESS(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.SUCCESS, Text.FETCHED_SUCCESSFULLY, extraData);

    }

    public static ResponseObject<?> FETCHING_FAILED(Object response, Map<String, Object> extraData) {
        return new ResponseObject(response, ReturnCode.FAILED, Text.FETCHING_FAILED, extraData);
    }

    public enum ReturnCode {
        SUCCESS("Success"),
        FAILED("Failed");

        private final String returnCode;

        ReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnCode() {
            return returnCode;
        }
    }

    public enum Text {
        LOGGED_IN("LoggedInSuccessfully"),
        LOGIN_FAILED("LoginFailed"),
        ADDED_SUCCESSFULLY("AddedSuccessfully"),
        ADDING_FAILED("AddingFailed"),
        UPDATED_SUCCESSFULLY("UpdatedSuccessfully"),
        UPDATING_FAILED("UpdatingFailed"),
        FETCHED_SUCCESSFULLY("FetchedSuccessfully"),
        FETCHING_FAILED("FetchingFailed"),
        DELETED_SUCCESSFULLY("DeletedSuccessfully"),
        DELETING_FAILED("DeletingFailed"),
        ;

        private final String text;

        Text(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    @JsonAnyGetter
    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
