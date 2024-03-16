package dev.martsin.universitytesttask.services;

import dev.martsin.universitytesttask.models.Lector;
import dev.martsin.universitytesttask.repositories.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    public String globalSearch(String template) {
        List<Lector> lectors = lectorRepository.findByNameContaining(template);
        return lectors.stream()
                .map(Lector::getName)
                .collect(Collectors.joining(", "));
    }
}
