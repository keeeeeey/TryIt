package com.tryIt.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KTE_ChatbotController {

    private static String secretKey = "cVFCVWx5VWtTRW1WdnphQk5jbkVZUVVlV3phYWNuTGc=";
    private static String apiUrl = "https://48177f4276a44b18b155f0a28f99bb59.apigw.ntruss.com/custom/v1/5792/d0abbc19e55bb09869bee5e7a734ee0b719f19d807fd5bba2c1080a5cb95b40a";

    @GetMapping("/chatbot")
    public void chatbot(){
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public List<String> sendMessage(@Payload String chatMessage) throws IOException
    {

        List<String> chatMessage_list = new ArrayList<>();
        URL url = new URL(apiUrl);

        String message =  getReqMessage(chatMessage);
        String encodeBase64String = makeSignature(message, secretKey);

        //api서버 접속 (서버 -> 서버 통신)
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;UTF-8");
        con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        wr.write(message.getBytes("UTF-8"));
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();

        BufferedReader br;

        if(responseCode==200) { // 정상 호출

            //보낸 메시지 받아 jsonString에 저장
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream(), "UTF-8"));
            String decodedString;
            String jsonString = "";
            while ((decodedString = in.readLine()) != null) {
                jsonString = decodedString;
            }

            //받아온 값을 세팅하는 부분
            JSONParser jsonparser = new JSONParser();
            try {
                JSONObject json = (JSONObject)jsonparser.parse(jsonString);
                JSONArray bubbles = (JSONArray)json.get("bubbles");


                JSONObject bubble = (JSONObject)bubbles.get(0);
                String chatType = (String)bubble.get("type");
                JSONObject data = (JSONObject)bubble.get("data");
                if(chatType.equals("text")) {
                    chatMessage = (String)data.get("description");
                    chatMessage_list.add(chatMessage);
                }
                else if(chatType.equals("template")) {
                    JSONObject cover = (JSONObject)data.get("cover");
                    JSONObject data2 = (JSONObject)cover.get("data");
                    chatMessage = (String)data2.get("description");
                    chatMessage_list.add(chatMessage);
                    JSONArray contentTableArray = (JSONArray)data.get("contentTable");


                    for(int j = 0; j<contentTableArray.size();j++) {
                        JSONArray contentTables = (JSONArray)contentTableArray.get(j);
                        for(int i = 0;i<contentTables.size();i++) {
                            JSONObject contentTable = (JSONObject)contentTables.get(i);
                            JSONObject data3 = (JSONObject)contentTable.get("data");

                            String title = (String)data3.get("title");
                            chatMessage_list.add(title);

                        }
                    }

                }
                else {
                    chatMessage = "";
                    chatMessage_list.add(chatMessage);

                }

            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }

            in.close();
        } else {  // 에러 발생
            chatMessage = con.getResponseMessage();
            chatMessage_list.add(chatMessage);

        }
        return chatMessage_list;
    }






    //보낼 메세지를 네이버에서 제공해준 암호화로 변경해주는 메소드
    public static String makeSignature(String message, String secretKey) {

        String encodeBase64String = "";

        try {
            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.encodeBase64String(rawHmac);

            return encodeBase64String;

        } catch (Exception e){
            System.out.println(e);
        }

        return encodeBase64String;

    }


    // 영문 + 숫자를 조합한 키 생성
    public static String generateKey() throws Exception{
        // 16byte 의 랜럼 수치를 저장
        String key = "";
        while(true) {
            byte[] bytes = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(bytes);

            try {
                key = new String(Base64.encodeBase64(bytes, false), "UTF-8").replace("==", "");
            } catch (Exception e) {
                throw new Exception(e);
            }
            if(key.matches("^[a-zA-Z0-9]*$"))
            {
                break;
            }
        }
        return key;
    }

    //보낼 메세지를 네이버 챗봇에 포맷으로 변경해주는 메소드
    public static String getReqMessage(String voiceMessage) {

        String requestBody = "";

        try {

            JSONObject obj = new JSONObject();

            long timestamp = new Date().getTime();

            //System.out.println("##"+timestamp);

            obj.put("version", "v2");
//            obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
            obj.put("userId", generateKey());

            obj.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();

            bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            data_obj.put("description", voiceMessage);

            bubbles_obj.put("type", "text");
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.add(bubbles_obj);

            obj.put("bubbles", bubbles_array);
            obj.put("event", "send");

            requestBody = obj.toString();

        } catch (Exception e){
            System.out.println("## Exception : " + e);
        }

        return requestBody;

    }
}