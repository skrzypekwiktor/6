import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongDate extends Exception { }

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int ex = menu();
                switch (ex) {
                    case 1: 
                        exercise1(); 
                        break;
                    case 2: 
                        exercise2(); 
                        break;
                    case 3: 
                        exercise3(); 
                        break;
                    case 0: 
                        System.out.println("Koniec programu.");
                        return;  
                    default: 
                        System.out.println("Niepoprawny wybór, spróbuj ponownie.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Wystąpił błąd wejścia/wyjścia.");
            } catch (WrongStudentName e) {
                System.out.println("Błędne imię studenta! ");
            } catch (WrongAge e) {
                System.out.println("Błędny wiek studenta! Musi być w przedziale 0–100.");
            } catch (WrongDate e) {
                System.out.println("Błędna data! Musi być w formacie DD-MM-RRRR.");
            } catch (InputMismatchException e) {
                System.out.println("Błąd: Należy podać liczbę!");
                scan.nextLine(); 
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");

        return ReadMenu(); 
    }

    public static int ReadMenu() {
        while (true) {
            try {
                int choice = scan.nextInt();
                scan.nextLine(); 
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Błąd: Należy podać liczbę!");
                scan.nextLine(); 
            }
        }
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine(); 
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if (name.contains(" ")) {  
            throw new WrongStudentName();
        }
        return name;
    }

    public static int ReadAge() throws WrongAge {
        while (true) {
            System.out.println("Podaj wiek: ");
            try {
                int age = scan.nextInt();
                if (age < 0 || age > 100) { 
                    throw new WrongAge();
                }
                scan.nextLine(); 
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Błąd: Wiek musi być liczbą!");
                scan.nextLine(); 
            }
        }
    }

    public static String ReadDate() throws WrongDate {
        scan.nextLine(); 
        while (true) {
            System.out.println("Podaj datę urodzenia DD-MM-RRRR: ");
            String date = scan.nextLine();
            if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
                System.out.println("Błąd: Data musi być w formacie DD-MM-RRRR.");
                continue;
            }
            return date;
        }
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDate {
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDate();

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
        if (wanted == null) {
            System.out.println("Nie znaleziono...");
        } else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
