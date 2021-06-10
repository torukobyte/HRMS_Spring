package torukobyte.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import torukobyte.hrms.business.abstracts.JobExperienceService;
import torukobyte.hrms.core.utilities.results.*;
import torukobyte.hrms.dataAccess.abstracts.JobExperienceDao;
import torukobyte.hrms.entities.concretes.JobExperience;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
        this.jobExperienceDao = jobExperienceDao;
    }

    @Override
    public Result addJobExperience(JobExperience jobExperience) {
        this.jobExperienceDao.save(jobExperience);
        return new SuccessResult("Success: Kariyer bilgisi başarıyla sisteme eklendi!");
    }

    @Override
    public DataResult<List<JobExperience>> findAllSorted(int id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "endDate");
        if (this.jobExperienceDao.getJobExperienceEndDateByCurriculaVitaeId(id, sort).isEmpty()) {
            return new WarningDataResult<>("Warning: Listelenecek iş tecrübesi bulunamadı!");
        } else {
            return new SuccessDataResult<>(
                    this.jobExperienceDao.getJobExperienceEndDateByCurriculaVitaeId(id, sort),
                    "Success: İş tecrübeleri başarıyla sıralanıp listelendi!");
        }
    }
}