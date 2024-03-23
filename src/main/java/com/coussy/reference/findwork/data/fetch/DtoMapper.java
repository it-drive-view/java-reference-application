package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import com.coussy.reference.findwork.data.fetch.http.JobsFindWorkSkillDatabase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// pourquoi est ce que je l'appelle Component ?
@Component
public class DtoMapper {

    public List<JobsFindWorkDatabase> toJobsFindWorks(List<ResultDto> results) {

        List<JobsFindWorkDatabase> list = new ArrayList<>();
        JobsFindWorkDatabase jfw;
            List<JobsFindWorkSkillDatabase> skills;
        for (ResultDto dto : results) {


            jfw = new JobsFindWorkDatabase();
            jfw.setId(dto.id());
            jfw.setDatePosted(dto.date_posted());
            List<String> keywords = dto.keywords();

            skills = new ArrayList<>();
            jfw.setJobsFindWorkSkillsDatabase(skills);
            for (String skill                    : dto.keywords()) {
                // TODO est ce que je peux vider la liste ici ?
                    JobsFindWorkSkillDatabase ss = new JobsFindWorkSkillDatabase();
                   ss.setSkill(skill);
                   skills.add(ss);
            }
            jfw.setJobsFindWorkSkillsDatabase(skills);
            list.add(jfw);

        }
        return list;
    }

    public JobsFindWorkDatabase toJobsFindWorks(ResultDto dto) {

        List<JobsFindWorkSkillDatabase> skills;
        JobsFindWorkDatabase jfw = new JobsFindWorkDatabase();
            jfw.setId(dto.id());
            jfw.setDatePosted(dto.date_posted());

            skills = new ArrayList<>();
            jfw.setJobsFindWorkSkillsDatabase(skills);
            for (String skill                    : dto.keywords()) {
                // TODO est ce que je peux vider la liste ici ?
                JobsFindWorkSkillDatabase ss = new JobsFindWorkSkillDatabase();
                ss.setSkill(skill);
                skills.add(ss);
            }
            jfw.setJobsFindWorkSkillsDatabase(skills);
        return jfw;
    }

}
