package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Hunters;

@Repository
public interface HuntersRepo extends JpaRepository<Hunters, Integer> {
//	List<Hunters> findByNameIgnoreCase(String name);
//	List<Hunters> findByEchoes(Integer echoes);
//	List<Hunters> findByOldBlood(Boolean oldBloodFeared);
//	

}
