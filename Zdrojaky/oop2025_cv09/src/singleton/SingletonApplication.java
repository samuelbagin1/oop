package singleton;

public class SingletonApplication {
    public static void main(String[] args) {
        Configuration configuration = Configuration.getInstance();
        System.out.println("domain:   " + configuration.getWebDomain());
        System.out.println("port:     " + configuration.getWebPort());
        System.out.println("language: " + configuration.getLanguage());

        configuration.setWebDomain("google.com");

        Configuration configuration2 = Configuration.getInstance();
        System.out.println(configuration == configuration2); //true
        System.out.println("domain:   " + configuration2.getWebDomain());
    }
}
