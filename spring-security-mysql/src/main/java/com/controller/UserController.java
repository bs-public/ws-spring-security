package com.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.UserDTO;
import com.service.UserService;


@RestController
public class UserController {

  private static Logger logger = LoggerFactory.getLogger(UserController.class);
  
  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public ResponseEntity<?> getDetails() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/v1/users")
  public ResponseEntity<?> getDetails(HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    logger.info("principal: {}", principal);
    String subject = principal.getName();
    UserDTO userDTO = userService.getBasicDetails(subject);
    return ResponseEntity.ok(userDTO);
  }

  /*
   * API accessible to authenticated users having role ADMIN. By using @PreAuthorize annotation
   * & @EnableGlobalMethodSecurity set to true in security configuration class.
   */
  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping("/v1/users/all")
  public ResponseEntity<?> getFullDetails(HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    logger.info("principal: {}", principal);
    String subject = principal.getName();
    List<UserDTO> userDTOs = userService.getFullDetails(subject);
    return ResponseEntity.ok(userDTOs);
  }
}
