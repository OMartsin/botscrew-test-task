package dev.martsin.universitytesttask.services;

import dev.martsin.universitytesttask.models.Department;
import dev.martsin.universitytesttask.models.Lector;
import dev.martsin.universitytesttask.repositories.DepartmentRepository;
import dev.martsin.universitytesttask.repositories.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public String getHeadOfDepartment(String departmentName) {
        Optional<Department> department = departmentRepository.findByName(departmentName);
        return department.map(value -> value.getHeadOfDepartment() != null ?
                "Head of " + departmentName + " department is " + value.getHeadOfDepartment().getName() :
                "This department doesn't have a head").orElse("Department not found");
    }

    public String getDepartmentStatistics(String departmentName) {
        List<Lector> lectors = lectorRepository.findByDepartmentsName(departmentName);
        if (lectors.isEmpty()) {
            return "Department not found or has no employees";
        }
        Map<String, Long> statistics = lectors.stream()
                .collect(Collectors.groupingBy(lector -> lector.getDegree().getName(), Collectors.counting()));

        return statistics.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public String getAverageSalary(String departmentName) {
        List<Lector> lectors = lectorRepository.findByDepartmentsName(departmentName);
        if (lectors.isEmpty()) {
            return "Department not found or has no employees";
        }
        double averageSalary = lectors.stream().mapToInt(Lector::getSalary).average().orElse(0.0);
        return "The average salary of " + departmentName + " is " + averageSalary;
    }

    public String getCountOfEmployee(String departmentName) {
        long count = lectorRepository.countByDepartmentsName(departmentName);
        if (count == 0) {
            return "Department not found or has no employees";
        }
        return departmentName + " has " + count + " employees";
    }
}
