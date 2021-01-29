package com.yuan.bbs.service;

import com.yuan.bbs.common.pojo.ApiTranslationDto;
import com.yuan.bbs.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiService {

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    @Value("${baidu.appid}")
    private String appid;
    @Value("${baidu.securityKey}")
    private String securityKey;
    @Autowired
    RestTemplate restTemplate;

    // 调用百度翻译api获取翻译结果
    public ApiTranslationDto getTransResult(String query) {
        return getTransResult(query, "auto", "auto");
    }

    public ApiTranslationDto getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.setAll(params);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TRANS_API_HOST);
        URI uri = builder.queryParams(requestParams).build().encode().toUri();  //  这里不能进行 encode 了，编码就错误了
        return restTemplate.getForObject(uri, ApiTranslationDto.class);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appid);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", CommonUtil.md5(src));

        return params;
    }
}
