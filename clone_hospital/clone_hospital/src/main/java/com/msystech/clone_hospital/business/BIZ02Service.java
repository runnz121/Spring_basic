package com.msystech.clone_hospital.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BIZ02Service {
    private final org.slf4j.Logger LOGGER 			= org.slf4j.LoggerFactory.getLogger("TYPHOON_LOG");

    public JSONResMessage list(JSONObject paramJSon) throws Exception {
        JSONResMessage resJson  =   new JSONResMessage (JsonResMEssage.SUCCESS);

        try{
        } catch(Exception e) {
            LOGGER.error("biz02service.list", e);
            throw e;
        }
        return resJson;
    }

    public JSONResMessage detail(JSONObject paramJson) throws Exception {
        JSONResmessage resjson = new JSONResMessage(JSONResMEssage.SUCCESS);

        try {
        } catch(Exception e) {
            LOGGER.error("biz02Service.detail", e);
            throw e;
        }
        return resJson;
    }

    @Transactional(rollbackFor = { Exception.class })
    public JSONResMessage insert(JSONObject paramJson) throws Exception {
        JSONResMessage resJson 				= new JSONResMessage(JSONResMessage.SUCCESS);

        try {
            //this.biz02SQLMapper.insert(paramJson);
            //resJson.setStatusAndDefaultMessage(JSONResMessage.SUCCESS);
        } catch(Exception e) {
            //resJson.setStatusAndDefaultMessage(JSONResMessage.FAIL);
            LOGGER.error("biz02Service.insert", e);
            throw e;
        }

        return resJson;
    }

    @Transactional(rollbackFor = { Exception.class })
    public JSONResMessage update(JSONObject paramJson) throws Exception {
        JSONResMessage resJson 				= new JSONResMessage(JSONResMessage.SUCCESS);

        try {
            //this.biz02SQLMapper.update(paramJson);
            //resJson.setStatusAndDefaultMessage(JSONResMessage.SUCCESS);
        } catch(Exception e) {
            //resJson.setStatusAndDefaultMessage(JSONResMessage.FAIL);
            LOGGER.error("biz02Service.update", e);
            throw e;
        }

        return resJson;
    }

    @Transactional(rollbackFor = { Exception.class })
    public JSONResMessage delete(JSONObject paramJson) throws Exception {
        JSONResMessage resJson 				= new JSONResMessage(JSONResMessage.SUCCESS);

        try {
            //this.biz02SQLMapper.delete(paramJson);
            //resJson.setStatusAndDefaultMessage(JSONResMessage.SUCCESS);
        } catch(Exception e) {
            //resJson.setStatusAndDefaultMessage(JSONResMessage.FAIL);
            LOGGER.error("biz02Service.delete", e);
            throw e;
        }

        return resJson;
    }
}
