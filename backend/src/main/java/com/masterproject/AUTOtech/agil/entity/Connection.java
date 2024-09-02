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
@Table(name = "Bus")
@Entity
public class Connection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "positionFromX")
	private String positionFromX;
	
	@Column(name = "positionFromY")
	private String positionFromY;
	
	@Column(name = "positionToX")
	private String positionToX;
	
	@Column(name = "positionToY")
	private String positionToY;
	
	@Column(name = "connectedFrom")
	private String connectedFrom;
	
	@Column(name = "connectedTo")
	private String connectedTo;
	
	@Column(name = "twoWayConnection")
	private Boolean twoWayConnection;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "architecture_id")
    private Architecture architecture;
    
	@Builder.Default
	@OneToMany(mappedBy = "connection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConnectionProperty> connectionProperty = new ArrayList<ConnectionProperty>();
	
	/*@Builder.Default
	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> properties = new ArrayList<Property>();*/

}
