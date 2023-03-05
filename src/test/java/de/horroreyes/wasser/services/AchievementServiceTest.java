package de.horroreyes.wasser.services;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.horroreyes.wasser.model.Achievement;
import de.horroreyes.wasser.repositories.AchievementRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AchievementServiceTest {

    @Mock
    AchievementRepository achievementRepository;

    @InjectMocks
    AchievementService achievementService;

    @Test
    void testSave() {
        // GIVEN
        when(achievementRepository.save(any(Achievement.class))).thenReturn(new Achievement());
        // WHEN
        Achievement saved = achievementService.save(new Achievement());
        // THEN
        verify(achievementRepository).save(any(Achievement.class));
        assertThat(saved).isNotNull();
    }

    @Test
    void testDelete() {
        // GIVEN
        // WHEN
        achievementService.delete(1L);
        // THEN
        verify(achievementRepository).deleteById(1L);
    }

    @Test
    void testGet() {
        // GIVEN
        when(achievementRepository.findById(1L)).thenReturn(Optional.of(new Achievement()));
        // WHEN
        Optional<Achievement> achievement = achievementService.get(1L);
        // THEN
        verify(achievementRepository).findById(1L);
        assertThat(achievement).isNotNull().isPresent();
    }

    @Test
    void testGetAll() {
        // GIVEN
        // WHEN
        achievementService.getAll();
        // THEN
        verify(achievementRepository).findAll();
    }

    @Test
    void testGetAllByPersonId() {
        // GIVEN
        // WHEN
        achievementService.getAllByPersonId(1L);
        // THEN
        verify(achievementRepository).findAllByPersonId(1L);
    }
}