package com.zcit.common.log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class billAspect {

    @Pointcut("execution(* com.zcit.bill.bill..*.*(..))")
    public void billLog(){
        System.out.println("============================middle");
    }

    @Before("billLog()")
    public void adviceBeforeBill(){
        System.out.println("============================before");
    }

    @After("billLog()")
    public void adviceAfterBill(){
        System.out.println("============================after");
    }

}
