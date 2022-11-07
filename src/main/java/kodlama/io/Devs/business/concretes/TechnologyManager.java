package kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.Devs.business.abstracts.TechnologyService;
import kodlama.io.Devs.business.request.technology.CreateTechnologyRequest;
import kodlama.io.Devs.business.request.technology.DeleteTechnologyRequest;
import kodlama.io.Devs.business.request.technology.UpdateTechnologyRequest;
import kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import kodlama.io.Devs.business.responses.technology.GetTechnologyByIdResponse;
import kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.Devs.entities.concretes.ProgrammingLanguage;
import kodlama.io.Devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyRepository technologyRepository;
    private ProgrammingLanguageRepository programmingLanguageRepository;
    public TechnologyManager (TechnologyRepository technologyRepository, ProgrammingLanguageRepository programmingLanguageRepository){
        this.technologyRepository = technologyRepository;
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public void add(CreateTechnologyRequest createTechnologyRequest) {
        Technology technology = new Technology();
        ProgrammingLanguage language = programmingLanguageRepository.findById(createTechnologyRequest.getLanguageId()).get();
        technology.setName(createTechnologyRequest.getName());
        technology.setProgrammingLanguage(language);
        if(!isTechnologyNameExist(technology) && !isTechnologyEmpty(technology))
            this.technologyRepository.save(technology);
        else
            System.out.println("Aynı veri veya boş veri griilemez.");
    }

    @Override
    public void delete(DeleteTechnologyRequest deleteTechnologyRequest) {
        int flag = 0;
        for (Technology technology : technologyRepository.findAll()) {
            if(technology.getId() == deleteTechnologyRequest.getId()){
                flag = 1;
                technologyRepository.deleteById(deleteTechnologyRequest.getId());
                break;
            }
        }
        if(flag == 0)
            System.out.println("veritabanında bu veri bulunamadı.");
    }

    @Override
    public void update(int id, UpdateTechnologyRequest updateTechnologyRequest) {
        if(isTechnologyIdExist(id)){
            Technology technology = technologyRepository.findById(id).get();
            ProgrammingLanguage language = programmingLanguageRepository.findById(updateTechnologyRequest.getLanguageId()).get();
            technology.setProgrammingLanguage(language);
            technology.setName(updateTechnologyRequest.getName());
            this.technologyRepository.save(technology);
        }
        else
            System.out.println("veritabanında bu veri bulunamadı.");
    }

    @Override
    public List<GetAllTechnologyResponse> getAll() {
        List<Technology> technologies = technologyRepository.findAll();
        List<GetAllTechnologyResponse> getAllTechnologyResponses = new ArrayList<>();
        for (Technology technology : technologies) {
            GetAllTechnologyResponse response = new GetAllTechnologyResponse();
            response.setName(technology.getName());
            response.setId(technology.getId());
            response.setLanguagename(technology.getProgrammingLanguage().getName());
            getAllTechnologyResponses.add(response);
        }
        return getAllTechnologyResponses;
    }

    @Override
    public GetTechnologyByIdResponse getById(int technologyId) {
        GetTechnologyByIdResponse response = new GetTechnologyByIdResponse();
        if(isTechnologyIdExist(technologyId)){
            response.setId(technologyRepository.getReferenceById(technologyId).getId());
            response.setName(technologyRepository.getReferenceById(technologyId).getName());
            response.setLanguageName(technologyRepository.getReferenceById(technologyId).getProgrammingLanguage().getName());
            return response;
        }
        else
            System.out.println("veritabanında bu veri bulunamadı.");
        return null;
    }

    private boolean isTechnologyIdExist(int id){
        for(int i = 0; i < technologyRepository.findAll().size(); i++)
            if(technologyRepository.findAll().get(i).getId() == id)
                return true;
        return false;
    }
    
    private boolean isTechnologyNameExist (Technology technology){
        for(Technology tech : technologyRepository.findAll())
            if(tech.getName().equalsIgnoreCase(technology.getName()))
                return true;
        return false;

    }
    private  boolean isTechnologyEmpty(Technology technology){
        return technology.getName().isEmpty() || technology.getName().isBlank();
    }
}
