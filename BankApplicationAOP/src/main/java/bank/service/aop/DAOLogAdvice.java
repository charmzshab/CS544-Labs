package bank.service.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DAOLogAdvice {
    @Autowired
    private ILogger logger;

    @After("execution(* bank.dao.*.*(..))")
    public void logDAOCall(JoinPoint joinPoint) {
        logger.log("method= "+joinPoint.getSignature().getName());
    }

}
