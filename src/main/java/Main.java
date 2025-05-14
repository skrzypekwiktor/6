import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int ex = menu();
                switch (ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch (IOException e) {
                System.out.println("Wystąpił błąd wejścia/wyjścia.");
            } catch (WrongStudentName e) {
                System.out.println("Błędne imię studenta! Nie może zawierać spacji.");
            } catch (WrongAge e) {
                System.out.println("Błędny wiek studenta! Musi być w przedziale 0–100.");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();  // wyczyść bufor po nextInt()
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if (name.contains(" ")) {
            throw new WrongStudentName();
        }
        return name;
    }

    public static int ReadAge() throws WrongAge {
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if (age < 0 || age > 100) {
            throw new WrongAge();
        }
        return age;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge {
        var name = ReadName();
        var age = ReadAge();
        scan.nextLine(); // czyszczenie bufora
        System.out.println("Podaj datę urodzenia (format dowolny na razie): ");
        var date = scan.nextLine();

        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for (Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if (wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
