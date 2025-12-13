package security;

import java.security.Provider;
import java.security.Security;

public class Providers {

    static void main() {
        for (Provider provider : Security.getProviders()) {
            System.out.println(provider.getVersionStr() + '\t' + provider.getName() + '\t' + provider.getInfo());
        }
    }
}
