package okten.javaspringboot.models.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okten.javaspringboot.models.views.Views;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @JsonView({Views.Admin.class})
    private int clientId;

    @JsonView({Views.Admin.class, Views.Client.class})
    private String clientName;

    @JsonView({Views.Admin.class, Views.Client.class})
    private String clientSurname;

    @JsonView({Views.Admin.class, Views.Client.class})
    private String clientEmail;
}
