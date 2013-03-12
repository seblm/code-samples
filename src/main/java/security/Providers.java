package security;

import java.security.Provider;
import java.security.Security;

public class Providers {

    public static void main(String[] args) {
        for (Provider provider : Security.getProviders()) {
            System.out.println(provider.getVersion() + '\t' + provider.getName() + '\t' + provider.getInfo());
        }
    }
}
