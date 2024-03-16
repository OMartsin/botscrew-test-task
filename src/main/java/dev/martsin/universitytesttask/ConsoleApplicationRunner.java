package dev.martsin.universitytesttask;

import dev.martsin.universitytesttask.models.Degree;
import dev.martsin.universitytesttask.models.Department;
import dev.martsin.universitytesttask.models.Lector;
import dev.martsin.universitytesttask.repositories.DegreeRepository;
import dev.martsin.universitytesttask.repositories.DepartmentRepository;
import dev.martsin.universitytesttask.repositories.LectorRepository;
import dev.martsin.universitytesttask.services.DepartmentService;
import dev.martsin.universitytesttask.services.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleApplicationRunner implements CommandLineRunner {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;
    private final DegreeRepository degreeRepository;
    private final DepartmentService departmentService;
    private final LectorService lectorService;
    @Override
    public void run(String... args) throws Exception {
        initializeData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine().trim();

            if (command.startsWith("Who is head of department ")) {
                String departmentName = command.substring("Who is head of department ".length());
                System.out.println(departmentService.getHeadOfDepartment(departmentName));
            } else if (command.startsWith("Show ") && command.contains(" statistics")) {
                String departmentName = command.substring("Show ".length(), command.indexOf(" statistics"));
                System.out.println(departmentService.getDepartmentStatistics(departmentName));
            } else if (command.startsWith("Show the average salary for the department ")) {
                String departmentName = command.substring("Show the average salary for the department ".length());
                System.out.println(departmentService.getAverageSalary(departmentName));
            } else if (command.startsWith("Show count of employee for ")) {
                String departmentName = command.substring("Show count of employee for ".length());
                System.out.println(departmentService.getCountOfEmployee(departmentName));
            } else if (command.startsWith("Global search by ")) {
                String template = command.substring("Global search by ".length());
                System.out.println(lectorService.globalSearch(template));
            }
            else if(command.equals("exit")) {
                break;
            }
            else {
                System.out.println("Unknown command");
            }
        }
    }

    private void initializeData() {
        try {
            Degree assistant = new Degree(null, "Assistant");
            Degree associateProfessor = new Degree(null, "Associate Professor");
            Degree professor = new Degree(null, "Professor");
            degreeRepository.save(assistant);
            degreeRepository.save(associateProfessor);
            degreeRepository.save(professor);

            Department computerScience = new Department(null, "Computer Science", null, new HashSet<>());
            Department mathematics = new Department(null, "Mathematics", null, new HashSet<>());
            departmentRepository.save(computerScience);
            departmentRepository.save(mathematics);

            Lector lector1 = new Lector(null, "John Doe", assistant, 50000, new HashSet<>());
            Lector lector2 = new Lector(null, "Jane Smith", professor, 70000, new HashSet<>());
            Lector lector3 = new Lector(null, "Tom Johnson", associateProfessor, 60000, new HashSet<>());
            Lector lector4 = new Lector(null, "Alice Williams", professor, 80000, new HashSet<>());
            lectorRepository.save(lector1);
            lectorRepository.save(lector2);
            lectorRepository.save(lector3);
            lectorRepository.save(lector4);

            lector1.getDepartments().add(computerScience);
            lector1.getDepartments().add(mathematics);
            lector2.getDepartments().add(mathematics);
            lector3.getDepartments().add(computerScience);
            lector4.getDepartments().add(computerScience);
            lector4.getDepartments().add(mathematics);
            lectorRepository.save(lector1);
            lectorRepository.save(lector2);
            lectorRepository.save(lector3);
            lectorRepository.save(lector4);

            computerScience.setHeadOfDepartment(lector1);
            departmentRepository.save(computerScience);
            departmentRepository.save(mathematics);
        }
        catch (Exception e) {
            System.out.println("Data already initialized");
        }
    }
}
