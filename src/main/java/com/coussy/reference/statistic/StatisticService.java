package com.coussy.reference.statistic;

import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    private final DataProviderHttpClient dataProviderHttpClient;
    private final SkillDatabaseRepository skillDatabaseRepository;

    public StatisticService(DataProviderHttpClient dataProviderHttpClient, SkillDatabaseRepository skillDatabaseRepository) {
        this.dataProviderHttpClient = dataProviderHttpClient;
        this.skillDatabaseRepository = skillDatabaseRepository;
    }

    public StatisticDto computeStatistic() {
        String token = dataProviderHttpClient.getToken();
        return new StatisticDto(token, new BigDecimal(2));
    }

    public List<Map.Entry<String, Long>> computeSkills() {

        // TODO voir sur le net comment ils font pour faire une comparaison descendante
        Comparator<Map.Entry<String, Long>> comparator = (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue());

        List<SkillDatabase> skills = skillDatabaseRepository.findAll();
        Map<String, Long> map = skills.stream()
                .map(SkillDatabase::getSkill)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Map.Entry<String, Long>> collect = map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return collect;
    }

}
