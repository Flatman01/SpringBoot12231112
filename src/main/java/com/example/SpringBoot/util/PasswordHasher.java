import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminPassword = "admin123";
        String userPassword = "user123";

        String newAdminHash = encoder.encode(adminPassword);
        String newUserHash = encoder.encode(userPassword);

        System.out.println("New hash for admin123: " + newAdminHash);
        System.out.println("New hash for user123: " + newUserHash);

    }
}