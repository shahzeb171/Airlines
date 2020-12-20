package com.airlines;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
@Id
String username;
String password,name;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Users [username=" + username + ", password=" + password + ", name=" + name + "]";
}

}
