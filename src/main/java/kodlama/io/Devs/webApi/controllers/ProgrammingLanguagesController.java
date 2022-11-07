package kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.Devs.business.request.language.CreateLanguageRequest;
import kodlama.io.Devs.business.request.language.DeleteLanguageRequest;
import kodlama.io.Devs.business.request.language.UpdateLanguageRequest;
import kodlama.io.Devs.business.responses.language.GetAllLanguageResponse;
import kodlama.io.Devs.business.responses.language.GetLanguageByIdResponse;

@RestController
@RequestMapping("/api/languages")
public class ProgrammingLanguagesController {

    private ProgrammingLanguageService languageService;
    public ProgrammingLanguagesController(ProgrammingLanguageService languageService){
        this.languageService = languageService;
    }

    @PostMapping("/add")
    public void add(CreateLanguageRequest createLanguageRequest){
        this.languageService.add(createLanguageRequest);
    }
    @DeleteMapping("/delete")
    public void delete(DeleteLanguageRequest deleteLanguageRequest){
        this.languageService.delete(deleteLanguageRequest);
    }
    @PutMapping("/update")
    public void update (int id, UpdateLanguageRequest updateLanguageRequest){
        this.languageService.update(id,updateLanguageRequest);
    }
    @GetMapping("/getAll")
    public List<GetAllLanguageResponse> getAll(){
        return this.languageService.getAll();
    }
    @GetMapping("/getById")
    public GetLanguageByIdResponse getById(int id){
        return this.languageService.getById(id);
    }
}
