package com.synergisticit.domain;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role {
	
	 
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long roleId;
	    
	    private String roleName;
	    
	    @JsonBackReference
	    @ManyToMany(mappedBy="roles")
	    private List<User> users = new ArrayList<>();
}
