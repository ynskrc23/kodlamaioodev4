package kodlama.io.Devs.business.abstracts;

import java.util.List;

import kodlama.io.Devs.business.request.language.CreateLanguageRequest;
import kodlama.io.Devs.business.request.language.DeleteLanguageRequest;
import kodlama.io.Devs.business.request.language.UpdateLanguageRequest;
import kodlama.io.Devs.business.responses.language.GetAllLanguageResponse;
import kodlama.io.Devs.business.responses.language.GetLanguageByIdResponse;

public interface ProgrammingLanguageService {

	 void add(CreateLanguageRequest createLanguageRequest);
	 
	 void delete(DeleteLanguageRequest deleteLanguageRequest);
	 
	 void update(int id, UpdateLanguageRequest updateLanguageRequest);
	 
	 List<GetAllLanguageResponse> getAll();
	 
	 GetLanguageByIdResponse getById (int languageId);
}
