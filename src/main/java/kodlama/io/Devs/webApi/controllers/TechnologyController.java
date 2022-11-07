package kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Devs.business.abstracts.TechnologyService;
import kodlama.io.Devs.business.request.technology.CreateTechnologyRequest;
import kodlama.io.Devs.business.request.technology.DeleteTechnologyRequest;
import kodlama.io.Devs.business.request.technology.UpdateTechnologyRequest;
import kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import kodlama.io.Devs.business.responses.technology.GetTechnologyByIdResponse;

@RestController
@RequestMapping("/api/technology")
public class TechnologyController {
    private TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService){
        this.technologyService = technologyService;
    }
    
    @PostMapping("/add")
    public void add(CreateTechnologyRequest createTechnologyRequest){
        this.technologyService.add(createTechnologyRequest);
    }
    
    @DeleteMapping("/delete")
    public void delete (DeleteTechnologyRequest deleteTechnologyRequest){
        this.technologyService.delete(deleteTechnologyRequest);
    }
    
    @PutMapping("/update")
    public void update(int id,UpdateTechnologyRequest updateTechnologyRequest){
        this.technologyService.update(id,updateTechnologyRequest);
    }
    
    @GetMapping("/getAll")
    public List<GetAllTechnologyResponse> getAll(){
        return this.technologyService.getAll();
    }
    
    @GetMapping("/getById")
    public GetTechnologyByIdResponse getById(int technologyId){
        return this.technologyService.getById(technologyId);
    }
}