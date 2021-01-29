package com.yuan.bbs.aspect;

import com.yuan.bbs.common.annotation.LogRecord;
import com.yuan.bbs.common.enums.OptType;
import com.yuan.bbs.entity.Log;
import com.yuan.bbs.service.LogService;
import com.yuan.bbs.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户操作日志记录切面
 * 主要记录：ip，uid，操作类型等等
 */
@Slf4j
@Aspect
@Component
public class LogRecordAspect {

    @Autowired
    LogService logService;

    @AfterReturning("@annotation(logRecord)")
    public void after(LogRecord logRecord) {
        OptType optType = logRecord.type();
        Log recordLog = new Log();

        if (optType.getType() > -1 || "".equals(logRecord.value())) {
            recordLog.setType(optType.getType());
            recordLog.setState(optType.getDesc());
        } else {
            recordLog.setType(-1);
            recordLog.setState(logRecord.value());
        }

        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            recordLog.setRequestIp(request.getRemoteAddr());
            Integer id = SecurityUtil.getUserFromSecurity().getId();
            recordLog.setUid(id);
            logService.save(recordLog);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
