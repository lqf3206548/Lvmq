import Test.Clinet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LvmqMain {
    public static void main(String[] args) {
        //启动server
        new Server().start();
        new Clinet().start();
        //启动Dispatch


    }

}
