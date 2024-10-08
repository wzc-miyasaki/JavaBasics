package zec.basics.services.impl;

import org.springframework.stereotype.Component;
import zec.basics.services.CalService;

@Component
public class CalServiceImpl implements CalService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }
}
