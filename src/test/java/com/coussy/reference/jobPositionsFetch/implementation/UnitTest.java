package com.coussy.reference.jobPositionsFetch.implementation;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response.UpworkResponse;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.FindworkHttpClient;
import com.coussy.reference.jobPositionsFetch.services.FindWorkApiService;
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
class UnitTest {

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

        List<UpworkResponse.JobPositionResponse> jobPositionResponses = new ArrayList<>();
        UpworkResponse value = new UpworkResponse(4, "next", jobPositionResponses, null);
        given(findworkHttpClient.getJobs("")).willReturn(value);

        UpworkResponse jobs = findworkHttpClient.getJobs("");

        Assertions.assertNotNull(underTest);
    }

}
