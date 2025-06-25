package chainofresponsibility;

public class Database {
    public boolean areCredentialsValid(String userName, String password) {
        if ("Peter".equals(userName) && "12345".equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getCourse(int id) throws IllegalArgumentException {
        return switch (id) {
            case 1 -> "Objektovo orientovane programovanie";
            case 2 -> "Anglictine";
            case 3 -> "Matematika";
            default -> throw new IllegalArgumentException();
        };
    }

    public String getDepartment(int id) throws IllegalArgumentException {
        return switch (id) {
            case 1 -> "Ustav informatiky a matematiky";
            case 2 -> "Ustav robotiky a kybernetiky";
            default -> throw new IllegalArgumentException();
        };
    }
}
