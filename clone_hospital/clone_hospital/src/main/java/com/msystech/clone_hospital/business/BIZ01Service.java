package com.msystech.clone_hospital.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BIZ01Service {
    private final org.slf4j.Logger LOGGER 			= org.slf4j.LoggerFactory.getLogger("TYPHOON_LOG");

    @Autowired
    private SolutionConfig solutionConfig;

    public JSONResMessage list(JSONObject paramJson) throws Exception {
        JSONResMessage resJson = new JSONResMEssage(JSONResMessage.SUCCESS);

        try {

        } catch(Exception e) {
            LOGGER.error("biz01service.list", e);
            throw e;
        }
        return resJson;

    }

    pubilic JSONResMessage detail(JSONObject paramJson) throws Exception {
        JSONResMessage resJson = new JSONResMEssage(JSONResMEssage.SUCCESS);

        try {

        } catch(Exception e) {
            LOGGER.error("biz01Service.insert", e);
            throw e;
        }
        return resJson;
    }

    @Transactional(rollbackFor = { Exception.class}) //어노테이션 transactional 을 사용하기 위해선 xml 에서 설정이 필요한다
    public JSONResMessage insert(JSONObject paramJson) throws Exception {
        JSONResMessage resJson   = new JSONResMessage(JSONResMessage.SUCCESS);

        try {
        }catch(Exception e) {
            LOGGER.error("biz01Service.insert", e);
            throw e;
        }
        return resJson;
    }

    @Transactional(rollbackFor = {Exception.class})
    public JSONResMessage update(JSONObject paramJson) throws Exception {
        JSONResMessage resJson  = new JSONResMessage(JSONResMessage.SUCCESS);

        try {
        }catch(Exception e) {
            LOGGER.error("biz01Service.udpate", e);
            throw e;
        }
        return resJson;
    }

    @Transactional(rollbackFor = { Exception.class})
    public JSONResMessage delete (JSONObject paramJson) throws Exception {
        JSONResMessage resJson  = new JSONResMessage(JSONResMessage.SUCCESS);

        try {
        }catch(Exception e) {
            LOGGER.error("biz01Service.delete", e);
            throw e;
        }
        return resJson;
    }

}
