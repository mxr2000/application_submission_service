package edu.virginia.cs.application_submission.service;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.UpdateNoEffectException;
import edu.virginia.cs.application_submission.mapper.UpdateMapper;
import edu.virginia.cs.application_submission.pojo.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UpdateServiceImplTest {

    @Autowired
    private UpdateService updateService;

    @MockBean
    private UpdateMapper updateMapper;

    private LocalDate submissionDate;

    @BeforeEach
    public void setUp() {
        submissionDate = LocalDate.parse("2022-08-05");
    }

    @Test
    public void testCreateUpdate_ApplicationDoesNotExist() {
        doThrow(DataIntegrityViolationException.class).when(updateMapper).insertUpdate(anyInt(), any());
        assertThrows(ApplicationDoesNotExistException.class, () -> updateService.createUpdate(1, new Update()));
    }

    @Test
    public void testCreateUpdate_ApplicationExists() {
        Update update = new Update();
        updateService.createUpdate(1, update);
        verify(updateMapper).insertUpdate(1, update);
    }

    @Test
    public void testUpdateUpdateCompleteTime_UpdateDoesNotExist() {
        when(updateMapper.updateCompleteTime(anyInt(), any())).thenReturn(0);
        assertThrows(UpdateNoEffectException.class, () -> updateService.updateUpdateCompleteTime(1, submissionDate));
    }
}
