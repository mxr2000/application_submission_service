package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    int insertCompany(String name, String spec);
    Company queryCompanyByName(String name);
}
