package co.wordbe.springsecurity.aopsecurity;

import org.springframework.stereotype.Service;

@Service
public class AopPointcutService {

    public void pointcutSecured() {
        System.out.println("pointcut secured");
    }

    public void notSecured() {
        System.out.println("not secured");
    }
}
