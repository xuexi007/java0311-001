package com.offcn.util;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
@Component
public class SmsListener implements MessageListener {
    @Autowired
    private SmsUtil smsUtil;

    @Override
    public void onMessage(Message message) {
        //使用map来封装消息
        if(message instanceof MapMessage){
            MapMessage mapMessage=(MapMessage)message;
            try {
              String mobile=  mapMessage.getString("mobile");
                System.out.println("mobile:"+mobile);
            String template_code=  mapMessage.getString("template_code")  ;
                System.out.println("template_code:"+template_code);
            String sign_name=    mapMessage.getString("sign_name");
                System.out.println("sign_name:"+sign_name);

              String parm=  mapMessage.getString("parm");
                System.out.println("parm:"+parm);

                //调用短信发送工具类，发送短信
                smsUtil.sendSms(mobile,template_code,sign_name,parm);
            } catch (JMSException e) {
                e.printStackTrace();
            }catch (ClientException e){
                e.printStackTrace();
            }
        }
    }
}
