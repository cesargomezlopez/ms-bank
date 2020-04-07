package ms.bank.model;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(value = "bank")
public class Bank {

  @Id
  private String id;
  
  @Indexed(unique = true)
  @NotEmpty(message = "Bank Name can not be empty")
  private String name;
  
  private Set<String> clientProfiles;
}
