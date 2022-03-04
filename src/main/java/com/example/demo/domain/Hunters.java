package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Hunters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String name;
	
	private Integer echoes;
	
	public Hunters() {
		super();
	}

	private Boolean oldBloodFeared;

	public Hunters(Integer id, String name, Integer echoes, Boolean oldBloodFeared) {
		super();
		this.id = id;
		this.name = name;
		this.echoes = echoes;
		this.oldBloodFeared = oldBloodFeared;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEchoes() {
		return echoes;
	}

	public void setEchoes(Integer echoes) {
		this.echoes = echoes;
	}

	public Boolean getOldBloodFeared() {
		return oldBloodFeared;
	}

	public void setOldBloodFeared(Boolean oldBloodFeared) {
		this.oldBloodFeared = oldBloodFeared;
	}

	@Override
	public String toString() {
		return "Hunters [id=" + id + ", name=" + name + ", echoes=" + echoes + ", oldBloodFeared=" + oldBloodFeared
				+ "]";
	}
}
