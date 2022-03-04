package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Hunters;
import com.example.demo.repo.HuntersRepo;




@Service
public class HuntersService implements HuntersServiceIF<Hunters>{
private HuntersRepo repo;

@Autowired
public HuntersService(HuntersRepo repo) {
	super();
	this.repo=repo;
}

		//CREATE METHOD to make a record
		public Hunters create(Hunters h) {
			Hunters created = this.repo.save(h);
			return created;
}
		//READ METHOD to read em all (need 4 here)
		public List<Hunters> getAll() {
			return this.repo.findAll();
		}
//		//Get by specific echoes count
//		public List<Hunters> getAllHuntersdByEchoes(Integer echoes){
//			List<Hunters> found = this.repo.findByEchoes(echoes);
//			return found;
//		}
//		//Get by name
//		public List<Hunters> getAllHuntersByName(String name){
//			List<Hunters> found = this.repo.findByNameIgnoreCase(name);
//			return found;
//		}
//		//Get by whether the hunter fears the old blood or not (this makes no sense lorewise)
//		public List<Hunters> getAllHuntersByOldBlood(Boolean oldBloodFeared){
//			List<Hunters> found = this.repo.findByOldBlood(oldBloodFeared);
//			return found;
//		}
		
		//Get by specific iD (don't know why you'd use this personally)
		public Hunters getOne(Integer id) {
			Optional<Hunters> found = this.repo.findById(id);
			return found.get();
		}
		
		// UPDATE change a current record, changes name, echoes and if old blood is feared
		public Hunters replace(Integer id, Hunters newHunters) {
			Hunters existing = this.repo.findById(id).get();
			existing.setEchoes(newHunters.getEchoes());
			existing.setOldBloodFeared(newHunters.getOldBloodFeared());
			existing.setName(newHunters.getName());
			Hunters updated = this.repo.save(existing);
			return updated;
		}
		
		//DELETE simply delete a record
		public void remove(@PathVariable Integer id) {
			this.repo.deleteById(id);;
		}

	
}
