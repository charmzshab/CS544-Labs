package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

//AOP
//Configuration
@Aspect
@Configuration
public class AfterAopAspect {


    // Pointcut expression for sendEmail(String, String)
//    @Pointcut("execution(* customers.EmailSender.sendEmail(..))")
//    public void sendEmailMethod() {}

    @After("execution(* customers.EmailSender.sendEmail(..)) && args(email,message)")
    public void afterSendEmail(JoinPoint joinPoint, String email, String message) {
        EmailSender emailSender = (EmailSender) joinPoint.getTarget();
        System.out.println(LocalDateTime.now() + " " + "method=" + joinPoint.getSignature().getName());
        System.out.println("address=" + email + " message=" + message + " " + "outgoing mail server =" + emailSender.getOutgoingMailServer());
    }

    @Around("execution(* customers.CustomerDAO.save(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();

        // print the time to the console
        System.out.println("duration of method calls=" + totaltime+"ms");
         return retVal;
    }
}