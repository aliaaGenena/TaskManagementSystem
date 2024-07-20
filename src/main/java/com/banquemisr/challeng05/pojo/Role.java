package com.banquemisr.challeng05.pojo;


import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"ROLE\"")
public class Role implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")    
    private long id;

    private String name;
    
    private String type;
    
    private String description;

	@Override
	public String getAuthority() {
		 return name;
	}
	
}
