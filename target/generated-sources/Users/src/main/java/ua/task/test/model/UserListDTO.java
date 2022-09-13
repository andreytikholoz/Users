package ua.task.test.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ua.task.test.model.UserDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserListDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-13T19:23:31.572389100+03:00[Europe/Helsinki]")

public class UserListDTO   {
  @JsonProperty("users")
  @Valid
  private List<UserDTO> users = null;

  public UserListDTO users(List<UserDTO> users) {
    this.users = users;
    return this;
  }

  public UserListDTO addUsersItem(UserDTO usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<UserDTO>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<UserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserListDTO userListDTO = (UserListDTO) o;
    return Objects.equals(this.users, userListDTO.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserListDTO {\n");
    
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

