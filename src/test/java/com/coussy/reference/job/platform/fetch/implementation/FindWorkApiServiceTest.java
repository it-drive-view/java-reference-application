package com.coussy.reference.job.platform.fetch.implementation;

import com.coussy.reference.job.platform.fetch.JobPositionDatabaseRepository;
import com.coussy.reference.job.platform.fetch.SkillDatabaseRepository;
import com.coussy.reference.job.platform.fetch.dto.ParentDto;
import com.coussy.reference.job.platform.fetch.dto.ResultDto;
import com.coussy.reference.job.platform.fetch.http.FindworkHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FindWorkApiServiceTest {

    FindWorkApiService underTest;

    @Mock
    FindworkHttpClient findworkHttpClient;

    @Mock
    JobPositionDatabaseRepository jobPositionDatabaseRepository;

    @Mock
    SkillDatabaseRepository skillDatabaseRepository;

    @BeforeEach
    public void init() {
        underTest = new FindWorkApiService(findworkHttpClient , jobPositionDatabaseRepository, skillDatabaseRepository);
    }

    @Test
    public void m() {

//        Mockito.doReturn()

        List<ResultDto> resultDtos = new ArrayList<>();
        ParentDto value = new ParentDto(4, "next", resultDtos);
        given(findworkHttpClient.getJobs("")).willReturn(value);

        ParentDto jobs = findworkHttpClient.getJobs("");

        Assertions.assertNotNull(underTest);
    }

}
