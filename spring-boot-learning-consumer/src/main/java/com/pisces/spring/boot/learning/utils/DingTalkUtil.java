package com.pisces.spring.boot.learning.utils;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.request.OapiRobotSendRequest.Link;
import com.dingtalk.api.request.OapiRobotSendRequest.Markdown;
import com.dingtalk.api.request.OapiRobotSendRequest.Text;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 11:10 2020/3/4
 * @Modified By:
 */
public class DingTalkUtil {
    private static final Logger log = LoggerFactory.getLogger(DingTalkUtil.class);

    public DingTalkUtil() {
    }

    public static void sendTextMsg(String serverUrl, String txt) {
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        Text text = new Text();
        text.setContent(txt);
        request.setText(text);

        try {
            OapiRobotSendResponse response = (OapiRobotSendResponse) client.execute(request);
            log.info("DingTalk response={}", JSON.toJSONString(response));
        } catch (ApiException var6) {
            log.warn("DingTalk ApiException", var6);
        }

    }

    public static void sendLinkMsg(String serverUrl, String linkUrl, String pickUrl, String title, String text) {
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        Link link = new Link();
        link.setMessageUrl(linkUrl);
        link.setPicUrl(pickUrl);
        link.setTitle(title);
        link.setText(text);
        request.setLink(link);

        try {
            OapiRobotSendResponse response = (OapiRobotSendResponse) client.execute(request);
            log.info("DingTalk response={}", JSON.toJSONString(response));
        } catch (ApiException var9) {
            log.warn("DingTalk ApiException", var9);
        }

    }

    public static void sendMarkdownMsg(String serverUrl, String title, String text) {
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        Markdown markdown = new Markdown();
        markdown.setTitle(title);
        markdown.setText(text);
        request.setMarkdown(markdown);

        try {
            OapiRobotSendResponse response = (OapiRobotSendResponse) client.execute(request);
            log.info("DingTalk response={}", JSON.toJSONString(response));
        } catch (ApiException var7) {
            log.warn("DingTalk ApiException", var7);
        }
    }
}