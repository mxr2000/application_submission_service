package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Update;
import edu.virginia.cs.application_submission.pojo.UpdateType;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
public class UpdateMapperTest {
    @Autowired
    private UpdateMapper updateMapper;

    @Test
    public void testInsertUpdate_ApplicationDoesNotExist() {
        assertThrows(DataIntegrityViolationException.class, () -> updateMapper.insertUpdate(100, new Update()));
    }

    @Test
    public void testInsertUpdate_Success() {
        Update update = new Update(LocalDate.parse("2000-08-05"), UpdateType.oa, "easy");
        updateMapper.insertUpdate(1, update);
        Update result = updateMapper.queryUpdatesByApplicationId(1).get(1);
        assertEquals(update, result);
    }

    @Test
    public void testUpdateCompleteTime_UpdateDoesNotExist() {
        assertEquals(0, updateMapper.updateCompleteTime(100, LocalDate.parse("2000-08-05")));
    }

    @Test
    public void testQueryUpdatesByApplicationId() {
        List<Update> updates = List.of(
                new Update(1, LocalDate.parse("2022-08-15"), LocalDate.parse("2022-08-20"), UpdateType.oa, "easy"));
        assertEquals(updates, updateMapper.queryUpdatesByApplicationId(1));
    }


}
