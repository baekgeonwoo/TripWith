package com.janggoback.tripwith.sido.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sido {
	@Id
	private Long sidoCode; //서울특별시(1), 강화도(2)
	private String sidoName;
//	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "sido", cascade = CascadeType.REMOVE)
	private List<Gugun> guguns = new ArrayList<Gugun>();
	
	@Builder
	public Sido(String sidoName, Long sidoCode) {
		super();
		this.sidoName = sidoName;
		this.sidoCode = sidoCode;
	}	
}
