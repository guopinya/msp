package com.umeng.push;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 推送演示
 *
 * @author zhuyifa
 * @since 2019-10-10
 */
public class PushDemo {

    private static final PushClient CLIENT = new PushClient();
    private static final Integer TEST = 1;

    public static void main(String[] args) throws Exception {
        switch (TEST) {
            case 1:
                sendAndroidUnicast();
                break;
            case 2:
                sendAndroidBroadcast();
                break;
            case 3:
                sendAndroidGroupcast();
                break;
            case 4:
                sendAndroidFilecast();
                break;
            case 5:
                sendAndroidCustomizedcast();
                break;
            case 6:
                sendAndroidCustomizedcastFile();
                break;
            case 7:
                sendIOSUnicast();
                break;
            case 8:
                sendIOSBroadcast();
                break;
            case 9:
                sendIOSGroupcast();
                break;
            case 10:
                sendIOSFilecast();
                break;
            case 11:
                sendIOSCustomizedcast();
                break;
            default:
        }
    }

    private static void sendAndroidUnicast() throws Exception {
        Message message = new AndroidMessage();
        message.setPredefinedField("type", "unicast");
        message.setPredefinedField("device_tokens", "Aukor2jbkpJgHictmyxqusjTMXbeiIU99-TQwmg2Qwg_");
        message.setPredefinedField("ticker", "Android unicast ticker");
        message.setPredefinedField("title", "中文的title");
        message.setPredefinedField("text", "Android unicast text");
        message.setPredefinedField("after_open", "go_app");
        message.setPredefinedField("display_type", "notification");
        // For how to register a test device, please see the developer doc.
        message.setPredefinedField("production_mode", "true");
        // Set customized fields
        message.setCustomizedField("test", "helloworld");

        CLIENT.send(message);
    }

    private static void sendAndroidBroadcast() throws Exception {
        Message message = new AndroidMessage();
        message.setPredefinedField("type", "broadcast");
        message.setPredefinedField("ticker", "Android broadcast ticker");
        message.setPredefinedField("title", "中文的title");
        message.setPredefinedField("text", "Android broadcast text");
        message.setPredefinedField("after_open", "go_app");
        message.setPredefinedField("display_type", "notification");
        // For how to register a test device, please see the developer doc.
        message.setPredefinedField("production_mode", "true");
        // Set customized fields
        message.setCustomizedField("test", "helloworld");

        CLIENT.send(message);
    }

    private static void sendAndroidGroupcast() throws Exception {
        /*
         *  Construct the filter condition:
         *  "where":
         *	{
         *		"and":
         *		[
         *			{"tag":"test"},
         *			{"tag":"Test"}
         *		]
         *	}
         */
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        JSONObject testTag = new JSONObject();
        JSONObject TestTag = new JSONObject();
        testTag.put("tag", "test");
        TestTag.put("tag", "Test");
        tagArray.add(testTag);
        tagArray.add(TestTag);
        whereJson.put("and", tagArray);
        filterJson.put("where", whereJson);
        System.out.println(filterJson.toString());

        Message message = new AndroidMessage();
        message.setPredefinedField("type", "broadcast");
        message.setPredefinedField("filter", filterJson);
        message.setPredefinedField("ticker", "Android groupcast ticker");
        message.setPredefinedField("title", "中文的title");
        message.setPredefinedField("text", "Android groupcast text");
        message.setPredefinedField("after_open", "go_app");
        message.setPredefinedField("display_type", "notification");
        // For how to register a test device, please see the developer doc.
        message.setPredefinedField("production_mode", "true");

        CLIENT.send(message);
    }

    private static void sendAndroidFilecast() throws Exception {
        Message message = new AndroidMessage();
        String fileId = CLIENT.upload(AndroidMessage.APP_KEY, AndroidMessage.APP_SECRET, "aa" + "\n" + "bb");
        message.setPredefinedField("type", "filecast");
        message.setPredefinedField("file_id", fileId);
        message.setPredefinedField("ticker", "Android filecast ticker");
        message.setPredefinedField("title", "中文的title");
        message.setPredefinedField("text", "Android filecast text");
        message.setPredefinedField("after_open", "go_app");
        message.setPredefinedField("display_type", "notification");

        CLIENT.send(message);
    }

