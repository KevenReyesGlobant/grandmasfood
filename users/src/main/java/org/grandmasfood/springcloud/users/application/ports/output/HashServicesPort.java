package org.grandmasfood.springcloud.users.application.ports.output;

public interface HashServicesPort {

    String hashPassword(String password);

    boolean checkPassword(String password, String hash);

}
