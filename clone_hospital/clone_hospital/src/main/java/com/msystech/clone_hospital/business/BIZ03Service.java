package com.msystech.clone_hospital.business;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;

public class BIZ03Service {
    private final org.slf4j.Logger LOGGER 			= org.slf4j.LoggerFactory.getLogger("TYPHOON_LOG");

    @Autowired
    private SolutionConfig solutionConfig;

    @Resource
    private FaqSQLMapper faqSQLMapper;

    @Transactional(rollbackFor = {Exception.class})
    public JSONResMessage bIZ0302_IA(JSONObject paramJson) throws Exception {
        JSONResMessage resJson  =   new JSONResMEssage(JSONResMessasge.SUCCESS);

        try {
            this.faqSQLMapper.insert(paramJson);
        } catch(Exception e) {
            LOGGER.error("biz0302_IA", e);
            throw e;
        }

        return resJson;
    }

    public JSONResMessage bIZ0302_LA(JSONObject paramJson) throws Exception {
        JSONResMessage resJson 		= new JSONResMessage(JSONResMessage.SUCCESS);
        if(!paramjson.containsKey("cpage")) {
            paramJson.put("cpage", 1);
        }
        int pageSize    =   12;
        int iDisplayStart   =   (paramJson.getIntValue("cpage")-1)*pageSize;
        paramJson.put("iDisplayStart", iDisplayStart);
        paramJson.put("iDisplayEnd", pageSize);
        paramJson.put("sort", "REGDT DESC");

        String word     =   paramJson.getString("w");
        String field    =   paramJson.getSTring("w_filed");
        if (field == null) {

        } else if (field.equals("T")) {
            paramJson.put("title_like", word);
        } else if (field.equals("B")) {
            paramJson.put("bbs_body_like", word);
        } else if (field.equals("F")) {
            paramJson.put("att_file_originnm", word);
        }

        if (paramJson.containsKey("regdt") && paramJson.getString("regdt").length() > 0) {
            paramJson.put("regdt", STRINGUtil.replace(paramJson.getString("regdt"), ". ", "-"));
        }

        try {
            int count 				= this.faqSQLMapper.count(paramJson);
            JSONArray list 			= this.faqSQLMapper.select(paramJson);
            resJson.put("aaData", list);
            resJson.put("iTotalRecords", count);
            resJson.put("iTotalDisplayRecords", count);
            resJson.put("pageSize", pageSize);
            resJson.put("cpage", paramJson.getIntValue("cpage"));
            resJson.put("pagecount", ((count-1)/pageSize) + 1);
            resJson.setStatusAndDefaultMessage(JSONResMessage.SUCCESS);
        } catch(Exception e) {
            LOGGER.error("bIZ0302_LA", e);
            throw e;
        }
        return resJSon;
    }

    public JSONObject bIZ0302_E(JSONObject paramJson, String userip) throws Exception {
        JSONObject bean     =   null;
        JSONObject sqlParam =   new JSONObject();
        sqlParam.put("faq_uid", paramJson.getIntValue("uid"));
        try {
            JSONObject sqlJson 		= new JSONObject();
            sqlJson.put("view_ip", userip);
            sqlJson.put("faq_uid", sqlParam.getIntValue("faq_uid"));

            bean = this.faqSQLMapper.select_row(sqlParam);
            JSONArray list = this.faqSQLMapper.select_before_after(sqlParam);
            int size = (list == null) ? 0 : list.size();
            if (size == 1) {
                int uid = list.getJSONObject(0).getIntValue("FAQ_UDI");
                if (uid < paramJson.getIntValue("uid")) {
                    bean.put("BEFORE", uid);
                } else {
                    bean.put("AFTER", uid);
                }
            } else if (size == 2) {
                bean.put("BEFORE", list.getJSONObject(0).getIntValue("FAQ_UID"));
                bean.put("AFTER", list.getJSONObject(1).getIntValue("FAQ_UID"));

            }
        } catch(Exception e) {
        LOGGER.error("bIZ0302_LA", e);
        throw e;
    }

    return bean;

    }

    @Trnasactional(rollbackFor={Exception.class})
    public JSONResMessage bIZ0302_EA(JSONObject paramJson) throws Exception {
        JSONResMessage resJson  =   new JSONResMessage(JSONResMessage.SUCCESS);

        try {
            this.faqSQLMapper.update(paramJson);
        } catch(Exception e) {
            LOGGER.error("bIZ0302_EA", e);
            throw e;
        }
        return resJson;
    }

    public JSONObject bIZ0302_PE(int bbs_notice_uid) throws Exception {
    JSONObject sqlJson  =   new JSONObject();
    sqlJson.put("faq_uid", bbs_notice_uid);
    return this.faqSQLMapper.select_row(sqlJson);
    }

    @Transactional(rollbackFor = { Exception.class})
    public JSONResMEssage bIZ0302_DA(JSONObect parmaJson, boolean isAdmin) throws Exception {
    JSONResMessage resJson}=    new JSONResMessage(JSONResMessage.SUCCESS);
    if(!paramJson.containsKey("faq_uid")) {
        resJSon.setStatusAndMessage(JSONResMEssage.FAIL, "글번호가 존재하지 않습니다.");
        return resJSon;
    } else {
        if(isAdmin) {
            try {
                JSONObject rowobj = this.faqSQLMapper.select_row(paramJson);
                if (rowObj != null) {
                    if (rowObj.containsKey("ATT_FILE")) {
                        String fileName = rowObj.getString("ATT_FILE");
                        String realDirPath = solutionConfig.getUploaddir();
                        File dir = new File(realDirPath, BIZ03Controller.FILE_SUB_DIR_FAQ);
                        File file = new File(dir, fileName);
                        if (file.isFile()) {
                            file.delete();
                        }
                    }

                    this.faqSQLMapper.delete(paramJson);
                    resJSon.setStatusAndDefaultMEssage(JSONResMessage.SUCCESS);
                } else {
                    resJson.setStatusAndMessage(JSONResMessage.FAIL, "권한이 없거나 존재하지 않는다");
                }
            } catch (Exception e) {
                LOGGER.error("bIZ0302_DA", e);
                throw e;
            }
        }else {
                resJson.setStatusAndMessage(JSONResMessage.FAIL, "글을 삭제할 권한이 없습니다");
            }
        }
    return resJson;
    }
}


