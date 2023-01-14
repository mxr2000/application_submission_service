package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Company;
import edu.virginia.cs.application_submission.pojo.Position;
import edu.virginia.cs.application_submission.pojo.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
public class PositionMapperTest {
    @Autowired
    private PositionMapper positionMapper;

    private Company company;
    private Season season;
    private Position presetPosition;

    @BeforeEach
    public void setUp() {
        company = new Company("TikTok", "");
        season = new Season(1, "2023 Fall Intern");
        presetPosition = new Position(1, company, season, "SDE");;
    }

    @Test
    public void testInsertPosition_SeasonDoesNotExist() {
        Season nonExistentSeason = new Season(100, "");
        assertThrows(DataIntegrityViolationException.class, () -> positionMapper.insertPosition(new Position(company, nonExistentSeason, "")));
    }

    @Test
    public void testInsertPosition_CompanyDoesNotExist() {
        Company nonExistentCompany = new Company("", "");
        assertThrows(DataIntegrityViolationException.class, () -> positionMapper.insertPosition(new Position(nonExistentCompany, season, "")));
    }

    @Test
    public void testInsertPosition_Success() {
        Position position = new Position(company, season, "a");
        assertEquals(1, positionMapper.insertPosition(position));
        assertEquals(position, positionMapper.queryPositionById(position.getId()));
    }

    @Test
    public void testQueryPositionById_Found() {
        assertEquals(presetPosition, positionMapper.queryPositionById(1));
    }

    @Test
    public void testQueryPositionById_NotFound() {
        assertNull(positionMapper.queryPositionById(100));
    }

    @Test
    public void testQueryPositionBySeasonAndNameAndCompanyName_Found() {
        assertEquals(presetPosition, positionMapper.queryPositionBySeasonAndNameAndCompanyName("SDE", "TikTok", 1));

    }

    @Test
    public void testQueryPositionBySeasonAndNameAndCompanyName_NotFound() {
        assertNull(positionMapper.queryPositionBySeasonAndNameAndCompanyName("a", "b", 1));
    }
}
