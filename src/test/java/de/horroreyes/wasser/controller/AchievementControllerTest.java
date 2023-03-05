package de.horroreyes.wasser.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.horroreyes.wasser.model.Achievement;
import de.horroreyes.wasser.services.AchievementService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AchievementControllerTest {
    @Mock
    AchievementService achievementService;

    @InjectMocks
    AchievementController achievementController;

    @Test
    void testGetAll() {
        // GIVEN
        when(achievementService.getAll()).thenReturn(List.of(new Achievement()));
        // WHEN
        List<Achievement> all = achievementController.getAll();
        // THEN
        assertThat(all).isNotNull().isNotEmpty().hasSize(1);
        assertThat(all.get(0)).isNotNull();
    }

    @Test
    void testGet() {
        // GIVEN
        when(achievementService.get(1L)).thenReturn(Optional.of(new Achievement()));
        // WHEN
        Achievement achievement = achievementController.get(1L);
        // THEN
        assertThat(achievement).isNotNull();
    }

    @Test
    void testGetAllByPersonId() {
        // GIVEN
        when(achievementService.getAllByPersonId(1L)).thenReturn(List.of(new Achievement()));
        // WHEN
        List<Achievement> allByPersonId = achievementController.getAllByPersonId(1L);
        // THEN
        assertThat(allByPersonId).isNotNull();
    }

    @Test
    void testSave() {
        // GIVEN
        when(achievementService.save(any())).thenReturn(new Achievement());
        // WHEN
        Achievement achievement = achievementController.save(new Achievement());
        // THEN
        assertThat(achievement).isNotNull();
    }

    @Test
    void testDelete() {
        // GIVEN
        doNothing().when(achievementService).delete(1L);
        // WHEN
        achievementController.delete(1L);
        // THEN
        verify(achievementService).delete(1L);
    }


}