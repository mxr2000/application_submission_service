package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Company;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
public class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void testInsertCompany_Success() {
        companyMapper.insertCompany("Innocent", "");
        assertEquals(new Company("Innocent", ""), companyMapper.queryCompanyByName("Innocent"));
    }

    @Test
    public void testQueryCompanyByName_NotFound() {
        assertNull(companyMapper.queryCompanyByName("Dick"));
    }

    @Test
    public void testQueryCompanyByName_Found() {
        assertEquals(new Company("TikTok", ""), companyMapper.queryCompanyByName("TikTok"));
    }
}