    private static void sendAndroidCustomizedcast() throws Exception {
        Message message = new AndroidMessage();
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        message.setPredefinedField("type", "customizedcast");
        message.setPredefinedField("alias_type", "aliasType");
        message.setPredefinedField("alias", "alias");
        message.setPredefinedField("ticker", "Android customizedcast ticker");
        message.setPredefinedField("title", "中文的title");
        message.setPredefinedField("text", "Android customizedcast text");
        message.setPredefinedField("after_open", "go_app");
        message.setPredefinedField("display_type", "notification");
        // For how to register a test device, please see the developer doc.
        message.setPredefinedField("production_mode", "true");

        CLIENT.send(message);
    }

    private static void sendAndroidCustomizedcastFile() throws Exception {
        Message message = new AndroidMessage();
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        String fileId = CLIENT.upload(AndroidMessage.APP_KEY, AndroidMessage.APP_SECRET, "aa" + "\n" + "bb" + "\n" + "alias");
        message.setPredefinedField("type", "customizedcast");
        message.setPredefinedField("file_id", fileId);
        message.setPredefinedField("alias_type", "aliasType");
        message.setPredefinedField("ticker", "Android customizedcastfile ticker");
        message.setPredefinedField("title", "中文的title");
        message.setPredefinedField("text", "Android customizedcastfile text");
        message.setPredefinedField("after_open", "go_app");
        message.setPredefinedField("display_type", "notification");
        // For how to register a test device, please see the developer doc.
        message.setPredefinedField("production_mode", "true");

        CLIENT.send(message);
    }

    private static void sendIOSUnicast() throws Exception {
        Message msg = new IOSMessage();
        msg.setPredefinedField("type", "unicast");
        msg.setPredefinedField("device_tokens", "e6e3574151baad193a74dc3c96c4c3c1873aedf83fb0b8f375b4a883ae3f7c44");
        msg.setPredefinedField("alert", "IOS 单播测试");
        msg.setPredefinedField("badge", 0);
        msg.setPredefinedField("sound", "default");
        msg.setPredefinedField("production_mode", "false");

        msg.setCustomizedField("test", "helloworld");

        CLIENT.send(msg);
    }

    private static void sendIOSBroadcast() throws Exception {
        Message broadcast = new IOSMessage();

        broadcast.setPredefinedField("alert", "IOS 广播测试");
        broadcast.setPredefinedField("badge", 0);
        broadcast.setPredefinedField("sound", "default");
        broadcast.setPredefinedField("production_mode", "false");
        // Set customized fields
        broadcast.setCustomizedField("test", "helloworld");

        CLIENT.send(broadcast);
    }

    private static void sendIOSGroupcast() throws Exception {
        Message groupcast = new IOSMessage();
        /*
         *  Construct the filter condition:
         *  "where":
         *	{
         *		"and":
         *		[
         *			{"tag":"iostest"}
         *		]
         *	}
         */
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        JSONObject testTag = new JSONObject();
        testTag.put("tag", "iostest");
        tagArray.add(testTag);
        whereJson.put("and", tagArray);
        filterJson.put("where", whereJson);
        System.out.println(filterJson.toString());

        // Set filter condition into rootJson
        groupcast.setPredefinedField("filter", filterJson);
        groupcast.setPredefinedField("alert", "IOS 组播测试");
        groupcast.setPredefinedField("badge", 0);
        groupcast.setPredefinedField("sound", "default");
        groupcast.setPredefinedField("production_mode", "false");

        CLIENT.send(groupcast);
    }

    private static void sendIOSFilecast() throws Exception {
        Message filecast = new IOSMessage();
        String fileId = CLIENT.upload(IOSMessage.APP_KEY, IOSMessage.APP_SECRET, "aa" + "\n" + "bb");
        filecast.setPredefinedField("file_id", fileId);
        filecast.setPredefinedField("alert", "IOS 文件播测试");
        filecast.setPredefinedField("badge", 0);
        filecast.setPredefinedField("sound", "default");
        filecast.setPredefinedField("production_mode", "false");

        CLIENT.send(filecast);
    }

    private static void sendIOSCustomizedcast() throws Exception {
        Message customizedcast = new IOSMessage();
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        customizedcast.setPredefinedField("alias", "alias");
        customizedcast.setPredefinedField("alias_type", "aliasType");
        customizedcast.setPredefinedField("alert", "IOS 个性化测试");
        customizedcast.setPredefinedField("badge", 0);
        customizedcast.setPredefinedField("sound", "default");
        customizedcast.setPredefinedField("production_mode", "false");

        CLIENT.send(customizedcast);
    }
}
