import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\b((\\d\\d?|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d\\d?|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher matcher = pattern.matcher(input);

        boolean found = false;
        while (matcher.find()) {
            String validIPv4 = matcher.group();
            System.out.println("Верный IPv4: " + validIPv4);
            found = true;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ipv4_addresses.txt", true))) {
                writer.write(validIPv4 + "\n");
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }

        if (!found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ipv4_addresses.txt", true))) {
                writer.write("В строке нет верного IPv4 адреса\n");
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }
    }
}