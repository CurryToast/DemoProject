package org.iclass.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoMember {
	private String userid; // required
	private String name; // required
	@Setter
	private String password; // required
	private String email; // required
	private String birth; // required
	private String gender; // required
	private String phone; // required
	private String favorites;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String postalCode;
	private String adultYn; // required
	private Timestamp joinDate;
}
