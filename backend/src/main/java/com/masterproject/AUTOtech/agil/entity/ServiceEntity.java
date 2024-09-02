package com.masterproject.AUTOtech.agil.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Services")
@Entity
public class ServiceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "positionX")
	private int positionX;
	
	@Column(name = "positionY")
	private int positionY;
	
	@Column(name = "connectedTo")
	private String connectedTo;
	
	/*@Builder.Default
	@OneToMany(mappedBy = "ecu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EcuSoftware> software = new ArrayList<EcuSoftware>();*/
	
	@Builder.Default
	@OneToMany(mappedBy = "serviceEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceProperty> serviceProperty = new ArrayList<ServiceProperty>();
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ecu_id")
    private Hardware hardware;
    
	//@Column(name = "parent_ecu_id")
	//private Long parent_ecu_id;
	
	
	
}




