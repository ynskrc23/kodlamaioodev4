package kodlama.io.Devs.business.abstracts;

import java.util.List;

import kodlama.io.Devs.business.request.technology.CreateTechnologyRequest;
import kodlama.io.Devs.business.request.technology.DeleteTechnologyRequest;
import kodlama.io.Devs.business.request.technology.UpdateTechnologyRequest;
import kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import kodlama.io.Devs.business.responses.technology.GetTechnologyByIdResponse;

public interface TechnologyService {

	 void add(CreateTechnologyRequest createTechnologyRequest);
	 
	 void delete(DeleteTechnologyRequest deleteTechnologyRequest);
	 
	 void update(int id,UpdateTechnologyRequest updateTechnologyRequest);
	 
	 List<GetAllTechnologyResponse> getAll();
	 
	 GetTechnologyByIdResponse getById (int technologyId);
}
