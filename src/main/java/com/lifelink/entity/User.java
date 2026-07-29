package com.lifelink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lifelink.common.BaseEntity;
import com.lifelink.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User extends BaseEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String email;

	private String password;

	private Role role;

	@Builder.Default
	private Boolean accountEnabled = true;
	@Builder.Default
	private Boolean profileCompleted = false;
}