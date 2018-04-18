package cn.sxy.SimulateSpring.Sample;

import cn.sxy.SimulateSpring.AOP.Annotation.NeedAdvice;
import cn.sxy.SimulateSpring.IOC.Annotation.Service;
import cn.sxy.SimulateSpring.IOC.BeanManager.BeanManager;

@Service
public class UserService {

    @NeedAdvice
    public void speak() {
        System.out.println("I am speaking!");
    }

    public void singing() {
        System.out.println("I am singing");
    }

    public static void main(String[] args) {

        BeanManager.constructBean("cn/sxy/SimulateSpring/Sample");

        UserService userService = (UserService) BeanManager.getBean(UserService.class);

        userService.singing();

        userService.speak();
    }
}
