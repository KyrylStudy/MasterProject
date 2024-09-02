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
@Table(name = "ECUs")
@Entity
public class Hardware {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "label")
	private String label;
	
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
	
	@Builder.Default
	@OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EcuSoftware> software = new ArrayList<EcuSoftware>();
	
	@Builder.Default
	@OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HardwareProperty> property = new ArrayList<HardwareProperty>();
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "architecture_id")
    private Architecture architecture;
    
	@Builder.Default
	@OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceEntity> bus = new ArrayList<ServiceEntity>();
	
	
	
}




