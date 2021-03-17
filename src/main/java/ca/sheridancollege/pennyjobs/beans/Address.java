package ca.sheridancollege.pennyjobs.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {

	private String street;
	private String city;
	private String postalCode;
	private String province;
}
