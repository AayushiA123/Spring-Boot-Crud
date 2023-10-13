package com.example.demo.dto;

import java.sql.Date;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Customer")
public class CustomerDto {

	public static final String message = "Not found";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotNull(message = "First Name should not be null")
	@NotEmpty(message = "First Name should not be empty")
	@Size(max = 30, message = "First Name must not be greater than 30 characters")
	@Size(min = 2, message = "First Name should have at least 2 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please enter characters only, not special characters or any numbers.")
	String firstName;
	
	@NotNull(message = "Last Name should not be null")
	//@NotEmpty(message = "Last Name should not be empty")
	@Size(max = 30, message = "Last Name must not be greater than 30 characters")
	@Size(min = 2, message = "Last Name should have at least 2 characters")
	// @Pattern(regexp = "[a-zA-Z]",message = "Please enter characters only and not
	// special character or any number")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please enter characters only, not special characters or any numbers.")
	String lastName;

	@NotNull(message = "Address should not be null")
	//@NotEmpty(message = "Address should not be empty")
	@Size(max = 70, message = "Address must not be greater than 255 characters")
	@Size(min = 20, message = "Address should have at least 20 characters")
	// @Pattern(regexp = "[a-zA-Z0-9]",message = "Please enter characters only and
	// not special character or any number")
	String address1;

	@Size(max = 70, message = "Address must not be greater than 255 characters")
	@Size(min = 2, message = "Address should have at least 20 characters")
	// @Pattern(regexp = "[a-zA-Z0-9]",message = "Please enter characters only and
	// not special character or any number")
	String address2;

	@NotNull(message = "Date of Birth Should not be null")
	//@DateTimeFormat(pattern = "dd-mm-yyyy")
	@Past(message = "Enter valid date.")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	Date dateOfBirth;

	@NotNull(message = "Mobile Number should not be null")
	@NotBlank(message="Mobile Number should not be blank")
	@Column(unique = true)
	@Digits(message = "Mobile number should contain 10 digits.", fraction = 0, integer = 10)
	@Size(min=10, max=17, message="Mobile number should contain 10 digits.")
	// @Length(min=10, max=10, message="aaaaaaaaaaa")
	@Pattern(regexp = "^[^A-Za-z]*$", message = "Please enter numbers only, not special characters or any characters.")
	String mobileNumber;

	@NotNull(message = "Age should not be null")
	// @NotBlank
	// @Size(min=18,max=30, message="age must be greatere than 18")
	// @Length(min=18,max=30,message="aaaaaaaaaa")
	// @Pattern(regexp="^[1-9]{2}$")
	Integer age;

	@NotNull(message = "Gender should not be null")
	@Enumerated(EnumType.STRING)
	GenderOption gender;

	@NotNull(message = "Email should not be null")
	@NotEmpty(message = "Email should not be empty")
	@Length(max = 70, message = "Email must not be greater than 70 characters")
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please write in proper email format.")
	@Email
	String email;

	public enum GenderOption {
		MALE, FEMALE
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public GenderOption getGender() {
		return gender;
	}

	public void setGender(GenderOption gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address1=" + address1
				+ ", address2=" + address2 + ", dateOfBirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber
				+ ", age=" + age + ", gender=" + gender + ", email=" + email + "]";
	}

	public CustomerDto(Long id, @NotNull @Length(max = 30) String firstName, @NotNull @Length(max = 30) String lastName,
			@NotNull @Length(max = 50) String address1, @Length(max = 50) String address2, @NotNull Date dateOfBirth,
			@NotNull @Length(max = 10) String mobileNumber, @NotNull Integer age, @NotNull GenderOption gender,
			@NotNull @Length(max = 70) String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}

	public CustomerDto() {
		super();
	}


}
