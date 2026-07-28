package com.lifelink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lifelink.common.BaseEntity;
import com.lifelink.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class User extends BaseEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String email;

	private String password;

	private Role role;

	private Boolean accountEnabled = true;
}