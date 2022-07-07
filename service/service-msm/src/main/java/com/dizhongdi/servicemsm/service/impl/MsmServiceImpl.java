package com.dizhongdi.servicemsm.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.dizhongdi.servicemsm.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * ClassName:MsmServiceImpl
 * Package:com.dizhongdi.servicemsm.service.impl
 * Description:
 *
 * @Date: 2022/7/7 23:35
 * @Author:dizhongdi
 */
@Service
public class MsmServiceImpl implements MsmService {
        //容联云发送手机验证码
        @Override
        public boolean send(String phone, String templateCode,String code) {
            //生产环境请求地址：app.cloopen.com
            String serverIp = "app.cloopen.com";
            //请求端口
            String serverPort = "8883";
            //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
            String accountSId = "8aaf07087a331dc7017b0aaa2bde4088";
            String accountToken = "70955fe6dc7542d789e5e6cd082c8a29";
            //请使用管理控制台中已创建应用的APPID
            String appId = "8aaf070881ad8ad40181d9483d3907ea";
            CCPRestSmsSDK sdk = new CCPRestSmsSDK();
            sdk.init(serverIp, serverPort);
            sdk.setAccount(accountSId, accountToken);
            sdk.setAppId(appId);
            sdk.setBodyType(BodyType.Type_JSON);
            //发送短信至手机号
            String to = phone;
            //短信模板
            String templateId= templateCode;
            //验证码，分钟内到期

            String[] datas = {code,"5"};
            HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
            if("000000".equals(result.get("statusCode"))){
                //正常返回输出data包体信息（map）
                return true;

//            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for(String key:keySet){
//                Object object = data.get(key);
//                System.out.println(key +" = "+object);
//            }
            }else{
                //异常返回输出错误码和错误信息
                System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
                return true;
            }
        }

    @Override
    public String getCode() {
        int random = (int) (Math.random() * 1000000);
        System.out.println(random);
        String code = String.format("%06d", random);
        System.out.println(code);
        return code;
    }
}
