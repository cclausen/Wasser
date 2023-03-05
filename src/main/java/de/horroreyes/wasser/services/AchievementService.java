package de.horroreyes.wasser.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.horroreyes.wasser.model.Achievement;
import de.horroreyes.wasser.repositories.AchievementRepository;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public Achievement save(Achievement newAchievement) {
        return achievementRepository.save(newAchievement);
    }

    public void delete(long achievementId) {
        achievementRepository.deleteById(achievementId);
    }

    public Optional<Achievement> get(long achievementId) {
        return achievementRepository.findById(achievementId);
    }

    public List<Achievement> getAll() {
        return achievementRepository.findAll();
    }

    public List<Achievement> getAllByPersonId(long personId) {
        return achievementRepository.findAllByPersonId(personId);
    }
}
