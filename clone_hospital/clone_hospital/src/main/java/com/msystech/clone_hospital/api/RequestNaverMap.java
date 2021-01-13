package com.msystech.clone_hospital.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.URLEncoder;

public class RequestNaverMap {
    public static String getAddressMapPosition(String localAddress) throws Exception {
        return getAddressMapPosition(localAddress, "utf-8");
    }

    public static String getAddressMapPosition(String localAddress, String charset) throws Exception {
        String encodeResult = URLEncoder.encode(localAddress, charset); //urlencoder : url을 웹통용 방식으로 변환 https://arabiannight.tistory.com/151

        return get("https://api-maps.cloud.toast.com/maps/v3.0/appkeys/1NMyiMyXXGRhddKU/coordinates?query=" + encodeResult);
    }

    public static void main(String[] args) {
        String jsonTxt 			= "{\"address\":{\"result\":true,\"totalcount\":1,\"admtotalcount\":1,\"admcount\":1,\"res_type\":\"NNYN\",\"adm\":[{\"type\":3,\"posx\":\"127.125125\",\"posy\":\"37.345157\",\"admcode\":\"4113511400\",\"jibun\":\"\",\"address\":\"경기도 성남시 분당구 구미동\",\"roadname\":\"경기도 성남시 분당구 구미로173번길\",\"roadjibun\":\"\",\"accuracy\":0,\"distance\":509723}]},\"header\":{\"isSuccessful\":true,\"resultCode\":0,\"resultMessage\":\"\"}}";
        try {
            //String response 	= RequestNaverMap.getAddressMapPosition("경기도 성남시 분당구 구미로 173번길");
            //System.out.println("RESPONSE:" + response);

            JSONObject json 			= JSONObject.parseObject(jsonTxt);
            JSONObject addressObj 		= json.getJSONObject("address");
            JSONArray list 				= addressObj.getJSONArray("adm");
            int size 					= (list == null)? 0 : list.size();
            JSONObject rowJson 			= null;
            for (int i = 0; i < size;i ++) {
                rowJson 				= list.getJSONObject(i);
                double posx 			= rowJson.getDouble("posx");
                double posy 			= rowJson.getDouble("posy");
                System.out.println("posx:" + posx + ", posy:" + posy);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
