import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Group group = WorkWithDatabase.databaseConnection();
        int selectMenuPath = 0;
        while (selectMenuPath != 4) {
            System.out.println("To add a student to the academic group press -1");
            System.out.println("Sorting Group press-2");
            System.out.println("List of students of draft age-3");
            System.out.println("Press to exit the menu-4");
            Scanner sc = new Scanner(System.in);
            selectMenuPath = sc.nextInt();
            if (selectMenuPath == 1) {
                Student newStudent = new Student();

                System.out.println("Enter student's last name");

                Scanner scanner = new Scanner(System.in);
                String surname = scanner.next();

                Pattern pattern = Pattern.compile("([A-Za-z]{2,20})");
                Matcher matcher = pattern.matcher(surname);
                boolean foundSurname = matcher.matches();
                try {
                    if (!foundSurname) {
                        System.out.println("Не найдено Surname");
                        throw new SurnameException("The last name is incorrect, restart the program and try again");
                    }
                    surname = surname.toLowerCase();
                    newStudent.setSurname(surname.substring(0, 1).toUpperCase() + surname.substring(1));

                    System.out.println("Enter student's name");
                    String name = scanner.next();
                    Matcher matcherName = pattern.matcher(name);
                    boolean foundName = matcherName.matches();
                    if (!foundName) {
                        System.out.println("Не найдено Name");
                        throw new SurnameException("The last name is incorrect, restart the program and try again");
                    }
                    name = name.toLowerCase();
                    newStudent.setName(name.substring(0, 1).toUpperCase() + name.substring(1));

                    System.out.println("Enter student's age");
                    int age = scanner.nextInt();
                    newStudent.setAge(age);

                    System.out.println("Enter student's academic performance");
                    int academicPerformance = scanner.nextInt();
                    newStudent.setAcademicPerformance(academicPerformance);

                    System.out.println("Enter student's academic scholarship");
                    int scholarship = scanner.nextInt();
                    newStudent.setScholarship(scholarship);

                    System.out.println("Enter student's gender");
                    String gender = scanner.next();
                    newStudent.setGender(gender);

                    WorkWithDatabase.studentRegistration(newStudent);
                } catch (InputMismatchException | SurnameException e) {
                    e.printStackTrace();
                    System.out.println("Data entered incorrectly, please try again");

                } catch (MyException e) {
                    e.printStackTrace();
                }

            }
            if (selectMenuPath == 2) {
                System.out.println("sort by last name -1");
                System.out.println("sort by  name -2");
                System.out.println("sort by age -3");
                System.out.println("sort by academicPerformance -4");
                System.out.println("sort by scholarship -5");
                Scanner scanner = new Scanner(System.in);
                int selectMenuSortingGroup = sc.nextInt();
                if (selectMenuSortingGroup == 1) {
                    group = SortingGroup.sortSurname(group);
                    for (Student student : group.getGroup()) {
                        if (student != null) {
                            System.out.println(student.toString());
                        }
                    }
                }

                if (selectMenuSortingGroup == 2) {
                    group = SortingGroup.sortName(group);
                    for (Student student : group.getGroup()) {
                        if (student != null) {
                            System.out.println(student.toString());
                        }
                    }
                }

                if (selectMenuSortingGroup == 3) {
                    group = SortingGroup.sortAge(group);
                    for (Student student : group.getGroup()) {
                        if (student != null) {
                            System.out.println(student.toString());
                        }
                    }
                }

                if (selectMenuSortingGroup == 4) {
                    group = SortingGroup.sortAcademicPerformance(group);
                    for (Student student : group.getGroup()) {
                        if (student != null) {
                            System.out.println(student.toString());
                        }
                    }
                }

                if (selectMenuSortingGroup == 5) {
                    group = SortingGroup.sortScholarship(group);
                    for (Student student : group.getGroup()) {
                        if (student != null) {
                            System.out.println(student.toString());
                        }
                    }
                }

            }
            if (selectMenuPath == 3) {
                List <Student>potentialSoldiers= group.potentialSoldiers();
                for (Student student : potentialSoldiers) {
                    if (student != null) {
                        System.out.println(student.toString());
                    }
                }
            }
        }
    }
}
