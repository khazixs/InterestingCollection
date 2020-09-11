package com.ic.learn.service.design.removeIfElse;

import org.springframework.beans.factory.InitializingBean;

/*策略接口*/
public abstract class Handler {
    public void AAA(String name){
        throw new UnsupportedOperationException();
    }

    public void BBB(String name){
        throw new UnsupportedOperationException();
    }
}
