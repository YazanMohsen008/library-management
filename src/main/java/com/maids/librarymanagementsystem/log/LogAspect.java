package com.maids.librarymanagementsystem.log;

import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import com.maids.librarymanagementsystem.dao.LogDao;
import com.maids.librarymanagementsystem.domain.ApplicationUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private final LogDao logDao;

    public LogAspect(LogDao logDao) {
        this.logDao = logDao;
    }


    @AfterReturning(value = ("@annotation(com.maids.librarymanagementsystem.log.Log)"), returning = "returnValue")
    public void log(JoinPoint joinPoint, Object returnValue) throws Exception {
        ApplicationUser applicationUser = CustomUserDetails.getCurrentInstance() != null ? CustomUserDetails.getCurrentInstance().getApplicationUser() : null;
        String action = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Log.class).actionType().getName();
        com.maids.librarymanagementsystem.domain.Log log = new com.maids.librarymanagementsystem.domain.Log();
        if (returnValue instanceof Loggable) {
            log.setEntityId(((Loggable) returnValue).fetchId());
            log.setEntityName((((Loggable) returnValue).getRelatedEntity()));
            log.setActionType(action);
            log.setApplicationUser(applicationUser);
            logDao.save(log);
        }
    }


}
