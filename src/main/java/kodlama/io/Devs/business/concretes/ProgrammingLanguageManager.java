package kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.Devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.Devs.business.request.language.CreateLanguageRequest;
import kodlama.io.Devs.business.request.language.DeleteLanguageRequest;
import kodlama.io.Devs.business.request.language.UpdateLanguageRequest;
import kodlama.io.Devs.business.responses.language.GetAllLanguageResponse;
import kodlama.io.Devs.business.responses.language.GetLanguageByIdResponse;
import kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private ProgrammingLanguageRepository programmingLanguageRepository;

    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository){
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public void add(CreateLanguageRequest createLanguageRequest) {
        ProgrammingLanguage language = new ProgrammingLanguage();
        language.setName(createLanguageRequest.getName());
        if(!isLanguageEmpty(language) && !isLanguageNameExist(language))
            this.programmingLanguageRepository.save(language);
        else
            System.out.println("Aynı veri veya boş veri griilemez..");
    }

    @Override
    public void delete(DeleteLanguageRequest deleteLanguageRequest) {
        int flag = 0;
        for(ProgrammingLanguage language : programmingLanguageRepository.findAll()){
            if(language.getId() == deleteLanguageRequest.getId()){
                programmingLanguageRepository.deleteById(deleteLanguageRequest.getId());
                flag = 1;
                break;
            }
        }
        if(flag == 0)
            System.out.println("veritabanında bu veri bulunamadı.");
    }

    @Override
    public void update(int languageId,UpdateLanguageRequest updateLanguageRequest) {
        if(isLanguageIdExist(languageId)){
            ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(languageId).get();
            if(!isLanguageEmpty(programmingLanguage)){
                programmingLanguage.setName(updateLanguageRequest.getName());
                programmingLanguageRepository.save(programmingLanguage);
            }
            else
                System.out.println("Aynı veri veya boş veri griilemez..");
        }
        else
            System.out.println("veritabanında bu veri bulunamadı.");

    }

    @Override
    public List<GetAllLanguageResponse> getAll() {
        List <ProgrammingLanguage> languages = programmingLanguageRepository.findAll();
        List <GetAllLanguageResponse> languageResponses = new ArrayList<>();
        for(ProgrammingLanguage language: languages){
            GetAllLanguageResponse response = new GetAllLanguageResponse();
            response.setId(language.getId());
            response.setName(language.getName());
            languageResponses.add(response);
        }
        return  languageResponses;
    }

    @Override
    public GetLanguageByIdResponse getById(int languageId) {
        GetLanguageByIdResponse response = new GetLanguageByIdResponse();
            if(isLanguageIdExist(languageId)){
                response.setName(programmingLanguageRepository.getReferenceById(languageId).getName());
                response.setId(programmingLanguageRepository.getReferenceById(languageId).getId());
                return response;
            }
            else
                System.out.println("veritabanında bu veri bulunamadı.");
            return null;
    }

    private boolean isLanguageIdExist  (int id){
        for(int i = 0; i < programmingLanguageRepository.findAll().size(); i++)
            if(programmingLanguageRepository.findAll().get(i).getId() == id)
                return true;
        return false;
    }
    
    private boolean isLanguageNameExist (ProgrammingLanguage programmingLanguage){
        for(ProgrammingLanguage language : programmingLanguageRepository.findAll()){
            if(language.getName().equalsIgnoreCase(programmingLanguage.getName()))
                return true;
        }
        return false;
    }
    
    private  boolean isLanguageEmpty(ProgrammingLanguage programmingLanguage){
        return programmingLanguage.getName().isEmpty() || programmingLanguage.getName().isBlank();
    }
}