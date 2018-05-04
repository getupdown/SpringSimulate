package cn.sxy.SimulateSpring.AOP.AspectJ;

import org.springframework.stereotype.Component;


@Component
public class FuckingImpl implements Fucking {

    public void fuck () {
        System.out.println("fuck");
    }
}
