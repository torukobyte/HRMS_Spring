package torukobyte.hrms.api.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import torukobyte.hrms.business.abstracts.CurriculaVitaeService;
import torukobyte.hrms.core.utilities.results.DataResult;
import torukobyte.hrms.core.utilities.results.Result;
import torukobyte.hrms.entities.concretes.CurriculaVitae;
import torukobyte.hrms.entities.dtos.addDtos.CurriculaVitaeAddDto;
import torukobyte.hrms.entities.dtos.updateDtos.CurriculaVitaeUpdateDto;

import java.io.IOException;

@RestController
@RequestMapping("/api/cvs")
@CrossOrigin
public class CurriculaVitaesController {

    private final CurriculaVitaeService curriculaVitaeService;

    public CurriculaVitaesController(CurriculaVitaeService curriculaVitaeService) {
        this.curriculaVitaeService = curriculaVitaeService;
    }

//    @GetMapping("/getJobseekersWithCv")
//    public DataResult<List<JobseekerWithCurriculaVitaeDto>> getJobseekerWithCv(@RequestParam int id) {
//        return this.curriculaVitaeService.getJobSeekerWithCurriculaVitae(id);
//    }

    @GetMapping("/getCvWithJobSeekerId")
    public DataResult<CurriculaVitae> findCvByJobSeekerId(int jobseekerId) {
        return this.curriculaVitaeService.findByJobSeekerId(jobseekerId);
    }

    @PostMapping("/addCv")
    public Result addCv(@RequestBody CurriculaVitaeAddDto curriculaVitae) {
        return this.curriculaVitaeService.addCv(curriculaVitae);
    }

    @PutMapping("/updateCv")
    public Result updateCv(@RequestBody CurriculaVitaeUpdateDto curriculaVitae) {
        return this.curriculaVitaeService.updateCv(curriculaVitae);
    }

    @PostMapping("/addPicture")
    public Result addPicture(int cvId, MultipartFile file) throws IOException {
        return this.curriculaVitaeService.uploadPicture(cvId, file);
    }
}