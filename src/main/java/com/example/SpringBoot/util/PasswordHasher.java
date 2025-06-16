import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminPassword = "admin123";
        String userPassword = "user123";

        // Проверка соответствия старого хэша для admin
        String oldAdminHash = "$2a$10$X1iBq1VLhd3xLTHZtfN1he7A9qW1yo6bWNRQzFcg5xJ7uYCBceQ/u";
        boolean match = encoder.matches(adminPassword, oldAdminHash);
        System.out.println("Password matches old admin hash: " + match);

        // Генерация новых хэшей
        String newAdminHash = encoder.encode(adminPassword);
        String newUserHash = encoder.encode(userPassword);

        System.out.println("New hash for admin123: " + newAdminHash);
        System.out.println("New hash for user123: " + newUserHash);

        // Проверка сгенерированных хэшей
        System.out.println("Verify new admin hash: " + encoder.matches(adminPassword, newAdminHash));
        System.out.println("Verify new user hash: " + encoder.matches(userPassword, newUserHash));
    }
}