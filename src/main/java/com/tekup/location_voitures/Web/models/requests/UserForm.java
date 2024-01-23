package com.tekup.location_voitures.Web.models.requests;

import com.tekup.location_voitures.dao.entities.Role;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @Size(min = 3, max = 10, message = "invalid firstName caracters not between 3 and 10")
    private String firstName;
    @Size(min = 3, max = 10, message = "invalid lastName caracters not between 3 and 10")
    private String lastName;
    @Size(min = 3, max = 12, message = "invalid userName caracters not between 3 and 10")
    private String username;
    private String password;

    private Role role;
}
